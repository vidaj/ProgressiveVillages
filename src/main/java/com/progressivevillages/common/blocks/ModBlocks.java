package com.progressivevillages.common.blocks;

import java.util.LinkedList;
import java.util.List;

import net.minecraftforge.fml.common.FMLLog;

import com.progressivevillages.common.blocks.decorative.ByzantineTile;

public class ModBlocks implements IModBlocks {
	
	private static boolean isInitialized = false;
	
	protected List<AbstractBlock> allBlocks = new LinkedList<AbstractBlock>();

	private VillageCenterControllerBlock villageCenterController;

	private ByzantineTile byzantianTile;
	
	@Override
	public AbstractBlock getVillageCenterController() {
		return villageCenterController;
	}
	
	@Override
	public AbstractBlock getByzantineTile() {
		return byzantianTile;
	}
	
	public void initialize() {
		if (isInitialized) {
			FMLLog.warning("[ProgressiveVillages] Initializing blocks after they are already initialized. This is a programming error. Please report this to the mod author.");
			return;
		}
		
		isInitialized = true;
		
		villageCenterController = new VillageCenterControllerBlock();
		byzantianTile = new ByzantineTile();
		
		allBlocks.add(villageCenterController);
		allBlocks.add(byzantianTile);
	}

	@Override
	public List<AbstractBlock> getAllBlocks() {
		return allBlocks;
	}

	

}
