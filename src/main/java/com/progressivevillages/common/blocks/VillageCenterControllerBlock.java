package com.progressivevillages.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.progressivevillages.ProgressiveVillages;

public class VillageCenterControllerBlock extends AbstractBlock {
	
	public static final String Name = "villagecentercontroller";
	
	public VillageCenterControllerBlock() {
		super(Material.rock);
		GameRegistry.registerBlock(this);
	}
	
	@Override
	protected String getName() {
		return Name;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos,
			IBlockState state, EntityPlayer playerIn, EnumFacing side,
			float hitX, float hitY, float hitZ) {

		playerIn.openGui(ProgressiveVillages.instance, 1, world, pos.getX(), pos.getY(), pos.getZ());
		
//		BlockPos above = pos.up();
//		
//		generateFloor(world, above, 7, 10, Blocks.planks.getDefaultState());
		
		return false;
	}

	private void generateFloor(World world, BlockPos above, int width, int depth, IBlockState material) {
		int halfWidth = width / 2;
		int halfDepth = depth / 2;
		
		for (int depthOffset = -halfDepth; depthOffset < halfDepth; depthOffset++) {
			for (int widthOffset = -halfWidth; widthOffset < halfWidth; widthOffset++) {
				BlockPos position = above.add(depthOffset, 0, widthOffset);
				
				world.setBlockState(position, material);
			}
		}
	}
	
	
}
