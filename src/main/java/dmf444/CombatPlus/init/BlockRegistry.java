package dmf444.CombatPlus.init;

/**
 * Created by David on 2017-08-01.
 */

import dmf444.CombatPlus.Common.TileEntity.*;
import dmf444.CombatPlus.Common.blocks.*;
import jline.internal.Log;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

import java.lang.reflect.Field;

/**
 * File created by mincrmatt12 on 1/21/2017.
 * Originally written for ImpressiveAutomation.
 * <p>
 * See LICENSE.txt for license information.
 */

public class BlockRegistry {

    public static Block CREATIVE_WIRELESS_ENERGY = new BlockInfiniteEnergy();
    public static Block WIRELESS_ENERGY =  new BlockWirelessEnergy();
    public static Block ENERGY_CREATOR = new BlockEnergyCreator();
    public static Block HACK_INTERCEPTOR = new BlockHackInterceptor();
    public static Block TURRET_HACKER = new WirelessHacker();


    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        for (Field field : BlockRegistry.class.getDeclaredFields()) {
            if (field.getType() == Block.class) {
                try {
                    registry.register((Block)field.get(null));
                } catch (IllegalAccessException e) {
                    Log.warn("Somehow failed to get a block from its registrator. WAT?");
                }
            }
        }

        GameRegistry.registerTileEntity(TileEnergyCreator.class, "EnergyCreator");
        GameRegistry.registerTileEntity(TileSetTurretBase.class, "CardCheat");
        GameRegistry.registerTileEntity(TileInfiniteEnergy.class, "infiniteTile");
        GameRegistry.registerTileEntity(WirelessEnergy.class, "wirelessEnergy");
        GameRegistry.registerTileEntity(TileInterception.class, "interceptor");
    }

    @SubscribeEvent
    public void registerItemBlocks(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        for (Field field : BlockRegistry.class.getDeclaredFields()) {
            if (field.getType() == Block.class) {
                try {
                    Block theBlock = (Block) field.get(null);
                    registry.register(new ItemBlock(theBlock).setRegistryName(theBlock.getRegistryName()));
                } catch (IllegalAccessException e) {
                    Log.warn("Somehow failed to get a block from its registrator. WAT?");
                }
            }
        }
    }
}

