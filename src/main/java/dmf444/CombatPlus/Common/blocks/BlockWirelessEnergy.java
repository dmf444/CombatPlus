package dmf444.CombatPlus.Common.blocks;

import dmf444.CombatPlus.Common.TileEntity.TileInfiniteEnergy;
import dmf444.CombatPlus.Common.TileEntity.WirelessEnergy;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;


public class BlockWirelessEnergy extends BlockContainer {

    public BlockWirelessEnergy() {
        super(Material.iron);

        this.setCreativeTab(CreativeTabs.tabRedstone);
    }


    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new WirelessEnergy();
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

    private void setDefaultDirection(World world, int x, int y, int z, EntityLivingBase entity) {
        int rotation = MathHelper.floor_double((double) (entity.rotationYaw * 4F / 360F) + 0.5D) & 3;

        if(rotation == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if(rotation == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if(rotation == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if(rotation == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack Itemstack) {

        super.onBlockAdded(world, x, y, z);
        this.setDefaultDirection(world, x, y, z, entity);

    }

    public void registerBlockIcons(IIconRegister icon) {
        this.blockIcon = icon.registerIcon("combatplus:WirelessEnergyItem");
    }
}