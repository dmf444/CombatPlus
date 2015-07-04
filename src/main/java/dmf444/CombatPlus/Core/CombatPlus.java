package dmf444.CombatPlus.Core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import dmf444.CombatPlus.Common.ItemCardChangerCreative;
import dmf444.CombatPlus.Common.ItemCardChangerNormal;
import dmf444.CombatPlus.Common.TileEntity.TileInfiniteEnergy;
import dmf444.CombatPlus.Common.TileEntity.TileSetTurretBase;
import dmf444.CombatPlus.Common.TileEntity.WirelessEnergy;
import dmf444.CombatPlus.Common.blocks.BlockInfiniteEnergy;
import dmf444.CombatPlus.Common.blocks.WirelessHacker;
import dmf444.CombatPlus.Common.blocks.BlockWirelessEnergy;
import dmf444.CombatPlus.proxy.CommonProxy;
import dmf444.CombatPlus.Core.lib.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


@Mod(modid = ModInfo.MODID, version = ModInfo.VERSION)
public class CombatPlus {
    @Mod.Instance(value = "CombatPlus")
    public static CombatPlus instance;

    @SidedProxy(clientSide= ModInfo.Clientproxy, serverSide= ModInfo.Serverproxy)
    public static CommonProxy proxy;

    public static Side side;

    public static Block wirelessBase;
    public static Item hackyCard;
    public static Block CardHackTerminal;
    public static Item hackyCardNormal;
    public static Block wirelessEnergy;
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


        wirelessBase = new BlockInfiniteEnergy().setBlockName("CreativeWirelessCharger");
        GameRegistry.registerBlock(wirelessBase, "infiniteTile");
        CardHackTerminal = new WirelessHacker().setBlockName("TurretHacker");
        GameRegistry.registerBlock(CardHackTerminal, "CardHackTerminal");
        wirelessEnergy = new BlockWirelessEnergy().setBlockName("WirelessCharger");
        GameRegistry.registerBlock(wirelessEnergy, "wirelessEnergy");



        GameRegistry.registerTileEntity(TileSetTurretBase.class, "CardCheat");
        GameRegistry.registerTileEntity(TileInfiniteEnergy.class, "infiniteTile");
        GameRegistry.registerTileEntity(WirelessEnergy.class, "wirelessEnergy");

    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event)
    {
        proxy.registerRenderers();
        GameRegistry.addShapelessRecipe(new ItemStack(hackyCardNormal), new ItemStack(Blocks.redstone_torch), new ItemStack(openmodularturrets.items.Items.ioBus), new ItemStack(openmodularturrets.items.Items.chamberTierTwoItem), new ItemStack(Items.name_tag));
        GameRegistry.addShapedRecipe(new ItemStack(CardHackTerminal), new Object[] {"obo", "rsr", "oro", 'o', new ItemStack(Items.gold_ingot), 'r', new ItemStack(openmodularturrets.items.Items.ioBus), 'b', new ItemStack(openmodularturrets.blocks.Blocks.turretBaseTierOne), 's', new ItemStack(openmodularturrets.items.Items.sensorTierFourItem)});
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event)
    {

    }
}
