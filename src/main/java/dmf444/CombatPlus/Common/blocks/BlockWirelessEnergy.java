package dmf444.CombatPlus.Common.blocks;

import dmf444.CombatPlus.Common.TileEntity.TileInfiniteEnergy;
import dmf444.CombatPlus.Common.TileEntity.WirelessEnergy;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class BlockWirelessEnergy extends BasicBlock {

    public BlockWirelessEnergy() {
        super(Material.IRON);
        this.setName("WirelessCharger");
        this.blockHardness = 2.0F;
        this.setCreativeTab(CreativeTabs.REDSTONE);
    }


    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new WirelessEnergy();
    }

}