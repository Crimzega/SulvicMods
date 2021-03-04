package com.sulvic.core.lib;

import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatBasic;
import net.minecraft.util.text.TextComponentTranslation;

public class GrehzkinStats{
	
	public static final StatBase DISC_PLAYED = (new StatBasic("stat.discPlayed", new TextComponentTranslation("stat.recordPlayed", new Object[0]))).registerStat();
	
}
