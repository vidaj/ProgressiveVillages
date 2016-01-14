package com.progressivevillages.common.blocks.decorative;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;

import com.progressivevillages.common.blocks.AbstractMetaBlock;
import com.progressivevillages.common.blocks.IVariantSpecification;

public class ByzantineTile extends AbstractMetaBlock<ByzantineTile.TileType>  {
	
	public static final String Name = "byzantinetile";
	
	public static PropertyEnum<TileType> VARIANT = PropertyEnum.<TileType>create("variant", TileType.class);

	public ByzantineTile() {
		super(Material.rock);
	}
	
	@Override
	protected PropertyEnum<TileType> getVariantProperty() {
		return VARIANT;
	}

	@Override
	protected TileType getDefaultVariant() {
		return TileType.Gold;
	}
	
	@Override
	protected String getName() {
		return Name;
	}

	public static enum TileType implements IVariantSpecification
    {
        Gold(0, "gold"),
        Blue(1, "blue"),
        Green(2, "green"),
        DarkBlue(3, "darkblue");

        private final int metadata;
        private final String name;

        private TileType(int metadata, String name)
        {
            this.metadata = metadata;
            this.name = name;
        }

        public int getMetadata()
        {
            return this.metadata;
        }

        public String toString()
        {
            return this.name;
        }

        public String getName()
        {
            return this.name;
        }
    }

	
}
