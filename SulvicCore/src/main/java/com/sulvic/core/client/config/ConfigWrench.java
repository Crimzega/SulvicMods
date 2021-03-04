package com.sulvic.core.client.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.sulvic.core.SulvicCore;
import com.sulvic.core.api.WrenchAPI;
import com.sulvic.core.api.WrenchBlock;
import com.sulvic.io.SulvicIO;

@SuppressWarnings({"serial"})
public class ConfigWrench{
	
	private static final Gson GSON = new GsonBuilder().registerTypeAdapter(new TypeToken<WrenchBlock[]>(){
		
	}.getType(), new Helper()).setPrettyPrinting().create();
	private static boolean useTemp = false;
	public static ConfigWrench instance;
	private final File jsonFile;
	private WrenchBlock[] origWrenchables = new WrenchBlock[0], newWrenchables = new WrenchBlock[0];
	
	private ConfigWrench(File folder){
		jsonFile = new File(folder, "sulvic/wrench.json");
		instance = this;
	}
	
	public static void init(FMLPreInitializationEvent evt){ new ConfigWrench(evt.getModConfigurationDirectory()); }
	
	private void addBlock(WrenchBlock wrenchable){
		WrenchBlock[] temp = new WrenchBlock[newWrenchables.length + 1];
		for(int i = 0; i < newWrenchables.length; i++) temp[i] = newWrenchables[i];
		temp[newWrenchables.length] = wrenchable;
		newWrenchables = temp;
	}
	
	private void writeFile(){
		try{
			origWrenchables = newWrenchables;
			FileWriter writer = new FileWriter(jsonFile);
			GSON.toJson(origWrenchables, writer);
			SulvicIO.closeQuietly(writer);
		}
		catch(IOException ioe){
			SulvicCore.getLogger().catching(ioe);
		}
	}
	
	public boolean hasChanged(){ return !Arrays.equals(origWrenchables, newWrenchables); }
	
	public void addBlock(Block block){ addBlock(WrenchAPI.createWrenchable(block)); }
	
	public void addBlock(Block block, int min){ addBlock(WrenchAPI.createWrenchable(block, min)); }
	
	public void addBlock(Block block, int min, int max){ addBlock(WrenchAPI.createWrenchable(block, min, max)); }
	
	public void load() throws IOException{
		if(!jsonFile.exists()){
			useTemp = true;
			addBlock(Blocks.AIR, 1, 2);
			writeFile();
		}
		FileReader reader = new FileReader(jsonFile);
		origWrenchables = newWrenchables = GSON.fromJson(reader, WrenchBlock[].class);
		SulvicIO.closeQuietly(reader);
	}
	
	public void save(){
		writeFile();
		if(useTemp) useTemp = false;
	}
	
	protected static class Helper implements JsonDeserializer<WrenchBlock[]>, JsonSerializer<WrenchBlock[]>{
		
		public WrenchBlock[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException{
			WrenchBlock[] array = null;
			if(json.isJsonArray()){
				JsonArray jsonArr = json.getAsJsonArray();
				array = new WrenchBlock[jsonArr.size()];
				for(int i = 0; i < jsonArr.size(); i++){
					JsonElement jsonElem = jsonArr.get(i);
					WrenchBlock wrenchable = null;
					if(jsonElem.isJsonObject()){
						JsonObject jsonWrenchable = jsonElem.getAsJsonObject();
						wrenchable = WrenchAPI.createWrenchable(Block.getBlockFromName(jsonWrenchable.get("name").getAsString()));
						if(jsonWrenchable.has("damage")){
							JsonElement jsonElem1 = jsonWrenchable.get("damage");
							if(jsonElem1.isJsonPrimitive()) wrenchable.setDamage(jsonElem1.getAsInt());
							else{
								JsonObject jsonDmg = jsonElem1.getAsJsonObject();
								wrenchable.setDamage(jsonDmg.get("min").getAsInt(), jsonDmg.get("max").getAsInt());
							}
						}
					}
					array[i] = wrenchable;
				}
			}
			return array;
		}
		
		public JsonElement serialize(WrenchBlock[] src, Type typeOfSrc, JsonSerializationContext context){
			JsonArray jsonArr = new JsonArray();
			boolean writtenComment = true;
			for(WrenchBlock wrenchable: src){
				JsonObject jsonObj = new JsonObject();
				if(useTemp && !writtenComment){
					jsonObj.addProperty("comment", "This is the format style that is to be expected.");
					writtenComment = true;
				}
				jsonObj.addProperty("name", Block.REGISTRY.getNameForObject(wrenchable.getBlock()).toString());
				WrenchBlock.Damage dmg = wrenchable.getDamage();
				if(dmg != null){
					if(dmg.usesMax()){
						JsonObject jsonDmg = new JsonObject();
						jsonDmg.addProperty("min", dmg.getMin());
						jsonDmg.addProperty("max", dmg.getMax());
						jsonObj.add("damage", jsonDmg);
					}
					else jsonObj.addProperty("damage", dmg.getMin());
				}
			}
			return jsonArr;
		}
		
	}
	
}
