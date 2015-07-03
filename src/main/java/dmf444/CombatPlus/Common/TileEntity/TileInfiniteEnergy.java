package dmf444.CombatPlus.Common.TileEntity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.TileEnergyHandler;
import dmf444.CombatPlus.Core.lib.CPLog;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.ForgeDirection;
import openmodularturrets.tileentity.turretbase.TurretBase;

import java.util.Iterator;
import java.util.Map;


public class TileInfiniteEnergy extends TileEntity{

    Chunk chuck;

    public TileInfiniteEnergy() {

    }

    @Override
    public void updateEntity(){
        chuck = this.getWorldObj().getChunkFromBlockCoords(this.xCoord, this.zCoord);
        Map maz = chuck.chunkTileEntityMap;
        Iterator iterator = maz.values().iterator();

        while(iterator.hasNext()) {
            TileEntity tileEntity = (TileEntity) iterator.next();
            if(tileEntity instanceof TurretBase){
                if(tileEntity instanceof IEnergyHandler){
                    IEnergyHandler handler = (IEnergyHandler) tileEntity;
                    if(handler.getEnergyStored(ForgeDirection.UNKNOWN) <= handler.getMaxEnergyStored(ForgeDirection.UNKNOWN)) {
                        handler.receiveEnergy(ForgeDirection.UP, 10000, false);
                    }
                }
            }
        }
    }


}
