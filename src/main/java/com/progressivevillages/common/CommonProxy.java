package com.progressivevillages.common;

import net.minecraftforge.fml.common.network.NetworkRegistry;

import com.progressivevillages.ProgressiveVillages;
import com.progressivevillages.common.blocks.IModBlocks;
import com.progressivevillages.common.blocks.ModBlocks;

public class CommonProxy {
	
	private ModBlocks blocks;
	
	private GuiHandler guiHandler;

	public void registerRecipes() {
	}
	
	public void registerEventHandlers() {
		
	}
	
	public void registerBlocks() {
		blocks = new ModBlocks();
		blocks.initialize();
	}
	
	public void registerItems() {
	}
	
	public void registerFluids() {
		
	}

	public void registerOreDict() {
	}
	
	public IModBlocks getBlocks() {
		return blocks;
	}
	
	public void registerGui() {
		guiHandler = new GuiHandler();
		NetworkRegistry.INSTANCE.registerGuiHandler(ProgressiveVillages.instance, guiHandler);
	}
}
