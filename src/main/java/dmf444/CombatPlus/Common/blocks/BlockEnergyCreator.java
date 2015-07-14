package dmf444.CombatPlus.Common.blocks;

import dmf444.CombatPlus.Common.TileEntity.TileEnergyCreator;
import dmf444.CombatPlus.Common.TileEntity.TileSetTurretBase;
import dmf444.CombatPlus.Core.CombatPlus;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class BlockEnergyCreator extends BlockContainer{


    public BlockEnergyCreator() {
        super(Material.circuits);
        this.setCreativeTab(CreativeTabs.tabRedstone);
        this.blockHardness = 2.0F;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEnergyCreator();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitx, float hity, float hitz) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity == null || player.isSneaking())
            return false;
        else
            player.openGui(CombatPlus.instance, 1, world, x, y, z);
        return true;
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
        this.blockIcon = icon.registerIcon("combatplus:RedstoneHeaterItem");
    }

}
