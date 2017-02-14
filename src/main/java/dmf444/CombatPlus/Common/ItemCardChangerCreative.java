package dmf444.CombatPlus.Common;


import dmf444.CombatPlus.Core.CombatPlus;
import dmf444.CombatPlus.Core.lib.CPLog;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class ItemCardChangerCreative extends Item {

    public ItemCardChangerCreative(){
        this.setUnlocalizedName("CreativeChangerCard");
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.setMaxStackSize(1);
        //this.setTextureName("combatplus:creativeHackCard");
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int number, boolean yn) {
        Scoreboard score = this.getBoard(world);
        if(score != null) {
            Collection teams = score.getTeams();
            if (teams.size() == 2) {
                EntityPlayer p = (EntityPlayer) entity;
                if (p.getTeam() != null) {
                    String t = p.getTeam().getRegisteredName();
                    this.loopString(score.getTeam(t).getMembershipCollection(), stack);
                }
            }
        }

    }

    private void loopString(Collection names, ItemStack itemStack){
        if(!itemStack.hasTagCompound()){
            itemStack.setTagCompound(new NBTTagCompound());
        }
        String carlos = "";
        Iterator i = names.iterator();
        while(i.hasNext()){
            carlos += i.next() + "~";
        }
        if(carlos != null) {
            NBTTagCompound tag = itemStack.getTagCompound();
            tag.setString("names", carlos);
            CPLog.error(carlos);
        }
    }

    protected Scoreboard getBoard(World world)
    {
        return world.getScoreboard();
    }


    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean yn) {
        if(player.isInvisible()) {
            list.add("Only works when two teams are registered in the scoreboard,");
            list.add("and the player using the card is on one of those teams");
            list.add("This will set the entire team to access the turrets");
        } else{
            list.add("Become invisible for more info");
        }
    }
}
