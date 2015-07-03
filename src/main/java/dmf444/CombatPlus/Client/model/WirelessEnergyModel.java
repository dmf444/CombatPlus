package dmf444.CombatPlus.Client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * WirelessEnergy - dmf444
 * Created using Tabula 4.1.1
 */
public class WirelessEnergyModel extends ModelBase {
    public ModelRenderer Base2_2;
    public ModelRenderer Base1;
    public ModelRenderer Base4_4;
    public ModelRenderer Base3_3;
    public ModelRenderer shape5;
    public ModelRenderer shape6;
    public ModelRenderer shape7;
    public ModelRenderer shape8;
    public ModelRenderer Base1_1;
    public ModelRenderer Base4;
    public ModelRenderer Base2;
    public ModelRenderer Base3;
    public ModelRenderer Glass2;
    public ModelRenderer Glass4;
    public ModelRenderer Glass1;
    public ModelRenderer Base22;
    public ModelRenderer Top17;
    public ModelRenderer Top22;
    public ModelRenderer shape17;
    public ModelRenderer Glass3;

    public WirelessEnergyModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Glass2 = new ModelRenderer(this, 0, 3);
        this.Glass2.setRotationPoint(-7.5F, 9.0F, -7.0F);
        this.Glass2.addBox(0.0F, 0.0F, 0.0F, 0, 14, 14, 0.0F);
        this.Glass1 = new ModelRenderer(this, 0, 3);
        this.Glass1.setRotationPoint(7.5F, 9.0F, -7.0F);
        this.Glass1.addBox(0.0F, 0.0F, 0.0F, 0, 14, 14, 0.0F);
        this.Base4 = new ModelRenderer(this, 0, 0);
        this.Base4.setRotationPoint(7.0F, 23.0F, -7.0F);
        this.Base4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14, 0.0F);
        this.shape17 = new ModelRenderer(this, 5, 7);
        this.shape17.setRotationPoint(0.0F, 22.9F, 0.0F);
        this.shape17.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
        this.Base4_4 = new ModelRenderer(this, 0, 0);
        this.Base4_4.setRotationPoint(7.0F, 8.0F, -7.0F);
        this.Base4_4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14, 0.0F);
        this.Base22 = new ModelRenderer(this, 21, 0);
        this.Base22.setRotationPoint(-7.0F, 8.5F, -7.0F);
        this.Base22.addBox(0.0F, 0.0F, 0.0F, 14, 0, 14, 0.0F);
        this.Glass3 = new ModelRenderer(this, 0, 17);
        this.Glass3.setRotationPoint(-7.0F, 9.0F, -7.5F);
        this.Glass3.addBox(0.0F, 0.0F, 0.0F, 14, 14, 0, 0.0F);
        this.Base1 = new ModelRenderer(this, 0, 0);
        this.Base1.setRotationPoint(-8.0F, 23.0F, -8.0F);
        this.Base1.addBox(0.0F, 0.0F, 0.0F, 16, 1, 1, 0.0F);
        this.Base1_1 = new ModelRenderer(this, 0, 0);
        this.Base1_1.setRotationPoint(-8.0F, 8.0F, -8.0F);
        this.Base1_1.addBox(0.0F, 0.0F, 0.0F, 16, 1, 1, 0.0F);
        this.Top22 = new ModelRenderer(this, 21, 0);
        this.Top22.setRotationPoint(-7.0F, 23.5F, -7.0F);
        this.Top22.addBox(0.0F, 0.0F, 0.0F, 14, 0, 14, 0.0F);
        this.Glass4 = new ModelRenderer(this, 0, 17);
        this.Glass4.setRotationPoint(-7.0F, 9.0F, 7.5F);
        this.Glass4.addBox(0.0F, 0.0F, 0.0F, 14, 14, 0, 0.0F);
        this.shape8 = new ModelRenderer(this, 0, 0);
        this.shape8.setRotationPoint(7.0F, 9.0F, 7.0F);
        this.shape8.addBox(0.0F, 0.0F, 0.0F, 1, 14, 1, 0.0F);
        this.shape7 = new ModelRenderer(this, 0, 0);
        this.shape7.setRotationPoint(-8.0F, 9.0F, 7.0F);
        this.shape7.addBox(0.0F, 0.0F, 0.0F, 1, 14, 1, 0.0F);
        this.shape5 = new ModelRenderer(this, 0, 0);
        this.shape5.setRotationPoint(-8.0F, 9.0F, -8.0F);
        this.shape5.addBox(0.0F, 0.0F, 0.0F, 1, 14, 1, 0.0F);
        this.shape6 = new ModelRenderer(this, 0, 0);
        this.shape6.setRotationPoint(7.0F, 9.0F, -8.0F);
        this.shape6.addBox(0.0F, 0.0F, 0.0F, 1, 14, 1, 0.0F);
        this.Base3_3 = new ModelRenderer(this, 0, 0);
        this.Base3_3.setRotationPoint(-8.0F, 8.0F, -7.0F);
        this.Base3_3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14, 0.0F);
        this.Base3 = new ModelRenderer(this, 0, 0);
        this.Base3.setRotationPoint(-8.0F, 23.0F, -7.0F);
        this.Base3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14, 0.0F);
        this.Top17 = new ModelRenderer(this, 5, 7);
        this.Top17.setRotationPoint(0.0F, 8.5F, 0.0F);
        this.Top17.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
        this.Base2_2 = new ModelRenderer(this, 0, 0);
        this.Base2_2.setRotationPoint(-8.0F, 8.0F, 7.0F);
        this.Base2_2.addBox(0.0F, 0.0F, 0.0F, 16, 1, 1, 0.0F);
        this.Base2 = new ModelRenderer(this, 0, 0);
        this.Base2.setRotationPoint(-8.0F, 23.0F, 7.0F);
        this.Base2.addBox(0.0F, 0.0F, 0.0F, 16, 1, 1, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Glass2.render(f5);
        this.Glass1.render(f5);
        this.Base4.render(f5);
        this.shape17.render(f5);
        this.Base4_4.render(f5);
        this.Base22.render(f5);
        this.Glass3.render(f5);
        this.Base1.render(f5);
        this.Base1_1.render(f5);
        this.Top22.render(f5);
        this.Glass4.render(f5);
        this.shape8.render(f5);
        this.shape7.render(f5);
        this.shape5.render(f5);
        this.shape6.render(f5);
        this.Base3_3.render(f5);
        this.Base3.render(f5);
        this.Top17.render(f5);
        this.Base2_2.render(f5);
        this.Base2.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
