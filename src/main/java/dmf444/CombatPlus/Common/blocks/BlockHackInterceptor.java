package dmf444.CombatPlus.Common.blocks;

import dmf444.CombatPlus.Common.TileEntity.TileInterception;
import dmf444.CombatPlus.Core.CombatPlus;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class BlockHackInterceptor extends BlockContainer {


    protected BlockHackInterceptor() {
        super(Material.circuits);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    @Override
    public TileEntity createNewTileEntity(World wolrd, int meta) {
        return new TileInterception();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitx, float hity, float hitz) {
        if(player.getCurrentEquippedItem().getItem().equals(CombatPlus.upgradeCard)){
            TileInterception.setIntercept(true);
            return true;
        } else if(player.getCurrentEquippedItem().getItem().equals(CombatPlus.explodeCard)){
            TileInterception.allowExplosions();
        }
        return false;
    }
}
