package dmf444.CombatPlus.Common.blocks;

import dmf444.CombatPlus.Common.TileEntity.TileEnergyCreator;
import dmf444.CombatPlus.CombatPlus;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;


public class BlockEnergyCreator extends BasicBlock{


    public BlockEnergyCreator() {
        super(Material.CIRCUITS);
        this.setName("EnergyCreator");
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.blockHardness = 2.0F;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEnergyCreator();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity == null || player.isSneaking())
            return false;
        else
            player.openGui(CombatPlus.instance, 1, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }


}
