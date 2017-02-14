package dmf444.CombatPlus.Common.TileEntity;

import cofh.api.energy.IEnergyHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import omtteam.openmodularturrets.tileentity.TurretBase;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class TileInterception extends TileEntity implements ITickable{

    Chunk chuck;
    private static EntityPlayer owner;
    private static World world;
    private static boolean intercept;
    private static boolean explodeHacker;

    public TileInterception() {
        world = this.getWorld();
    }

    public static void setOwner(String player){
        List players = world.playerEntities;
        for(int i = 0; i <= players.size(); i++){
            EntityPlayer player1 = (EntityPlayer) players.get(i);
            if(player1.getName() == player){
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
    public void update(){
        chuck = this.getWorld().getChunkFromBlockCoords(this.getPos());
        Map maz = chuck.getTileEntityMap();
        Iterator iterator = maz.values().iterator();

        while(iterator.hasNext()) {
            TileEntity tileEntity = (TileEntity) iterator.next();
            if(tileEntity instanceof TurretBase){
                if(tileEntity instanceof TileSetTurretBase){
                    TileSetTurretBase handler = (TileSetTurretBase) tileEntity;
                    if(handler.getHack() && !this.intercept) {
                        new TextComponentTranslation("WARNING: %s, Wireless hacker found at x:" + handler.getPos().getX() + " y: " + handler.getPos().getY() + " z: " + handler.getPos().getZ(), new Object[] {getOwner().getDisplayNameString()});
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
