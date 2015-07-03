package dmf444.CombatPlus.Client.model;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

@SideOnly(Side.CLIENT)
public class SphereModel {

    private IModelCustom modelJM;

    public SphereModel(){
        modelJM = AdvancedModelLoader.loadModel(new ResourceLocation("combatplus:modelobj/sphere.obj"));
    }

    public void render(){
        modelJM.renderAll();
    }
}