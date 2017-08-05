package dmf444.CombatPlus.Common.blocks;

import dmf444.CombatPlus.Common.TileEntity.TileSetTurretBase;
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

public class WirelessHacker extends BasicBlock {

    public WirelessHacker(){
        super(Material.IRON);
        this.setName("TurretHacker");
        this.setCreativeTab(CreativeTabs.REDSTONE);
        //this.setBlockBounds(0.2f, 0.0f, 0.2f, 0.8f, 0.65f, 0.82f);
        this.blockHardness = 2.0F;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileSetTurretBase();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
        if(player.getHeldItem(hand) != null) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof TileSetTurretBase && player.getHeldItem(hand).getItem().equals(ItemRegistry.TEAM_HACKING_CARD)) {
                TileSetTurretBase TE = (TileSetTurretBase) tile;
                TE.addTeamtoAccepted(player.getHeldItem(hand));
                return true;
            } else if(tile instanceof TileSetTurretBase && player.getHeldItem(hand).getItem().equals(ItemRegistry.HACKING_CARD)){
                TileSetTurretBase TE = (TileSetTurretBase) tile;
                TE.cacheStack(player.getHeldItem(hand));
                TE.startHack();
            }
        }
        return false;
    }


}
