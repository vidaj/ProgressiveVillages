package com.progressivevillages.common.blocks.decorative;

import java.util.EnumMap;
import java.util.EnumSet;
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
        GameRegistry.registerBlock(this, ItemBlockMeta.class);
	}
	
	@Override
	protected String getName() {
		return Name;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModel() {
		Item item = Item.getItemFromBlock(this);
		String registryName = getRegistryName();
		
		for (TileType type : EnumSet.allOf(TileType.class)) {
			ModelLoader.setCustomModelResourceLocation(item, type.getMetadata(), new ModelResourceLocation(registryName, "variant=" + type.getUnlocalizedName()));
		}
		
//		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this),	TileType.Gold.metadata, new ModelResourceLocation(getRegistryName(), "variant="+TileType.Gold.unlocalizedName));
//		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this),	TileType.Blue.metadata, new ModelResourceLocation(getRegistryName(), "variant="+TileType.Blue.unlocalizedName));
//		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this),	TileType.Green.metadata, new ModelResourceLocation(getRegistryName(), "variant="+TileType.Green.unlocalizedName));
	}
	
	
	@Override
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
    {
		for (TileType type : EnumSet.allOf(TileType.class)) {
			list.add(new ItemStack(this, 1, type.getMetadata()));
		}
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
        Gold(0, "gold"),
        Blue(1, "blue"),
        Green(2, "green"),
        DarkBlue(3, "darkblue");

        private final int metadata;
        private final String name;
        private final String unlocalizedName;

        private TileType(int metadata, String name)
        {
            this(metadata, name, name);
        }

        private TileType(int metadata, String name, String unlocalizedName)
        {
            this.metadata = metadata;
            this.name = name;
            this.unlocalizedName = unlocalizedName;
        }

        public int getMetadata()
        {
            return this.metadata;
        }

        public String getUnlocalizedName()
        {
            return this.unlocalizedName;
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
        	case 3: return DarkBlue;
        	}
        	
        	return Green;
        }

        public String getName()
        {
            return this.name;
        }
    }


	
}
