package dmf444.CombatPlus.Client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * InterceptorModel - dmf444
 * Created using Tabula 4.1.1
 */
public class InterceptorModel extends ModelBase {
    public ModelRenderer CableExtension;
    public ModelRenderer PCDoor;
    public ModelRenderer SideWing2;
    public ModelRenderer Reflector;
    public ModelRenderer SideWing1;
    public ModelRenderer MainPanel;
    public ModelRenderer pole;
    public ModelRenderer Base1;
    public ModelRenderer PCback;

    public InterceptorModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.PCback = new ModelRenderer(this, 0, 35);
        this.PCback.setRotationPoint(-3.0F, 13.0F, -5.0F);
        this.PCback.addBox(-0.5F, 0.0F, -0.5F, 7, 10, 2, 0.0F);
        this.PCDoor = new ModelRenderer(this, 0, 28);
        this.PCDoor.setRotationPoint(-3.0F, 13.0F, -6.0F);
        this.PCDoor.addBox(-0.5F, 0.0F, -0.5F, 7, 6, 1, 0.0F);
        this.pole = new ModelRenderer(this, 37, 18);
        this.pole.setRotationPoint(0.0F, 13.0F, 4.5F);
        this.pole.addBox(-0.5F, 0.0F, -0.5F, 1, 10, 1, 0.0F);
        this.SideWing1 = new ModelRenderer(this, 0, 5);
        this.SideWing1.setRotationPoint(0.0F, 13.0F, 4.5F);
        this.SideWing1.addBox(-7.8F, 0.0F, -1.8F, 4, 4, 1, 0.0F);
        this.setRotateAngle(SideWing1, 0.0F, 0.5585053606381855F, 0.0F);
        this.CableExtension = new ModelRenderer(this, 40, 22);
        this.CableExtension.setRotationPoint(0.0F, 22.0F, -3.5F);
        this.CableExtension.addBox(-1.5F, 0.0F, 0.0F, 3, 1, 9, 0.0F);
        this.MainPanel = new ModelRenderer(this, 0, 0);
        this.MainPanel.setRotationPoint(0.0F, 13.0F, 4.5F);
        this.MainPanel.addBox(-4.0F, 0.0F, 0.5F, 8, 4, 1, 0.0F);
        this.Reflector = new ModelRenderer(this, 0, 16);
        this.Reflector.setRotationPoint(0.0F, 13.0F, 4.5F);
        this.Reflector.addBox(-0.5F, 1.4F, 1.5F, 1, 1, 3, 0.0F);
        this.Base1 = new ModelRenderer(this, 10, 0);
        this.Base1.setRotationPoint(-5.5F, 23.0F, -8.0F);
        this.Base1.addBox(0.0F, 0.0F, 0.0F, 11, 1, 16, 0.0F);
        this.SideWing2 = new ModelRenderer(this, 0, 10);
        this.SideWing2.setRotationPoint(0.0F, 13.0F, 4.5F);
        this.SideWing2.addBox(3.8F, 0.0F, -1.7F, 4, 4, 1, 0.0F);
        this.setRotateAngle(SideWing2, 0.0F, -0.5585053606381855F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.PCback.render(f5);
        this.PCDoor.render(f5);
        this.pole.render(f5);
        this.SideWing1.render(f5);
        this.CableExtension.render(f5);
        this.MainPanel.render(f5);
        this.Reflector.render(f5);
        this.Base1.render(f5);
        this.SideWing2.render(f5);
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
