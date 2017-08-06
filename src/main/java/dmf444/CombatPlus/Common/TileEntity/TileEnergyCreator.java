package dmf444.CombatPlus.Common.TileEntity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.TileEnergyHandler;
import dmf444.CombatPlus.Core.ConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class TileEnergyCreator extends TileEnergyHandler implements ISidedInventory, ITickable{

    private static final int EMPTY = 0;
    private static final int REDSTONE = 1;
    private static final int BLOCK = 2;

    private static final int ENERGY_CREATED = ConfigHandler.RFperT;
    private static final Double BLOCK_MULTIPLIER = ConfigHandler.Multiplyer;
    private static final int TICKS_TO_WAIT = ConfigHandler.tickWait;

    private int ticks = 0;

    public TileEnergyCreator() {
        this.storage = new EnergyStorage(50000);
        this.inv = new ItemStack[3];
    }


    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);

        NBTTagList tagList = tagCompound.getTagList("Inventory", 10);
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < inv.length) {
                inv[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);

        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < inv.length; i++) {
            ItemStack stack = inv[i];
            if (stack != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag(tag);
            }
        }
        tagCompound.setTag("Inventory", itemList);
        return tagCompound;
    }

    @Override
    public void update() {
        ticks++;

        if(ticks == TICKS_TO_WAIT) {
            if (getWorld().getBlockState(this.getPos().down()).getBlock() == Blocks.LAVA && this.storage.getEnergyStored() < 50000) {
                int Type = testItems(this.inv[0]);
                if (Type != EMPTY) {
                    if (Type == REDSTONE) {
                        this.decrStackSize(0, 1);
                        storage.receiveEnergy(ENERGY_CREATED, false);
                    } else if (Type == BLOCK) {
                        this.decrStackSize(0, 1);
                        storage.receiveEnergy((int) (ENERGY_CREATED * BLOCK_MULTIPLIER), false);
                    }
                } else if (testItems(this.inv[1]) != EMPTY) {
                    Type = testItems(this.inv[1]);
                    if (Type == REDSTONE) {
                        this.decrStackSize(1, 1);
                        storage.receiveEnergy(ENERGY_CREATED, false);
                    } else if (Type == BLOCK) {
                        this.decrStackSize(1, 1);
                        storage.receiveEnergy((int) (ENERGY_CREATED * BLOCK_MULTIPLIER), false);
                    }
                } else if (testItems(this.inv[2]) != EMPTY) {
                    Type = testItems(this.inv[2]);
                    if (Type == REDSTONE) {
                        this.decrStackSize(2, 1);
                        storage.receiveEnergy(ENERGY_CREATED, false);
                    } else if (Type == BLOCK) {
                        this.decrStackSize(2, 1);
                        storage.receiveEnergy((int) (ENERGY_CREATED * BLOCK_MULTIPLIER), false);
                    }
                }
            }
            ticks = 0;
        }
        if(getWorld().getTileEntity(pos.up()) instanceof IEnergyReceiver && this.storage.getEnergyStored() > 1500){
            IEnergyReceiver handler = (IEnergyReceiver) getWorld().getTileEntity(getPos().up());
            handler.receiveEnergy(EnumFacing.DOWN, 1500, false);
            this.storage.extractEnergy(1500, false);
        }
    }


    private int testItems(ItemStack itemstack) {
        if (itemstack != null) {
            Item item = itemstack.getItem();
            if (item.equals(Items.REDSTONE)) {
                return REDSTONE;
            } else if (item.equals(Item.getItemFromBlock(Blocks.REDSTONE_BLOCK))) {
                return BLOCK;
            } else {
                return EMPTY;
            }
        }
        return EMPTY;
    }

    @Override
    public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
        return 0;
    }
    @Override
    public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
        if(from == EnumFacing.UP)
            return storage.extractEnergy(maxExtract, simulate);
        else
            return 0;
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        NBTTagCompound syncData = new NBTTagCompound();
        syncData.setInteger("Storage", storage.getEnergyStored());
        return new SPacketUpdateTileEntity(this.getPos(), 1, syncData);
    }

    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onDataPacket(net.minecraft.network.NetworkManager net, net.minecraft.network.play.server.SPacketUpdateTileEntity pkt)
    {
        storage.setEnergyStored(pkt.getNbtCompound().getInteger("Storage"));
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //**************************************************************************************************************
    //*****
    //*****                                    OTHER STUFF STARTS HERE!
    //*****
    //**************************************************************************************************************
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private ItemStack[] inv;
    private static final int[] slots_top = new int[] {};
    private static final int[] slots_bottom = new int[] {2, 1, 0};
    private static final int[] slots_sides = new int[] {2, 1, 0};

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return side.getIndex() == 0 ? slots_bottom : (side.getIndex() == 1 ? slots_top : slots_sides);
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return direction != EnumFacing.UP;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return false;
    }

    @Override
    public int getSizeInventory() {
        return inv.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inv[slot];
    }

    @Override
     public ItemStack decrStackSize(int slot, int amt) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize <= amt) {
                setInventorySlotContents(slot, null);
                } else {
                stack = stack.splitStack(amt);
                if (stack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
     }


    @Override
    public ItemStack removeStackFromSlot(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            setInventorySlotContents(slot, null);
        }
        return stack;
    }


    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inv[slot] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }
    }


    @Override
    public String getName() {
        return "EnergyCreator";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer e) {}

    @Override
    public void closeInventory(EntityPlayer e) {}

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack stack) {
        return stack.getItem() == Items.REDSTONE || stack.getItem() == Item.getItemFromBlock(Blocks.REDSTONE_BLOCK);
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }
}
