package dmf444.CombatPlus.proxy;



import dmf444.CombatPlus.Client.model.*;
import dmf444.CombatPlus.Common.TileEntity.*;
import dmf444.CombatPlus.init.ModelRegistry;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy{

    @Override
    public void registerKeyBindings() {

    }

    @SideOnly(Side.CLIENT)
    public void registerRenderers(){
        ClientRegistry.bindTileEntitySpecialRenderer(TileSetTurretBase.class, new WirelessHackerRender());
        ClientRegistry.bindTileEntitySpecialRenderer(WirelessEnergy.class, new WirelessEnergyRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileInfiniteEnergy.class, new WirelessEnergyRender());

        //ClientRegistry.bindTileEntitySpecialRenderer(TileInterception.class, new InterceptorRender());


    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerItemModels() {
        ModelRegistry.loadModels();
    }


}