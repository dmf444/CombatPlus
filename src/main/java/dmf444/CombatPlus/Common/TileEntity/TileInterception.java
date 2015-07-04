package dmf444.CombatPlus.Common.TileEntity;

import cofh.api.energy.IEnergyHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.ForgeDirection;
import openmodularturrets.tileentity.turretbase.TurretBase;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class TileInterception extends TileEntity {

    Chunk chuck;
    private static EntityPlayer owner;
    private static World world;
    private static boolean intercept;
    private static boolean explodeHacker;

    public TileInterception() {
        world = this.getWorldObj();
    }

    public static void setOwner(String player){
        List players = world.playerEntities;
        for(int i = 0; i <= players.size(); i++){
            EntityPlayer player1 = (EntityPlayer) players.get(i);
            if(player1.getCommandSenderName() == player){
                owner = player1;
            }
        }
    }

    public static EntityPlayer getOwner(){
        return owner;
    }

    public static void setIntercept(boolean interceptz) {
        intercept = interceptz;
    }

    public static boolean getIntercept() {
        return intercept;
    }

    public static boolean getExposions() {
        return explodeHacker;
    }

    @Override
    public void updateEntity(){
        chuck = this.getWorldObj().getChunkFromBlockCoords(this.xCoord, this.zCoord);
        Map maz = chuck.chunkTileEntityMap;
        Iterator iterator = maz.values().iterator();

        while(iterator.hasNext()) {
            TileEntity tileEntity = (TileEntity) iterator.next();
            if(tileEntity instanceof TurretBase){
                if(tileEntity instanceof TileSetTurretBase){
                    TileSetTurretBase handler = (TileSetTurretBase) tileEntity;
                    if(handler.getHack() && !this.intercept) {
                        new ChatComponentTranslation("WARNING: %s, Wireless hacker found at x:" + handler.xCoord + " y: " + handler.yCoord + " z: " + handler.zCoord, new Object[] {getOwner().func_145748_c_()});
                    }else if(handler.getHack() && this.intercept){
                        handler.stopHack();
                    } else if(handler.getHack() && this.explodeHacker){
                        handler.EXPLODE(owner);
                    }
                }
            }
        }
    }


    public static void allowExplosions() {
        explodeHacker = true;
    }
}
