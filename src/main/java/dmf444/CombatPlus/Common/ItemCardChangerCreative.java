package dmf444.CombatPlus.Common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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

import java.util.Collection;
import java.util.Iterator;


public class ItemCardChangerCreative extends Item {

    public ItemCardChangerCreative(){
        this.setUnlocalizedName("CreativeChangerCard");
        this.setCreativeTab(CreativeTabs.tabRedstone);
        this.setMaxStackSize(1);
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
}
