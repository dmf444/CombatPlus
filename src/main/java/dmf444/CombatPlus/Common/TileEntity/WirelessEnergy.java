package dmf444.CombatPlus.Common.TileEntity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.TileEnergyHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.ForgeDirection;
import openmodularturrets.tileentity.turretbase.TurretBase;

import java.util.Iterator;
import java.util.Map;


public class WirelessEnergy extends TileEnergyHandler implements ITickTile {

    Chunk chuck;
    private int ticks;

    public WirelessEnergy() {
        this.storage = new EnergyStorage(50000, 1500, 1000);
    }

    @Override
    public void updateEntity() {
        ticks++;
        chuck = this.getWorldObj().getChunkFromBlockCoords(this.xCoord, this.zCoord);
        Map maz = chuck.chunkTileEntityMap;
        Iterator iterator = maz.values().iterator();

        while (iterator.hasNext()) {
            TileEntity tileEntity = (TileEntity) iterator.next();
            if (tileEntity instanceof TurretBase) {
                if (tileEntity instanceof IEnergyHandler) {
                    IEnergyHandler handler = (IEnergyHandler) tileEntity;
                    if (handler.getEnergyStored(ForgeDirection.UNKNOWN) <= handler.getMaxEnergyStored(ForgeDirection.UNKNOWN) && this.getEnergyStored(ForgeDirection.UNKNOWN) > 0) {
                        if(this.storage.getEnergyStored() - this.storage.getMaxExtract() >= 0) {
                            handler.receiveEnergy(ForgeDirection.UP, this.storage.getMaxExtract(), false);
                            this.extractEnergy(ForgeDirection.UNKNOWN, this.storage.getMaxExtract(), false);
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
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        if(from == ForgeDirection.DOWN) {
            return storage.receiveEnergy(maxReceive, simulate);
        } else {return 0;}
    }
}