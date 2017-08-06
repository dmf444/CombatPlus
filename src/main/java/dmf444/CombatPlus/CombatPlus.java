package dmf444.CombatPlus;


import dmf444.CombatPlus.Common.GuiHandler;
import dmf444.CombatPlus.Core.ConfigHandler;
import dmf444.CombatPlus.Core.lib.ModInfo;
import dmf444.CombatPlus.init.BlockRegistry;
import dmf444.CombatPlus.init.ItemRegistry;
import dmf444.CombatPlus.proxy.CommonProxy;
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


    @Mod.EventHandler
    @SuppressWarnings("deprecation")
    public void init(FMLPreInitializationEvent event)
    {
        ConfigHandler.init(event.getSuggestedConfigurationFile());

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        proxy.registerItemModels();



    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event)
    {

        proxy.registerRenderers();
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event)
    {
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.HACKING_CARD), new ItemStack(Blocks.REDSTONE_TORCH), new ItemStack(getItem("ioBus")), new ItemStack(getItem("chamberTierTwo")), new ItemStack(Items.NAME_TAG));
        GameRegistry.addShapedRecipe(new ItemStack(BlockRegistry.TURRET_HACKER), new Object[] {"obo", "rsr", "oro", 'o', new ItemStack(Items.GOLD_INGOT), 'r', new ItemStack(getItem("ioBus")), 'b', new ItemStack(getItem("baseTierWood")), 's', new ItemStack(getItem("sensorTierFourItem"))});
        GameRegistry.addShapedRecipe(new ItemStack(BlockRegistry.WIRELESS_ENERGY), new Object[]{"iei", "d d", "iei", 'i', new ItemStack(Items.IRON_INGOT), 'e', new ItemStack(getItem("chamberTierThree")), 'd', new ItemStack(Items.DIAMOND)});
        GameRegistry.addShapedRecipe(new ItemStack(BlockRegistry.HACK_INTERCEPTOR), new Object[]{"c t", "eis", "yry", 'c', new ItemStack(Items.COMPARATOR), 't', new ItemStack(Blocks.TORCH), 'e', new ItemStack(Items.REPEATER), 'i', new ItemStack(Items.IRON_INGOT), 's', new ItemStack(Items.STICK), 'y', new ItemStack(Blocks.STONE_SLAB, 1, 0), 'r', new ItemStack(Items.REDSTONE)});
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.UPGRADE_CARD), new Object[]{"pdp", "prp", "pdp", 'p', new ItemStack(Items.PAPER), 'd', new ItemStack(Items.DYE, 1, 1), 'r', new ItemStack(Items.REPEATER)});
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.EXPLOSIVE_CARD), new Object[]{"pdp", "dtd", "pdp", 'p', new ItemStack(Items.PAPER), 'd', new ItemStack(Items.DIAMOND), 't', new ItemStack(Blocks.TNT)});

    }

    private Item getItem(String name){
        return GameRegistry.findItem("openmodularturrets", name);
    }
}
