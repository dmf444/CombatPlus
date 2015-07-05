package dmf444.CombatPlus.Common.blocks;

import dmf444.CombatPlus.Common.TileEntity.TileInfiniteEnergy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class BlockInfiniteEnergy extends BlockContainer {

    public BlockInfiniteEnergy() {
        super(Material.iron);

        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

   /* public TileEntity createTileEntity(World world, int metadata){
        return new TileInfiniteEnergy();
    }*/

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileInfiniteEnergy();
    }


    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public void registerBlockIcons(IIconRegister icon) {
        this.blockIcon = icon.registerIcon("combatplus:CreativeWirelessEnergyItem");
    }
}
