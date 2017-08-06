package dmf444.CombatPlus.Common.TileEntity;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.chunk.Chunk;


public class TileInterception extends TileEntity implements ITickable{

    Chunk chuck;
    private String owner;
    private boolean intercept;
    private boolean explodeHacker;

    public TileInterception() {

    }

    public void setOwner(String player){
        if(getWorld().getPlayerEntityByName(player) != null)
            owner = getWorld().getPlayerEntityByName(player).getUniqueID().toString();
    }

    public String getOwner(){
        return owner;
    }

    public void setIntercept(boolean interceptz) {
        intercept = interceptz;
    }

    public boolean getIntercept() {
        return intercept;
    }

    public boolean getExposions() {
        return explodeHacker;
    }

    @Override
    public void update(){
        chuck = this.getWorld().getChunkFromBlockCoords(this.getPos());
        Map maz = chuck.getTileEntityMap();
        Iterator iterator = maz.values().iterator();

        while(iterator.hasNext()) {
            TileEntity tileEntity = (TileEntity) iterator.next();
            if(tileEntity instanceof TileSetTurretBase){
                TileSetTurretBase handler = (TileSetTurretBase) tileEntity;
                if(handler.isHacking() && !this.intercept) {
                    new TextComponentTranslation("WARNING: %s, Wireless hacker found at x:" + handler.getPos().getX() + " y: " + handler.getPos().getY() + " z: " + handler.getPos().getZ(), getWorld().getPlayerEntityByUUID(UUID.fromString(getOwner())));
                }else if(handler.isHacking() && this.intercept){
                    handler.stopHacking();
                } else if(handler.isHacking() && this.explodeHacker){
                    handler.explodeDevice();
                }
            }
        }
    }


    public void allowExplosions() {
        explodeHacker = true;
    }
}
