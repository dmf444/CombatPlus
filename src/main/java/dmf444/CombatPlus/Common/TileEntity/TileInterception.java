package dmf444.CombatPlus.Common.TileEntity;

import dmf444.CombatPlus.Common.blocks.BlockHackInterceptor;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class TileInterception extends TileEntity implements ITickable{

    Chunk chuck;
    private String owner;
    private int ticks = 0;

    public TileInterception() {

    }

    public void setOwner(String player){
        if(getWorld().getPlayerEntityByName(player) != null)
            owner = getWorld().getPlayerEntityByName(player).getUniqueID().toString();
    }

    public String getOwner(){
        return owner;
    }


    @Override
    public void update(){
        ticks++;

        if(ticks > 50) {
            ticks = 0;
            chuck = this.getWorld().getChunkFromBlockCoords(this.getPos());
            Map maz = chuck.getTileEntityMap();
            Iterator iterator = maz.values().iterator();

            while (iterator.hasNext()) {
                TileEntity tileEntity = (TileEntity) iterator.next();
                if (tileEntity instanceof TileSetTurretBase) {
                    TileSetTurretBase handler = (TileSetTurretBase) tileEntity;
                    boolean isIntercepting = getWorld().getBlockState(getPos()).getValue(BlockHackInterceptor.INTERCEPTION);
                    boolean isExplosive = getWorld().getBlockState(getPos()).getValue(BlockHackInterceptor.EXPLOSIVE);
                    if(handler.isHacking()){
                        if (!isIntercepting) {
                            if(getWorld().isRemote) {
                                TextComponentTranslation msg = new TextComponentTranslation(
                                    "WARNING: Wireless hacker found at x: %s, y: %s, z: %s",
                                    handler.getPos().getX(), handler.getPos().getY(),
                                    handler.getPos().getZ());
                                getWorld().getPlayerEntityByUUID(UUID.fromString(getOwner()))
                                    .addChatComponentMessage(msg);
                            }
                        } else if (isIntercepting && !isExplosive) {
                            handler.stopHacking();
                        } else if (isExplosive) {
                            handler.explodeDevice();
                            break;
                        }
                    }

                }
            }
        }
    }
    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbtTag = this.writeToNBT(new NBTTagCompound());
        return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        this.readFromNBT(packet.getNbtCompound());
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound = super.writeToNBT(compound);
        if (owner.equals("") || owner == null) {
            compound.setString("owner", owner);
        }
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        if(compound.hasKey("owner"))
            owner = compound.getString("owner");
    }
}
