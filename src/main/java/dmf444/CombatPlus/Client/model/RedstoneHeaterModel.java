package dmf444.CombatPlus.Client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * RedstoneHeaterModel - dmf444
 * Created using Tabula 4.1.1
 */
public class RedstoneHeaterModel extends ModelBase {
    public ModelRenderer Leg1;
    public ModelRenderer Leg2;
    public ModelRenderer Leg3;
    public ModelRenderer Leg4;
    public ModelRenderer BaseCauldron;
    public ModelRenderer CauldronSide1;
    public ModelRenderer CauldronRoof4;
    public ModelRenderer CauldronRoof2;
    public ModelRenderer CauldronSide3;
    public ModelRenderer CauldronSide4;
    public ModelRenderer CauldronRoof1;
    public ModelRenderer CauldronSide2;
    public ModelRenderer CauldronRoof3;
    public ModelRenderer Roof1;

    public RedstoneHeaterModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.CauldronSide3 = new ModelRenderer(this, 0, 35);
        this.CauldronSide3.setRotationPoint(-6.0F, 15.0F, -7.0F);
        this.CauldronSide3.addBox(0.0F, 0.0F, 0.0F, 12, 5, 1, 0.0F);
        this.BaseCauldron = new ModelRenderer(this, 0, 47);
        this.BaseCauldron.setRotationPoint(-7.0F, 20.0F, -7.0F);
        this.BaseCauldron.addBox(0.0F, 0.0F, 0.0F, 14, 1, 14, 0.0F);
        this.CauldronSide1 = new ModelRenderer(this, 0, 14);
        this.CauldronSide1.setRotationPoint(-7.0F, 15.0F, -7.0F);
        this.CauldronSide1.addBox(0.0F, 0.0F, 0.0F, 1, 5, 14, 0.0F);
        this.Leg4 = new ModelRenderer(this, 50, 0);
        this.Leg4.setRotationPoint(-7.0F, 21.0F, -7.0F);
        this.Leg4.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.Leg2 = new ModelRenderer(this, 50, 0);
        this.Leg2.setRotationPoint(-7.0F, 21.0F, 6.0F);
        this.Leg2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.CauldronRoof2 = new ModelRenderer(this, 35, 7);
        this.CauldronRoof2.setRotationPoint(0.0F, 14.6F, -6.3F);
        this.CauldronRoof2.addBox(-6.0F, -5.0F, 0.0F, 12, 6, 1, 0.0F);
        this.setRotateAngle(CauldronRoof2, -0.7155849933176751F, 0.0F, 0.0F);
        this.Leg1 = new ModelRenderer(this, 50, 0);
        this.Leg1.setRotationPoint(6.0F, 21.0F, 6.0F);
        this.Leg1.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.CauldronRoof3 = new ModelRenderer(this, 34, 16);
        this.CauldronRoof3.setRotationPoint(6.2F, 16.0F, 0.0F);
        this.CauldronRoof3.addBox(0.0F, -6.0F, -7.0F, 1, 6, 14, 0.0F);
        this.setRotateAngle(CauldronRoof3, 0.0F, 0.0F, -0.7155849933176751F);
        this.Leg3 = new ModelRenderer(this, 50, 0);
        this.Leg3.setRotationPoint(6.0F, 21.0F, -7.0F);
        this.Leg3.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.CauldronRoof4 = new ModelRenderer(this, 34, 16);
        this.CauldronRoof4.setRotationPoint(-7.0F, 15.3F, 0.0F);
        this.CauldronRoof4.addBox(0.0F, -6.0F, -7.0F, 1, 6, 14, 0.0F);
        this.setRotateAngle(CauldronRoof4, 0.0F, 0.0F, 0.7155849933176751F);
        this.Roof1 = new ModelRenderer(this, 0, 7);
        this.Roof1.setRotationPoint(-2.5F, 10.5F, -2.5F);
        this.Roof1.addBox(0.0F, 0.0F, 0.0F, 5, 1, 5, 0.0F);
        this.CauldronSide2 = new ModelRenderer(this, 0, 14);
        this.CauldronSide2.setRotationPoint(6.0F, 15.0F, -7.0F);
        this.CauldronSide2.addBox(0.0F, 0.0F, 0.0F, 1, 5, 14, 0.0F);
        this.CauldronSide4 = new ModelRenderer(this, 0, 35);
        this.CauldronSide4.setRotationPoint(-6.0F, 15.0F, 6.0F);
        this.CauldronSide4.addBox(0.0F, 0.0F, 0.0F, 12, 5, 1, 0.0F);
        this.CauldronRoof1 = new ModelRenderer(this, 35, 7);
        this.CauldronRoof1.setRotationPoint(0.0F, 15.2F, 5.5F);
        this.CauldronRoof1.addBox(-6.0F, -5.0F, 0.0F, 12, 6, 1, 0.0F);
        this.setRotateAngle(CauldronRoof1, 0.7155849933176751F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.CauldronSide3.render(f5);
        this.BaseCauldron.render(f5);
        this.CauldronSide1.render(f5);
        this.Leg4.render(f5);
        this.Leg2.render(f5);
        this.CauldronRoof2.render(f5);
        this.Leg1.render(f5);
        this.CauldronRoof3.render(f5);
        this.Leg3.render(f5);
        this.CauldronRoof4.render(f5);
        this.Roof1.render(f5);
        this.CauldronSide2.render(f5);
        this.CauldronSide4.render(f5);
        this.CauldronRoof1.render(f5);
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
