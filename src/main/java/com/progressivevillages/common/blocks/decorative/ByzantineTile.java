package com.progressivevillages.common.blocks.decorative;

import java.util.List;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.progressivevillages.common.blocks.AbstractBlock;

public class ByzantineTile extends AbstractBlock  implements IMetaBlockName{
	
	public static final String Name = "byzantinetile";
	
	public static final PropertyEnum<TileType> VARIANT = PropertyEnum.<TileType>create("variant", TileType.class);

	public ByzantineTile() {
		super(Material.rock);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, TileType.Gold));
        this.setCreativeTab(CreativeTabs.tabBlock);
        GameRegistry.registerBlock(this);
	}
	
	@Override
	protected String getName() {
		return Name;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this),	TileType.Gold.metadata, new ModelResourceLocation(getRegistryName(), TileType.Gold.unlocalizedName));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this),	TileType.Blue.metadata, new ModelResourceLocation(getRegistryName(), TileType.Blue.unlocalizedName));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this),	TileType.Green.metadata, new ModelResourceLocation(getRegistryName(), TileType.Green.unlocalizedName));
	}
	
	
	@Override
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
    {
        list.add(new ItemStack(this, 1, TileType.Gold.getMetadata()));
        list.add(new ItemStack(this, 1, TileType.Blue.getMetadata()));
        list.add(new ItemStack(this, 1, TileType.Green.getMetadata()));
    }
	
	@Override
	public int getDamageValue(World worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        return iblockstate.getValue(VARIANT).getMetadata();
    }
	
	@Override
	public IBlockState getStateFromMeta(int meta)
    {
        TileType byMetadata = TileType.byMetadata(meta);
		return this.getDefaultState().withProperty(VARIANT, byMetadata);
    }

	@Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(VARIANT).getMetadata();
    }

	@Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {VARIANT });
    }
	
	@Override
	public int damageDropped(IBlockState state)
    {
        TileType tileType = state.getValue(VARIANT);
        
        return tileType.metadata;
    }
	
	@Override
	public String getSpecialName(ItemStack stack) {
		int meta = stack.getItemDamage();
		return TileType.byMetadata(meta).name;
	}
	
	public static enum TileType implements IStringSerializable
    {
        Gold(0, "gold", MapColor.dirtColor),
        Blue(1, "blue", MapColor.dirtColor),
        Green(2, "green", MapColor.obsidianColor);

//        private static final TileType[] METADATA_LOOKUP = new TileType[values().length];
        private final int metadata;
        private final String name;
        private final String unlocalizedName;
        private final MapColor field_181067_h;

        private TileType(int metadata, String name, MapColor color)
        {
            this(metadata, name, name, color);
        }

        private TileType(int metadata, String name, String unlocalizedName, MapColor color)
        {
            this.metadata = metadata;
            this.name = name;
            this.unlocalizedName = unlocalizedName;
            this.field_181067_h = color;
        }

        public int getMetadata()
        {
            return this.metadata;
        }

        public String getUnlocalizedName()
        {
            return this.unlocalizedName;
        }

        public MapColor func_181066_d()
        {
            return this.field_181067_h;
        }

        public String toString()
        {
            return this.name;
        }

        public static TileType byMetadata(int metadata)
        {
        	switch (metadata) {
        	case 0:	return Gold;
        	case 1: return Blue;
        	case 2: return Green;
        	}
        	
        	
        	return Green;
        }

        public String getName()
        {
            return this.name;
        }

//        static
//        {
//            for (TileType type : values())
//            {
//                METADATA_LOOKUP[type.getMetadata()] = type;
//            }
//        }
    }


	
}
