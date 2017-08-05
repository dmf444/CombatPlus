package dmf444.CombatPlus.Common.TileEntity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.TileEnergyHandler;
import dmf444.CombatPlus.CombatPlus;
import dmf444.CombatPlus.Core.lib.CPLog;
import dmf444.CombatPlus.init.ItemRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.chunk.Chunk;
import omtteam.openmodularturrets.tileentity.TurretBase;


import java.util.Iterator;
import java.util.Map;


public class TileSetTurretBase extends TileEnergyHandler implements ITickable {

    private int Ticks;
    private static boolean Hack;
    private int totalComplete;
    private static ItemStack cacheItem;


    public TileSetTurretBase(){
        Hack = false;
        this.storage = new EnergyStorage(40000);
    }

    public void update(){
        if(Hack && this.storage.getEnergyStored() >= 1500){

            this.Ticks++;
            this.extractEnergy(EnumFacing.DOWN, 1500, false);
            CPLog.fatal(Ticks);
            if(Ticks >= 50){
                totalComplete += 1;
                Ticks = 0;

                if(totalComplete >= 5){
                    this.Hack = false;
                    this.totalComplete = 0;
                    this.Ticks = 0;
                    addTeamtoAccepted(cacheItem);
                }
            }
        }
    }

    @Override
    public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
        if (from == EnumFacing.DOWN)
            return storage.receiveEnergy(maxReceive, simulate);
        else
            return -1;
    }
    public static void cacheStack(ItemStack stack){
        cacheItem = stack;
    }
    public static void startHack(){
        Hack = true;
    }
    public static boolean getHack(){return Hack;}

    public void addTeamtoAccepted(ItemStack items){
        if((items.getItem()).equals(ItemRegistry.TEAM_HACKING_CARD)) {
            if (items.hasTagCompound()) {
                NBTTagCompound tag = items.getTagCompound();
                String names = tag.getString("names");
                String[] name = names.split("~");
                Chunk chuck = this.getWorld().getChunkFromBlockCoords(this.getPos());
                Map maz = chuck.getTileEntityMap();
                Iterator iterator = maz.values().iterator();
                while (iterator.hasNext()) {
                    //CPLog.error("YUP");
                    TileEntity tileEntity = (TileEntity) iterator.next();
                    if (tileEntity instanceof TurretBase) {
                        //CPLog.fatal("X: "+ tileEntity.xCoord + " Y: "+ tileEntity.yCoord + " Z: " + tileEntity.zCoord);
                        this.removeTrustedPlayers((TurretBase) tileEntity);
                        for (int i = 0; i <= name.length - 1; i++) {
                           this.reloadTrustedPlayers((TurretBase) tileEntity, name[i]);
                        }
                    }

                }
            }
        } else if(items.getItem().equals(ItemRegistry.HACKING_CARD)){
            NBTTagCompound tag = items.getTagCompound();
            String name = tag.getString("names");
            Chunk chuck = this.getWorld().getChunkFromBlockCoords(this.getPos());
            Map maz = chuck.getTileEntityMap();
            Iterator iterator = maz.values().iterator();
            while (iterator.hasNext()) {
                TileEntity tileEntity = (TileEntity) iterator.next();
                if (tileEntity instanceof TurretBase) {
                    this.removeTrustedPlayers((TurretBase) tileEntity);
                    this.reloadTrustedPlayers((TurretBase) tileEntity, name);
                }
            }

        }
    }

    private void reloadTrustedPlayers(TurretBase tile, String name){
        tile.addTrustedPlayer(name);
        tile.getTrustedPlayer(name).setAdmin(true);
        tile.getTrustedPlayer(name).setCanChangeTargeting(true);
        tile.getTrustedPlayer(name).setCanOpenGUI(true);
    }

    private void removeTrustedPlayers(TurretBase tile){
        for(int d = 0; d < tile.getTrustedPlayers().size(); d++){
            tile.getTrustedPlayers().get(d).setAdmin(false);
            tile.getTrustedPlayers().get(d).setCanChangeTargeting(false);
            tile.getTrustedPlayers().get(d).setCanOpenGUI(false);
            tile.removeTrustedPlayer(tile.getTrustedPlayers().get(d).getName());
        }
    }

    public void stopHack() {
        Hack = false;
    }

    public void EXPLODE(EntityPlayer player) {
        worldObj.createExplosion(player, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 2.0F, true);
    }
    public int getTicks(){
        return Ticks;
    }
}
