package com.progressivevillages.client;

import com.progressivevillages.common.CommonProxy;
import com.progressivevillages.common.blocks.AbstractBlock;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerBlocks() {
		super.registerBlocks();
		
		for (AbstractBlock block : getBlocks().getAllBlocks()) {
			block.initModel();
		}
	}

}
