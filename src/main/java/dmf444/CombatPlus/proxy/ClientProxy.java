package dmf444.CombatPlus.proxy;


import cpw.mods.fml.client.registry.ClientRegistry;
import dmf444.CombatPlus.Client.model.WirelessEnergyRender;
import dmf444.CombatPlus.Client.model.WirelessHackerRender;
import dmf444.CombatPlus.Common.TileEntity.TileInfiniteEnergy;
import dmf444.CombatPlus.Common.TileEntity.TileSetTurretBase;
import dmf444.CombatPlus.Common.TileEntity.WirelessEnergy;

public class ClientProxy extends CommonProxy{

    @Override
    public void registerKeyBindings() {

    }
    public void registerRenderers(){
        ClientRegistry.bindTileEntitySpecialRenderer(TileSetTurretBase.class, new WirelessHackerRender());
        ClientRegistry.bindTileEntitySpecialRenderer(WirelessEnergy.class, new WirelessEnergyRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileInfiniteEnergy.class, new WirelessEnergyRender());
    }


}