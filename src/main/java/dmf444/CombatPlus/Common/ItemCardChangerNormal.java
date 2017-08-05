package dmf444.CombatPlus.Common;

import dmf444.CombatPlus.Core.lib.CPLog;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;


public class ItemCardChangerNormal extends Item {

    public ItemCardChangerNormal() {
        this.setUnlocalizedName("NormalChangerCard");
        this.setRegistryName("hackyCardNormal");
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.setMaxStackSize(1);
        //this.setTextureName("combatplus:normalHackCard");
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int number, boolean yn) {
        //CPLog.fatal(entity.getCommandSenderName());
        this.loopString(entity.getName(), stack);
    }

    private void loopString(String name, ItemStack itemStack) {
        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }
        if (name != null) {
            NBTTagCompound tag = itemStack.getTagCompound();
            tag.setString("names", name);
            //CPLog.error(carlos);
        }
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        if(stack.hasTagCompound()){
            String name = stack.getTagCompound().getString("names");
            tooltip.add("Current Owner: " + name);
        }
    }

}