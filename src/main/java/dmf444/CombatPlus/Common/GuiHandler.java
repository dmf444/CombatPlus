package dmf444.CombatPlus.Common;


import cpw.mods.fml.common.network.IGuiHandler;
import dmf444.CombatPlus.Client.gui.GuiEnergyCreator;
import dmf444.CombatPlus.Common.TileEntity.TileEnergyCreator;
import dmf444.CombatPlus.Common.container.ContainerEnergyCreator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    //returns an instance of the Container you made earlier
    public GuiHandler() {
    }


    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if(tileEntity instanceof TileEnergyCreator){

            return new ContainerEnergyCreator(player.inventory, (TileEnergyCreator) tileEntity);
        }
        return null;
    }




    //returns an instance of the Gui you made earlier
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if(tileEntity instanceof TileEnergyCreator)
            return new GuiEnergyCreator(player.inventory, (TileEnergyCreator) tileEntity);
        else
            return null;
    }
}