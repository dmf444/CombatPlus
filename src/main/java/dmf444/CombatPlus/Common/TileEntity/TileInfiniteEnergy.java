package dmf444.CombatPlus.Common.TileEntity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.TileEnergyHandler;
import dmf444.CombatPlus.Core.lib.CPLog;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import omtteam.openmodularturrets.tileentity.TurretBase;


import java.util.Iterator;
import java.util.Map;


public class TileInfiniteEnergy extends TileEntity implements ITickTile, ITickable{

    Chunk chuck;
    private int ticks;

    public TileInfiniteEnergy() {

    }

    @Override
    public void update(){
        ticks++;
        chuck = this.getWorld().getChunkFromBlockCoords(this.getPos());
        Map maz = chuck.getTileEntityMap();
        Iterator iterator = maz.values().iterator();

        while(iterator.hasNext()) {
            TileEntity tileEntity = (TileEntity) iterator.next();
            if(tileEntity instanceof TurretBase){
                if(tileEntity instanceof IEnergyHandler && tileEntity instanceof IEnergyReceiver){
                    IEnergyHandler handler = (IEnergyHandler) tileEntity;
                    IEnergyReceiver hand = (IEnergyReceiver) tileEntity;
                    if(handler.getEnergyStored(EnumFacing.DOWN) <= handler.getMaxEnergyStored(EnumFacing.DOWN)) {
                        hand.receiveEnergy(EnumFacing.UP, 10000, false);
                    }
                }
            }
        }
    }


    @Override
    public int getTicks() {
        return ticks;
    }

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation("combatplus:textures/blocks/CreativeWirelessEnergy.png");
    }
}
