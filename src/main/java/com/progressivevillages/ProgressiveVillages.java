package com.progressivevillages;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.progressivevillages.common.CommonProxy;

@Mod(modid = Constants.ModId, name = Constants.ModName, version = Constants.ModVersion)
public class ProgressiveVillages {

	@Instance(Constants.ModId)
	public static ProgressiveVillages instance;
	
	@SidedProxy(clientSide=Constants.ClientProxyClassName, serverSide=Constants.ServerProxyClassName)
	public static CommonProxy proxy;

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerOreDict();
		proxy.registerRecipes();
		proxy.registerEventHandlers();
		proxy.registerGui();
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.registerFluids();
		proxy.registerBlocks();
		proxy.registerItems();
	}
}
