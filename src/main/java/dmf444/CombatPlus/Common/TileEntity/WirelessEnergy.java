package dmf444.CombatPlus.Common.TileEntity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.TileEnergyHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import omtteam.openmodularturrets.tileentity.TurretBase;

import java.util.Iterator;
import java.util.Map;


public class WirelessEnergy extends TileEnergyHandler implements ITickTile, ITickable {

    Chunk chuck;
    private int ticks;

    public WirelessEnergy() {
        this.storage = new EnergyStorage(50000, 1500, 1000);
    }

    @Override
    public void update() {
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
                    if (handler.getEnergyStored(EnumFacing.DOWN) <= handler.getMaxEnergyStored(EnumFacing.DOWN) && this.getEnergyStored(EnumFacing.DOWN) > 0) {
                        if(this.storage.getEnergyStored() - this.storage.getMaxExtract() >= 0) {
                            hand.receiveEnergy(EnumFacing.UP, this.storage.getMaxExtract(), false);
                            this.extractEnergy(EnumFacing.DOWN, this.storage.getMaxExtract(), false);
                        }
                    }
                }
            }
        }
    }

    public int getTicks(){
        return ticks;
    }

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation("combatplus:textures/blocks/WirelessEnergy.png");
    }

    @Override
    public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
        if(from == EnumFacing.DOWN) {
            return storage.receiveEnergy(maxReceive, simulate);
        } else {return 0;}
    }
}