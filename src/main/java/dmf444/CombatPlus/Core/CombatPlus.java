package dmf444.CombatPlus.Core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
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


@Mod(modid = ModInfo.MODID, version = ModInfo.VERSION, name = "Combat Plus", dependencies = "required-after:openmodularturrets@[1.7.10-2.0.1-137,)")
public class CombatPlus {

    @Instance(value = "combatplus")
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
        hackyCard = new ItemCardChangerCreative();
        GameRegistry.registerItem(hackyCard, "hackyCard");
        hackyCardNormal = new ItemCardChangerNormal();
        GameRegistry.registerItem(hackyCardNormal, "hackyCardNormal");
        upgradeCard = new Item().setCreativeTab(CreativeTabs.tabRedstone).setUnlocalizedName("disableCard").setTextureName("combatplus:InterceptionUpgrade");
        GameRegistry.registerItem(upgradeCard, "disableCard");
        explodeCard = new Item().setCreativeTab(CreativeTabs.tabRedstone).setUnlocalizedName("explosiveInterception").setTextureName("combatplus:ExplosiveUpgrade");
        GameRegistry.registerItem(explodeCard, "explosiveInterception");


        energyCreator = new BlockEnergyCreator().setBlockName("EnergyCreator");
        GameRegistry.registerBlock(energyCreator, "EnergyCreator");
        wirelessBase = new BlockInfiniteEnergy().setBlockName("CreativeWirelessCharger");
        GameRegistry.registerBlock(wirelessBase, "infiniteTile");
        CardHackTerminal = new WirelessHacker().setBlockName("TurretHacker");
        GameRegistry.registerBlock(CardHackTerminal, "CardHackTerminal");
        wirelessEnergy = new BlockWirelessEnergy().setBlockName("WirelessCharger");
        GameRegistry.registerBlock(wirelessEnergy, "wirelessEnergy");
        hackInterceptor = new BlockHackInterceptor().setBlockName("interceptor");
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
        GameRegistry.addShapelessRecipe(new ItemStack(hackyCardNormal), new ItemStack(Blocks.redstone_torch), new ItemStack(getItem("ioBus")), new ItemStack(getItem("chamberTierTwo")), new ItemStack(Items.name_tag));
        GameRegistry.addShapedRecipe(new ItemStack(CardHackTerminal), new Object[] {"obo", "rsr", "oro", 'o', new ItemStack(Items.gold_ingot), 'r', new ItemStack(getItem("ioBus")), 'b', new ItemStack(getItem("baseTierWood")), 's', new ItemStack(getItem("sensorTierFourItem"))});
        GameRegistry.addShapedRecipe(new ItemStack(wirelessEnergy), new Object[]{"iei", "d d", "iei", 'i', new ItemStack(Items.iron_ingot), 'e', new ItemStack(getItem("chamberTierThree")), 'd', new ItemStack(Items.diamond)});
        GameRegistry.addShapedRecipe(new ItemStack(hackInterceptor), new Object[]{"c t", "eis", "yry", 'c', new ItemStack(Items.comparator), 't', new ItemStack(Blocks.torch), 'e', new ItemStack(Items.repeater), 'i', new ItemStack(Items.iron_ingot), 's', new ItemStack(Items.stick), 'y', new ItemStack(Blocks.stone_slab, 1, 0), 'r', new ItemStack(Items.redstone)});
        GameRegistry.addShapedRecipe(new ItemStack(upgradeCard), new Object[]{"pdp", "prp", "pdp", 'p', new ItemStack(Items.paper), 'd', new ItemStack(Items.dye, 1, 1), 'r', new ItemStack(Items.repeater)});
        GameRegistry.addShapedRecipe(new ItemStack(explodeCard), new Object[]{"pdp", "dtd", "pdp", 'p', new ItemStack(Items.paper), 'd', new ItemStack(Items.diamond), 't', new ItemStack(Blocks.tnt)});

    }

    private Item getItem(String name){
        return GameRegistry.findItem("openmodularturrets", name);
    }
}
