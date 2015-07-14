package dmf444.CombatPlus.Common.TileEntity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.TileEnergyHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;



public class TileEnergyCreator extends TileEnergyHandler implements ISidedInventory{

    private static final int EMPTY = 0;
    private static final int REDSTONE = 1;
    private static final int BLOCK = 2;

    private static final int ENERGY_CREATED = 1500;

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
    public void writeToNBT(NBTTagCompound tagCompound) {
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
    }

    @Override
    public void updateEntity() {
        ticks++;

        if(ticks == 150) {
            if (worldObj.getBlock(xCoord, yCoord - 1, zCoord) == Blocks.lava && this.storage.getEnergyStored() < 50000) {
                int Type = testItems(this.inv[0]);
                if (Type != EMPTY) {
                    if (Type == REDSTONE) {
                        this.decrStackSize(0, 1);
                        storage.receiveEnergy(ENERGY_CREATED, false);
                    } else if (Type == BLOCK) {
                        this.decrStackSize(0, 1);
                        storage.receiveEnergy((int) (ENERGY_CREATED * 9.5), false);
                    }
                } else if (testItems(this.inv[1]) != EMPTY) {
                    Type = testItems(this.inv[1]);
                    if (Type == REDSTONE) {
                        this.decrStackSize(1, 1);
                        storage.receiveEnergy(ENERGY_CREATED, false);
                    } else if (Type == BLOCK) {
                        this.decrStackSize(1, 1);
                        storage.receiveEnergy((int) (ENERGY_CREATED * 9.5), false);
                    }
                } else if (testItems(this.inv[2]) != EMPTY) {
                    Type = testItems(this.inv[2]);
                    if (Type == REDSTONE) {
                        this.decrStackSize(2, 1);
                        storage.receiveEnergy(ENERGY_CREATED, false);
                    } else if (Type == BLOCK) {
                        this.decrStackSize(2, 1);
                        storage.receiveEnergy((int) (ENERGY_CREATED * 9.5), false);
                    }
                }
            }
            ticks = 0;
        }
        if(worldObj.getTileEntity(xCoord, yCoord + 1,zCoord) instanceof IEnergyHandler && this.storage.getEnergyStored() > 1500){
            IEnergyHandler handler = (IEnergyHandler) worldObj.getTileEntity(xCoord, yCoord +1, zCoord);
            handler.receiveEnergy(ForgeDirection.DOWN, 1500, false);
            this.storage.extractEnergy(1500, false);
        }
    }


    private int testItems(ItemStack itemstack) {
        if (itemstack != null) {
            Item item = itemstack.getItem();
            if (item.equals(Items.redstone)) {
                return REDSTONE;
            } else if (item.equals(Item.getItemFromBlock(Blocks.redstone_block))) {
                return BLOCK;
            } else {
                return EMPTY;
            }
        }
        return EMPTY;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return 0;
    }
    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        if(from == ForgeDirection.UP)
            return storage.extractEnergy(maxExtract, simulate);
        else
            return 0;
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound syncData = new NBTTagCompound();
        syncData.setInteger("Storage", storage.getEnergyStored());
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        storage.setEnergyStored(pkt.func_148857_g().getInteger("Storage"));
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
    public int[] getAccessibleSlotsFromSide(int side) {
        return side == 0 ? slots_bottom : (side == 1 ? slots_top : slots_sides);
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return side != ForgeDirection.UP.ordinal();
    }

    @Override
    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
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
    public ItemStack getStackInSlotOnClosing(int slot) {
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
    public String getInventoryName() {
        return "EnergyCreator";
    }

    @Override
    public boolean hasCustomInventoryName() {
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
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack stack) {
        return stack.getItem() == Items.redstone || stack.getItem() == Item.getItemFromBlock(Blocks.redstone_block);
    }
}
