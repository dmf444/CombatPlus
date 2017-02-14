package dmf444.CombatPlus.Core;


import dmf444.CombatPlus.Common.GuiHandler;
import dmf444.CombatPlus.Common.ItemCardChangerCreative;
import dmf444.CombatPlus.Common.ItemCardChangerNormal;
import dmf444.CombatPlus.Common.TileEntity.TileEnergyCreator;
import dmf444.CombatPlus.Common.TileEntity.TileInfiniteEnergy;
import dmf444.CombatPlus.Common.TileEntity.TileSetTurretBase;
import dmf444.CombatPlus.Common.TileEntity.WirelessEnergy;
import dmf444.CombatPlus.Common.blocks.*;
import dmf444.CombatPlus.proxy.CommonProxy;
import dmf444.CombatPlus.Core.lib.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;


@Mod(modid = ModInfo.MODID, version = ModInfo.VERSION, name = "Combat Plus", dependencies = "required-after:openmodularturrets@[1.7.10-2.0.1-137,)")
public class CombatPlus {

    @Mod.Instance(value = "combatplus")
    public static CombatPlus instance;

    @SidedProxy(clientSide= ModInfo.Clientproxy, serverSide= ModInfo.Serverproxy)
    public static CommonProxy proxy;

    public static Side side;

    public static Block wirelessBase;
    public static Block CardHackTerminal;
    public static Block wirelessEnergy;
    public static Block hackInterceptor;
    public static Block energyCreator;

    public static Item hackyCard;
    public static Item hackyCardNormal;
    public static Item upgradeCard;
    public static Item explodeCard;

    @Mod.EventHandler
    public void init(FMLPreInitializationEvent event)
    {
        ConfigHandler.init(event.getSuggestedConfigurationFile());

        hackyCard = new ItemCardChangerCreative();
        GameRegistry.registerItem(hackyCard, "hackyCard");
        hackyCardNormal = new ItemCardChangerNormal();
        GameRegistry.registerItem(hackyCardNormal, "hackyCardNormal");
        upgradeCard = new Item().setCreativeTab(CreativeTabs.REDSTONE).setUnlocalizedName("disableCard");//.setTextureName("combatplus:InterceptionUpgrade");
        GameRegistry.registerItem(upgradeCard, "disableCard");
        explodeCard = new Item().setCreativeTab(CreativeTabs.REDSTONE).setUnlocalizedName("explosiveInterception");//.setTextureName("combatplus:ExplosiveUpgrade");
        GameRegistry.registerItem(explodeCard, "explosiveInterception");


        energyCreator = new BlockEnergyCreator().setUnlocalizedName("EnergyCreator");
        GameRegistry.registerBlock(energyCreator, "EnergyCreator");
        wirelessBase = new BlockInfiniteEnergy().setUnlocalizedName("CreativeWirelessCharger");
        GameRegistry.registerBlock(wirelessBase, "infiniteTile");
        CardHackTerminal = new WirelessHacker().setUnlocalizedName("TurretHacker");
        GameRegistry.registerBlock(CardHackTerminal, "CardHackTerminal");
        wirelessEnergy = new BlockWirelessEnergy().setUnlocalizedName("WirelessCharger");
        GameRegistry.registerBlock(wirelessEnergy, "wirelessEnergy");
        hackInterceptor = new BlockHackInterceptor().setUnlocalizedName("interceptor");
        GameRegistry.registerBlock(hackInterceptor, "interceptor");


        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        GameRegistry.registerTileEntity(TileEnergyCreator.class, "EnergyCreator");
        GameRegistry.registerTileEntity(TileSetTurretBase.class, "CardCheat");
        GameRegistry.registerTileEntity(TileInfiniteEnergy.class, "infiniteTile");
        GameRegistry.registerTileEntity(WirelessEnergy.class, "wirelessEnergy");



    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event)
    {
       // public static Item explodeCard;

        proxy.registerRenderers();
}

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event)
    {
        GameRegistry.addShapelessRecipe(new ItemStack(hackyCardNormal), new ItemStack(Blocks.REDSTONE_TORCH), new ItemStack(getItem("ioBus")), new ItemStack(getItem("chamberTierTwo")), new ItemStack(Items.NAME_TAG));
        GameRegistry.addShapedRecipe(new ItemStack(CardHackTerminal), new Object[] {"obo", "rsr", "oro", 'o', new ItemStack(Items.GOLD_INGOT), 'r', new ItemStack(getItem("ioBus")), 'b', new ItemStack(getItem("baseTierWood")), 's', new ItemStack(getItem("sensorTierFourItem"))});
        GameRegistry.addShapedRecipe(new ItemStack(wirelessEnergy), new Object[]{"iei", "d d", "iei", 'i', new ItemStack(Items.IRON_INGOT), 'e', new ItemStack(getItem("chamberTierThree")), 'd', new ItemStack(Items.DIAMOND)});
        GameRegistry.addShapedRecipe(new ItemStack(hackInterceptor), new Object[]{"c t", "eis", "yry", 'c', new ItemStack(Items.COMPARATOR), 't', new ItemStack(Blocks.TORCH), 'e', new ItemStack(Items.REPEATER), 'i', new ItemStack(Items.IRON_INGOT), 's', new ItemStack(Items.STICK), 'y', new ItemStack(Blocks.STONE_SLAB, 1, 0), 'r', new ItemStack(Items.REDSTONE)});
        GameRegistry.addShapedRecipe(new ItemStack(upgradeCard), new Object[]{"pdp", "prp", "pdp", 'p', new ItemStack(Items.PAPER), 'd', new ItemStack(Items.DYE, 1, 1), 'r', new ItemStack(Items.REPEATER)});
        GameRegistry.addShapedRecipe(new ItemStack(explodeCard), new Object[]{"pdp", "dtd", "pdp", 'p', new ItemStack(Items.PAPER), 'd', new ItemStack(Items.DIAMOND), 't', new ItemStack(Blocks.TNT)});

    }

    private Item getItem(String name){
        return GameRegistry.findItem("openmodularturrets", name);
    }
}
