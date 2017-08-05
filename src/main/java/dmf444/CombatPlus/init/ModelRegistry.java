package dmf444.CombatPlus.init;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

/**
 * Created by David on 2017-08-01.
 */
public class ModelRegistry {

    public static void loadModels() {
        registerModel(BlockRegistry.CREATIVE_WIRELESS_ENERGY);

    }


    public static void registerModel(Object obj){
        Item item = null;
        ResourceLocation name = null;
        if(obj instanceof Item){
            item = (Item) obj;
            name = item.getRegistryName();
        }else if(obj instanceof Block){
            item = Item.getItemFromBlock((Block) obj);
            name = ((Block)obj).getRegistryName();
        }
        if(item != null) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(name, "inventory"));
        }
    }

}
