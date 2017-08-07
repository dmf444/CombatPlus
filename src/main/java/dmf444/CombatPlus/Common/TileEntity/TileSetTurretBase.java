package dmf444.CombatPlus.Common.TileEntity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.TileEnergyHandler;
import dmf444.CombatPlus.Core.lib.CPLog;
import dmf444.CombatPlus.init.ItemRegistry;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import omtteam.openmodularturrets.tileentity.TurretBase;


public class TileSetTurretBase extends TileEnergyHandler implements ITickable {

    private int ticks;
    private boolean hack;
    private int totalComplete;
    private ItemStack cacheItem;


    public TileSetTurretBase(){
        hack = false;
        this.storage = new EnergyStorage(40000);
    }

    public void update(){
        if (hack && this.storage.getEnergyStored() >= 1500) {
            if(!getWorld().isRemote) {
                this.ticks++;
                this.extractEnergy(EnumFacing.DOWN, 1500, false);
                CPLog.fatal(ticks);
                if (ticks >= 50) {
                    totalComplete += 1;
                    ticks = 0;

                    if (totalComplete >= 5) {
                        this.hack = false;
                        this.totalComplete = 0;
                        this.ticks = 0;
                        addTeamtoAccepted(cacheItem);
                    }
                }
            }
            getWorld().notifyBlockUpdate(getPos(), getWorld().getBlockState(getPos()), getWorld().getBlockState(getPos()), 3);
        }
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        NBTTagCompound syncData = new NBTTagCompound();
        syncData.setInteger("ticks", this.getTicks());
        syncData.setBoolean("isHacking", this.isHacking());
        syncData.setInteger("complete", this.totalComplete);
        return new SPacketUpdateTileEntity(this.getPos(), 1, syncData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onDataPacket(net.minecraft.network.NetworkManager net, net.minecraft.network.play.server.SPacketUpdateTileEntity pkt)
    {
        this.ticks = pkt.getNbtCompound().getInteger("ticks");
        this.totalComplete = pkt.getNbtCompound().getInteger("complete");
        this.hack = pkt.getNbtCompound().getBoolean("isHacking");
    }


    @Override
    public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
        if (from == EnumFacing.DOWN)
            return storage.receiveEnergy(maxReceive, simulate);
        else
            return -1;
    }
    public void cacheStack(ItemStack stack){
        cacheItem = stack;
    }
    public void startHacking(){
        hack = true;
    }
    public boolean isHacking(){return hack;}

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
        tile.setOwner(getWorld().getPlayerEntityByName(name).getUniqueID().toString());
    }

    private void removeTrustedPlayers(TurretBase tile){
        for(int d = 0; d < tile.getTrustedPlayers().size(); d++){
            tile.getTrustedPlayers().get(d).setAdmin(false);
            tile.getTrustedPlayers().get(d).setCanChangeTargeting(false);
            tile.getTrustedPlayers().get(d).setCanOpenGUI(false);
            tile.removeTrustedPlayer(tile.getTrustedPlayers().get(d).getName());
        }
    }

    public void stopHacking() {
        hack = false;
    }

    public void explodeDevice() {
        getWorld().createExplosion(getWorld().getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 100, true), this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 1.0F, true);
    }
    public int getTicks(){
        return ticks;
    }
    public int getTotalComplete() {return this.totalComplete;}
}
