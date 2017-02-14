package dmf444.CombatPlus.Common;



import dmf444.CombatPlus.Client.gui.GuiEnergyCreator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import dmf444.CombatPlus.Common.container.*;
import dmf444.CombatPlus.Common.TileEntity.*;

public class GuiHandler implements IGuiHandler {
    //returns an instance of the Container you made earlier
    public GuiHandler() {
    }


    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if(tileEntity instanceof TileEnergyCreator){

            return new ContainerEnergyCreator(player.inventory, (TileEnergyCreator) tileEntity);
        }
        return null;
    }




    //returns an instance of the Gui you made earlier
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if(tileEntity instanceof TileEnergyCreator)
            return new GuiEnergyCreator(player.inventory, (TileEnergyCreator) tileEntity);
        else
            return null;
    }
}