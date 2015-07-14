package dmf444.CombatPlus.proxy;


import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import dmf444.CombatPlus.Client.model.*;
import dmf444.CombatPlus.Common.TileEntity.*;
import dmf444.CombatPlus.Core.CombatPlus;
import li.cil.oc.common.block.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy{

    @Override
    public void registerKeyBindings() {

    }
    public void registerRenderers(){
        ClientRegistry.bindTileEntitySpecialRenderer(TileSetTurretBase.class, new WirelessHackerRender());
        ClientRegistry.bindTileEntitySpecialRenderer(WirelessEnergy.class, new WirelessEnergyRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileInfiniteEnergy.class, new WirelessEnergyRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileInterception.class, new InterceptorRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEnergyCreator.class, new RedstoneHeaterRender());

       // MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(CombatPlus.CardHackTerminal), new BlockRender(new WirelessHackerModel(), "combatplus:textures/blocks/WirelessHacker.png"));
    }


}