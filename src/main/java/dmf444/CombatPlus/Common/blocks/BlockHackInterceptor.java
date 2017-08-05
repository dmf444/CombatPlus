package dmf444.CombatPlus.Common.blocks;

import dmf444.CombatPlus.Common.TileEntity.TileInterception;
import dmf444.CombatPlus.CombatPlus;
import dmf444.CombatPlus.init.ItemRegistry;
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


public class BlockHackInterceptor extends BasicBlock {


    public BlockHackInterceptor() {
        super(Material.CIRCUITS);
        this.setName("interceptor");
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.blockHardness = 2.0F;
    }

    @Override
    public TileEntity createNewTileEntity(World wolrd, int meta) {
        return new TileInterception();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
        if(player.getHeldItem(hand) != null) {
            if (player.getHeldItem(hand).getItem().equals(ItemRegistry.HACKING_CARD) && !TileInterception.getIntercept() && !TileInterception.getExposions()) {
                TileInterception.setIntercept(true);
                return true;
            } else if (player.getHeldItem(hand).getItem().equals(ItemRegistry.EXPLOSIVE_CARD) && TileInterception.getIntercept() && !TileInterception.getExposions()) {
                TileInterception.allowExplosions();
                return true;
            }
        }
        return false;
    }


}
