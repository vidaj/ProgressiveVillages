package com.progressivevillages;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Constants.ModId, name = Constants.ModName, version = Constants.ModVersion)
public class ProgressiveVillages {

	@Instance(Constants.ModId)
	public static ProgressiveVillages instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {

	}
}
