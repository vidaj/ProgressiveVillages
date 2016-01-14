package com.progressivevillages.common.blocks;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.progressivevillages.common.blocks.decorative.ByzantineTile.TileType;

public abstract class AbstractMetaBlock<T extends Enum<T> & IVariantSpecification> extends AbstractBlock implements IMetaBlockName {

	private final Map<Integer, T> metaToVariantMap = new HashMap<Integer, T>();
	
	public AbstractMetaBlock(Material material) {
		super(Material.rock);
		this.setDefaultState(this.blockState.getBaseState().withProperty(getVariantProperty(), getDefaultVariant()));
        this.setCreativeTab(CreativeTabs.tabBlock);
        
        mapVariantsToMeta();
        
        GameRegistry.registerBlock(this, ItemBlockMeta.class);
	}

	private void mapVariantsToMeta() {
		for (T type : EnumSet.allOf(getVariantProperty().getValueClass())) {
        	metaToVariantMap.put(Integer.valueOf(type.getMetadata()), type);
        }
	}
	
	protected abstract PropertyEnum<T> getVariantProperty();
	
	protected abstract T getDefaultVariant();
	
	@Override
	@SideOnly(Side.CLIENT)
	public void initModel() {
		Item item = Item.getItemFromBlock(this);
		String registryName = getRegistryName();
		
		for (T type : EnumSet.allOf(getVariantProperty().getValueClass())) {
			StringBuilder variantNameBuilder = new StringBuilder();
			variantNameBuilder.append(getVariantProperty().getName());
			variantNameBuilder.append("=");
			variantNameBuilder.append(type.getName());
			ModelLoader.setCustomModelResourceLocation(item, type.getMetadata(), new ModelResourceLocation(registryName, variantNameBuilder.toString()));
		}
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
        return iblockstate.getValue(getVariantProperty()).getMetadata();
    }
	
	@Override
	public IBlockState getStateFromMeta(int meta)
    {
		T byMetadata = getByMetadata(meta);
		return this.getDefaultState().withProperty(getVariantProperty(), byMetadata);
    }

	@Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(getVariantProperty()).getMetadata();
    }

	@Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {getVariantProperty() });
    }
	
	@Override
	public int damageDropped(IBlockState state)
    {
		IVariantSpecification tileType = state.getValue(getVariantProperty());
        
        return tileType.getMetadata();
    }
	
	@Override
	public String getSpecialName(ItemStack stack) {
		T type = getByMetadata(stack.getItemDamage());
		if (type == null) {
			return "";
		}
		
		return type.getName();
	}
	
	public T getByMetadata(int meta) {
		return metaToVariantMap.get(Integer.valueOf(meta));
	}
}
