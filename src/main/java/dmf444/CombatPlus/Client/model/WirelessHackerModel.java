package dmf444.CombatPlus.Client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * WirelessHackerModel - DMF444
 * Created using Tabula 4.1.1
 */
public class WirelessHackerModel extends ModelBase {
    public ModelRenderer Base2;
    public ModelRenderer Base1;
    public ModelRenderer Base3;
    public ModelRenderer Base4;
    public ModelRenderer Stand;
    public ModelRenderer AntennaBase;
    public ModelRenderer Unit;
    public ModelRenderer Antenna;
    public ModelRenderer keypad;

    public WirelessHackerModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Base3 = new ModelRenderer(this, 0, 5);
        this.Base3.setRotationPoint(4.0F, 23.0F, 2.0F);
        this.Base3.addBox(0.0F, 0.0F, -5.0F, 1, 1, 7, 0.0F);
        this.Antenna = new ModelRenderer(this, 48, 0);
        this.Antenna.setRotationPoint(-5.1F, 13.6F, 0.0F);
        this.Antenna.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.AntennaBase = new ModelRenderer(this, 47, 4);
        this.AntennaBase.setRotationPoint(-5.1F, 16.6F, 0.0F);
        this.AntennaBase.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.Stand = new ModelRenderer(this, 34, 7);
        this.Stand.setRotationPoint(0.0F, 23.9F, 4.1F);
        this.Stand.addBox(-1.0F, 0.0F, 0.0F, 2, 1, 6, 0.0F);
        this.setRotateAngle(Stand, 2.4586453172844123F, 0.0F, 0.0F);
        this.Base2 = new ModelRenderer(this, 0, 0);
        this.Base2.setRotationPoint(0.0F, 23.0F, -4.0F);
        this.Base2.addBox(-5.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F);
        this.keypad = new ModelRenderer(this, 33, 0);
        this.keypad.setRotationPoint(1.0F, 20.9F, -4.3F);
        this.keypad.addBox(0.0F, 0.0F, 0.0F, 2, 1, 3, 0.0F);
        this.setRotateAngle(keypad, 0.8639379797371932F, 0.0F, 0.0F);
        this.Base4 = new ModelRenderer(this, 0, 5);
        this.Base4.setRotationPoint(-5.0F, 23.0F, 2.0F);
        this.Base4.addBox(0.0F, 0.0F, -5.0F, 1, 1, 7, 0.0F);
        this.Unit = new ModelRenderer(this, 0, 15);
        this.Unit.setRotationPoint(0.0F, 23.0F, -3.1F);
        this.Unit.addBox(-4.0F, -2.0F, 0.0F, 8, 2, 8, 0.0F);
        this.setRotateAngle(Unit, 0.8639379797371932F, 0.0F, 0.0F);
        this.Base1 = new ModelRenderer(this, 0, 0);
        this.Base1.setRotationPoint(0.0F, 23.0F, 4.0F);
        this.Base1.addBox(-5.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Base3.render(f5);
        this.Antenna.render(f5);
        this.AntennaBase.render(f5);
        this.Stand.render(f5);
        this.Base2.render(f5);
        this.keypad.render(f5);
        this.Base4.render(f5);
        this.Unit.render(f5);
        this.Base1.render(f5);
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
