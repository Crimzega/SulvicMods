package com.sulvic.core.client;

import java.lang.reflect.Constructor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

@SuppressWarnings({"unchecked"})
public class ParticleHelper{
	
	private static final Logger logger = LogManager.getLogger("ParticleHelper");
	private static final Minecraft minecraft = Minecraft.getMinecraft();
	
	private static Particle instantiateParticle(Class<? extends Particle> particleClass, double posX, double posY, double posZ, double moveX, double moveY, double moveZ){
		try{
			Constructor<Particle> constructor = (Constructor<Particle>)particleClass.getDeclaredConstructor(World.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class);
			if(constructor != null){
				constructor.setAccessible(true);
				return constructor.newInstance(minecraft.world, posX, posY, posZ, moveX, moveY, moveZ);
			}
			else{
				constructor = (Constructor<Particle>)particleClass.getDeclaredConstructor(World.class, Double.class, Double.class, Double.class);
				constructor.setAccessible(true);
				Particle particle = constructor.newInstance(minecraft.world, posX, posY, posZ);
				particle.move(moveX, moveY, moveZ);
				return particle;
			}
		}
		catch(Exception e){
			logger.warn("Unable to instantiate particle class, missing a valid constructor. Class: {}", particleClass.getName());
			return null;
		}
	}
	
	public static Particle spawnParticle(Class<? extends Particle> particleClass, double posX, double posY, double posZ, double moveX, double moveY, double moveZ){
		if(minecraft != null && minecraft.getRenderViewEntity() != null && minecraft.effectRenderer != null){
			int setting = minecraft.gameSettings.particleSetting;
			if(setting == 1 && minecraft.world.rand.nextInt(3) == 0) setting = 2;
			double x = minecraft.getRenderViewEntity().posX - posX, y = minecraft.getRenderViewEntity().posY - posY, z = minecraft.getRenderViewEntity().posZ - posZ;
			Particle particle = null;
			double d = 16d;
			if(x * x + y * y + z * z > d * d) return null;
			else if(setting > 1) return null;
			else{
				particle = instantiateParticle(particleClass, posX, posY, posZ, moveX, moveY, moveZ);
				if(particle != null){
					minecraft.effectRenderer.addEffect(particle);
					return particle;
				}
				else return null;
			}
		}
		return null;
	}
	
}
