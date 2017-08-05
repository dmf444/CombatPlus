package dmf444.CombatPlus.Common.blocks;

import dmf444.CombatPlus.Common.TileEntity.TileInfiniteEnergy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class BlockInfiniteEnergy extends BasicBlock {

    public BlockInfiniteEnergy() {
        super(Material.IRON);
        this.setName("CreativeWirelessCharger");
        this.blockHardness = 2.0F;
        this.setCreativeTab(CreativeTabs.REDSTONE);
    }


    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileInfiniteEnergy();
    }

}
