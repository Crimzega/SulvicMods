package com.sulvic.core.common;

import static com.sulvic.core.world.gen.VeinInfo.create;
import static net.minecraft.init.Biomes.BEACH;
import static net.minecraft.init.Biomes.BIRCH_FOREST_HILLS;
import static net.minecraft.init.Biomes.COLD_BEACH;
import static net.minecraft.init.Biomes.COLD_TAIGA_HILLS;
import static net.minecraft.init.Biomes.DEEP_OCEAN;
import static net.minecraft.init.Biomes.DESERT;
import static net.minecraft.init.Biomes.DESERT_HILLS;
import static net.minecraft.init.Biomes.EXTREME_HILLS;
import static net.minecraft.init.Biomes.EXTREME_HILLS_WITH_TREES;
import static net.minecraft.init.Biomes.FOREST;
import static net.minecraft.init.Biomes.FOREST_HILLS;
import static net.minecraft.init.Biomes.FROZEN_OCEAN;
import static net.minecraft.init.Biomes.FROZEN_RIVER;
import static net.minecraft.init.Biomes.JUNGLE;
import static net.minecraft.init.Biomes.JUNGLE_HILLS;
import static net.minecraft.init.Biomes.MESA;
import static net.minecraft.init.Biomes.MESA_CLEAR_ROCK;
import static net.minecraft.init.Biomes.MESA_ROCK;
import static net.minecraft.init.Biomes.MUSHROOM_ISLAND;
import static net.minecraft.init.Biomes.MUSHROOM_ISLAND_SHORE;
import static net.minecraft.init.Biomes.MUTATED_BIRCH_FOREST_HILLS;
import static net.minecraft.init.Biomes.MUTATED_DESERT;
import static net.minecraft.init.Biomes.MUTATED_EXTREME_HILLS;
import static net.minecraft.init.Biomes.MUTATED_EXTREME_HILLS_WITH_TREES;
import static net.minecraft.init.Biomes.MUTATED_MESA;
import static net.minecraft.init.Biomes.MUTATED_MESA_CLEAR_ROCK;
import static net.minecraft.init.Biomes.MUTATED_MESA_ROCK;
import static net.minecraft.init.Biomes.MUTATED_REDWOOD_TAIGA_HILLS;
import static net.minecraft.init.Biomes.MUTATED_SAVANNA;
import static net.minecraft.init.Biomes.MUTATED_SAVANNA_ROCK;
import static net.minecraft.init.Biomes.OCEAN;
import static net.minecraft.init.Biomes.PLAINS;
import static net.minecraft.init.Biomes.REDWOOD_TAIGA;
import static net.minecraft.init.Biomes.REDWOOD_TAIGA_HILLS;
import static net.minecraft.init.Biomes.RIVER;
import static net.minecraft.init.Biomes.SAVANNA;
import static net.minecraft.init.Biomes.SAVANNA_PLATEAU;
import static net.minecraft.init.Biomes.STONE_BEACH;
import static net.minecraft.init.Biomes.SWAMPLAND;
import static net.minecraft.init.Biomes.TAIGA;
import static net.minecraft.init.Biomes.TAIGA_HILLS;

import java.awt.Color;
import java.util.Map;

import com.google.common.collect.Maps;
import com.sulvic.core.SulvicCore;
import com.sulvic.core.common.block.BlockGem;
import com.sulvic.core.common.block.BlockSpecial;
import com.sulvic.core.common.block.BlockSpecialWorkbench;
import com.sulvic.core.common.block.BlockTank;
import com.sulvic.core.common.block.CropSweetPotato;
import com.sulvic.core.common.block.OreGem;
import com.sulvic.core.common.block.OreSpecial;
import com.sulvic.core.common.item.ItemBlockGem;
import com.sulvic.core.common.item.ItemGem;
import com.sulvic.core.common.item.ItemArmorGem;
import com.sulvic.core.common.item.ItemAxeGem;
import com.sulvic.core.common.item.ItemHoeGem;
import com.sulvic.core.common.item.ItemPickaxeGem;
import com.sulvic.core.common.item.ItemSpadeGem;
import com.sulvic.core.common.item.ItemSwordGem;
import com.sulvic.core.common.item.ItemSpecialDust;
import com.sulvic.core.common.item.ItemSpecialIngot;
import com.sulvic.core.common.item.ItemSweetPotato;
import com.sulvic.core.common.item.ItemWrench;
import com.sulvic.core.lib.ZimedaRegistry;
import com.sulvic.core.util.ArmorHelper;
import com.sulvic.core.world.BiomeFlatlands;
import com.sulvic.core.world.gen.IVein;
import com.sulvic.core.world.gen.VeinInfo;
import com.sulvic.lib.DoubleValueSet;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.biome.Biome;

public class SulvicObjects{
	
	private static final Map<EnumGem, DoubleValueSet<ToolMaterial, ArmorMaterial>> GEM_MATERIALS = Maps.newHashMap();
	public static final OreGem GEM_ORES = new OreGem();
	public static final OreSpecial SPECIAL_ORE = new OreSpecial();
	public static final BlockGem GEM_BLOCKS = new BlockGem();
	public static final BlockSpecial SPECIAL_BLOCK = new BlockSpecial();
	public static final CropSweetPotato SWEET_POTATOES = new CropSweetPotato();
	public static final BlockTank TANK = new BlockTank();
	public static final BlockSpecialWorkbench SPECIAL_WORKBENCH = new BlockSpecialWorkbench();
	public static final ItemGem GEMS = new ItemGem();
	public static final ItemSpecialDust SPECIAL_DUST = new ItemSpecialDust();
	public static final ItemSpecialIngot SPECIAL_INGOT = new ItemSpecialIngot();
	public static final ItemSweetPotato SWEET_POTATO = new ItemSweetPotato();
	public static final ItemWrench WRENCH = new ItemWrench();
	public static final BiomeFlatlands FLATLANDS = new BiomeFlatlands();
	
	public static ItemArmorGem getGemArmor(EnumGem gem, ArmorHelper armor){ return ItemArmorGem.getByType(gem, armor); }
	
	public static ItemAxeGem getGemAxe(EnumGem gem){ return ItemAxeGem.getByType(gem); }
	
	public static ItemHoeGem getGemHoe(EnumGem gem){ return ItemHoeGem.getByType(gem); }
	
	public static ItemPickaxeGem getGemPickaxe(EnumGem gem){ return ItemPickaxeGem.getByType(gem); }
	
	public static ItemSpadeGem getGemSpade(EnumGem gem){ return ItemSpadeGem.getByType(gem); }
	
	public static ItemSwordGem getGemSword(EnumGem gem){ return ItemSwordGem.getByType(gem); }
	
	public static ToolMaterial getGemToolMaterial(EnumGem gem){ return GEM_MATERIALS.get(gem).getMainValue(); }
	
	public static ArmorMaterial getGemArmorMaterial(EnumGem gem){ return GEM_MATERIALS.get(gem).getSecondValue(); }
	
	public static void addToRegistry(){
		SulvicCore.getLogger().info("Registering mod objects");
		ZimedaRegistry.addBlock(GEM_ORES, new ItemBlockGem(GEM_ORES));
		ZimedaRegistry.addBlock(SPECIAL_ORE);
		ZimedaRegistry.addBlock(GEM_BLOCKS, new ItemBlockGem(GEM_BLOCKS));
		ZimedaRegistry.addBlock(SPECIAL_BLOCK);
		ZimedaRegistry.addBlock(SWEET_POTATOES);
		ZimedaRegistry.addBlock(TANK);
		ZimedaRegistry.addBlock(SPECIAL_WORKBENCH);
		ZimedaRegistry.addItem(GEMS);
		ZimedaRegistry.addItem(SPECIAL_DUST);
		ZimedaRegistry.addItem(SPECIAL_INGOT);
		ZimedaRegistry.addItem(SWEET_POTATO);
		ZimedaRegistry.addItem(WRENCH);
		for(EnumGem gem: EnumGem.values()){
			ZimedaRegistry.addItem(ItemSwordGem.getByType(gem));
			ZimedaRegistry.addItem(ItemSpadeGem.getByType(gem));
			ZimedaRegistry.addItem(ItemPickaxeGem.getByType(gem));
			ZimedaRegistry.addItem(ItemAxeGem.getByType(gem));
			for(ArmorHelper armor: ArmorHelper.values()) ZimedaRegistry.addItem(ItemArmorGem.getByType(gem, armor));
		}
		for(EnumGem gem: EnumGem.values()) ZimedaRegistry.addItem(ItemHoeGem.getByType(gem));
		ZimedaRegistry.addBiome(FLATLANDS);
	}
	
	public static void init(){
		for(EnumGem gem: EnumGem.values()) AzurilMaterials.setRepairStack(gem);
		ItemSwordGem.init();
		ItemSpadeGem.init();
		ItemPickaxeGem.init();
		ItemAxeGem.init();
		ItemHoeGem.init();
		ItemArmorGem.init();
	}
	
	public static class GemInfo{
		
		protected static final GemInfo gemRuby = new GemInfo("ruby").setVeinInfo(create(4, 12, 2, 8, 3)).setColor(0xDD2F46);
		protected static final GemInfo gemPinkPanther = new GemInfo("pink_panther", "pinkPanther").setVeinInfo(create(8, 20, 3, 6, 4)).setColor(0xFF40FF);
		protected static final GemInfo gemSapphire = new GemInfo("sapphire").setVeinInfo(create(4, 12, 2, 5, 3)).setColor(0x7E3E7E);
		protected static final GemInfo gemCassiterite = new GemInfo("cassiterite").setVeinInfo(create(6, 17, 6, 9, 6)).setColor(0x151515);
		protected static final GemInfo gemEnstatite = new GemInfo("enstatite").setVeinInfo(create(4, 12, 4, 9, 4)).setColor(0xE7B600);
		protected static final GemInfo gemMoonstone = new GemInfo("moonstone").setVeinInfo(create(4, 12, 2, 8, 5)).setColor(0xFFFF61);
		protected static final GemInfo gemAquamarine = new GemInfo("aquamarine").setVeinInfo(create(4, 12, 2, 5, 7)).setColor(0x8ec2FF);
		protected static final GemInfo gemCitrine = new GemInfo("citrine").setVeinInfo(create(6, 14, 3, 9, 5)).setColor(0xFF9200);
		protected static final GemInfo gemTourmaline = new GemInfo("tourmaline").setVeinInfo(create(4, 12, 2, 5, 5)).setColor(0xFF8499);
		protected static final GemInfo gemPeridot = new GemInfo("peridot").setVeinInfo(create(5, 14, 4, 6, 4)).setColor(0x00FF21);
		protected static final GemInfo gemBeryl = new GemInfo("beryl").setVeinInfo(create(7, 12, 2, 10, 3)).setColor(0x3BC9D6);
		protected static final GemInfo gemFireAgate = new GemInfo("fire_agate", "fireAgate").setVeinInfo(create(8, 20, 2, 8, 5)).setColor(0xD85900);
		protected static final GemInfo gemDruzy = new GemInfo("druzy").setVeinInfo(create(4, 18, 4, 10, 6)).setColor(0x3D1386);
		protected static final GemInfo gemAmetrine = new GemInfo("ametrine").setVeinInfo(create(6, 12, 4, 8, 4)).setColor(0x4F2454);
		protected static final GemInfo gemOnyx = new GemInfo("onyx").setVeinInfo(create(7, 19, 3, 9, 4)).setColor(0x343434);
		protected int gemColor;
		protected VeinInfo veinInfo;
		protected String theName, unlocalName;
		
		public GemInfo(String name){ this(name, name); }
		
		public GemInfo(String name, String unlocal){
			theName = name;
			unlocalName = unlocal;
		}
		
		private int getColor(float r, float g, float b){ return new Color(r, g, b).getRGB(); }
		
		private int getColor(int r, int g, int b){ return new Color(r, g, b).getRGB(); }
		
		public GemInfo setColor(float r, float g, float b){
			gemColor = getColor(r, g, b);
			return this;
		}
		
		public GemInfo setColor(int rgb){
			gemColor = rgb;
			return this;
		}
		
		public GemInfo setColor(int r, int g, int b){
			gemColor = getColor(r, g, b);
			return this;
		}
		
		public GemInfo setVeinInfo(VeinInfo info){
			veinInfo = info;
			return this;
		}
		
		public boolean equals(Object obj){ return super.equals(obj); }
		
		public int hashCode(){ return super.hashCode(); }
		
	}
	
	public static enum EnumGem implements IStringSerializable, IVein{
		
		RUBY(0, GemInfo.gemRuby),
		PINK_PANTHER(1, GemInfo.gemPinkPanther),
		SAPPHIRE(2, GemInfo.gemSapphire),
		CASSITERITE(3, GemInfo.gemCassiterite),
		ENSTATITE(4, GemInfo.gemEnstatite),
		MOONSTONE(5, GemInfo.gemMoonstone),
		AQUAMARINE(6, GemInfo.gemAquamarine),
		CITRINE(7, GemInfo.gemCitrine),
		TOURMALINE(8, GemInfo.gemTourmaline),
		PERIDOT(9, GemInfo.gemPeridot),
		BERYL(10, GemInfo.gemBeryl),
		FIRE_AGATE(11, GemInfo.gemFireAgate),
		DRUZY(12, GemInfo.gemDruzy),
		AMETRINE(13, GemInfo.gemAmetrine),
		ONYX(14, GemInfo.gemOnyx);
		
		protected final GemInfo theInfo;
		protected int gemMetadata;
		
		EnumGem(int metadata, GemInfo info){
			gemMetadata = metadata;
			theInfo = info;
		}
		
		public static EnumGem byMetadata(int metadata){
			EnumGem result = null;
			for(EnumGem gem: values()) if(gem.gemMetadata == metadata){
				result = gem;
				break;
			}
			return result;
		}
		
		public static int size(){ return values().length; }
		
		public Biome[] allowedBiomes(){
			switch(this){
				case RUBY:
					return new Biome[0];
				case PINK_PANTHER:
					return new Biome[]{PLAINS, DESERT, DESERT_HILLS, EXTREME_HILLS, JUNGLE};
				case SAPPHIRE:
					return new Biome[]{FOREST, TAIGA, SWAMPLAND, STONE_BEACH, REDWOOD_TAIGA, REDWOOD_TAIGA_HILLS};
				case CASSITERITE:
					return new Biome[0];
				case ENSTATITE:
					return new Biome[]{OCEAN, RIVER, FROZEN_OCEAN, FROZEN_RIVER, DEEP_OCEAN, MESA, MESA_ROCK, MESA_CLEAR_ROCK};
				case MOONSTONE:
					return new Biome[0];
				case AQUAMARINE:
					return new Biome[]{PLAINS, BEACH, STONE_BEACH, COLD_BEACH, FLATLANDS};
				case CITRINE:
					return new Biome[0];
				case TOURMALINE:
					return new Biome[]{EXTREME_HILLS, DESERT_HILLS, FOREST_HILLS, TAIGA_HILLS, JUNGLE_HILLS, BIRCH_FOREST_HILLS, COLD_TAIGA_HILLS, EXTREME_HILLS_WITH_TREES, MUTATED_EXTREME_HILLS,
						MUTATED_BIRCH_FOREST_HILLS, MUTATED_REDWOOD_TAIGA_HILLS, MUTATED_EXTREME_HILLS_WITH_TREES};
				case PERIDOT:
					return new Biome[]{DESERT, BEACH, DESERT_HILLS, SAVANNA, SAVANNA_PLATEAU, MUTATED_DESERT, MUTATED_SAVANNA, MUTATED_SAVANNA_ROCK};
				case BERYL:
					return new Biome[0];
				case FIRE_AGATE:
					return new Biome[]{DESERT, DESERT_HILLS, MUTATED_DESERT};
				case DRUZY:
					return new Biome[]{MUSHROOM_ISLAND, MUSHROOM_ISLAND_SHORE};
				case AMETRINE:
					return new Biome[0];
				case ONYX:
					return new Biome[]{MESA, MESA_ROCK, MESA_CLEAR_ROCK, MUTATED_MESA, MUTATED_MESA_ROCK, MUTATED_MESA_CLEAR_ROCK};
				default:
					return new Biome[0];
			}
		}
		
		public GemInfo getInfo(){ return theInfo; }
		
		public IBlockState getBlockState(){ return SulvicObjects.GEM_ORES.getStateFromMeta(gemMetadata); }
		
		public int getDimensionId(){ return 0; }
		
		public int getColor(){ return theInfo.gemColor; }
		
		public int getMetadata(){ return gemMetadata; }
		
		public String getName(){ return theInfo.theName; }
		
		public String getUnlocalName(){ return theInfo.unlocalName; }
		
		public VeinInfo getVeinInfo(){ return theInfo.veinInfo; }
		
	}
	
}
