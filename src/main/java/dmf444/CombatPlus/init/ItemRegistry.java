package dmf444.CombatPlus.init;

import dmf444.CombatPlus.Common.ItemCardChangerCreative;
import dmf444.CombatPlus.Common.ItemCardChangerNormal;
import jline.internal.Log;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

import java.lang.reflect.Field;

/**
 * Created by David on 2017-08-01.
 */
public class ItemRegistry {


    public static Item TEAM_HACKING_CARD = new ItemCardChangerCreative();
    public static Item HACKING_CARD = new ItemCardChangerNormal();
    public static Item UPGRADE_CARD = new Item().setCreativeTab(CreativeTabs.REDSTONE).setUnlocalizedName("disableCard").setRegistryName("disableCard");
    public static Item EXPLOSIVE_CARD   = new Item().setCreativeTab(CreativeTabs.REDSTONE).setUnlocalizedName("explosiveInterception").setUnlocalizedName("explosiveInterception");




    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        for (Field field : ItemRegistry.class.getDeclaredFields()) {
            if (field.getType() == Item.class) {
                try {
                    Item item = (Item) field.get(null);
                    registry.register(item);
                } catch (IllegalAccessException e) {
                    Log.warn("Somehow failed to get a block from its registrator. WAT?");
                }
            }
        }
    }
}
