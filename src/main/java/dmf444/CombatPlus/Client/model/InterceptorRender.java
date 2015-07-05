package dmf444.CombatPlus.Client.model;

import dmf444.CombatPlus.Common.TileEntity.ITickTile;
import dmf444.CombatPlus.Core.CombatPlus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;


public class InterceptorRender extends TileEntitySpecialRenderer {

    //The model of your block
    private final InterceptorModel model;
    private int ticks;

    public InterceptorRender() {
        this.model = new InterceptorModel();
    }

    private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        GL11.glPushMatrix();
        GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        ticks++;
        //The PushMatrix tells the renderer to "start" doing something.
        GL11.glPushMatrix();
        //GL11.glEnable(GL11.GL_LIGHTING);
        //This is setting the initial location.
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

        this.adjustRotatePivotViaMeta(te.getWorldObj(), (int)x, (int)y, (int)z);



        ResourceLocation textures = new ResourceLocation("combatplus:textures/blocks/InterceptorModel.png");
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);

        //This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);





        //A reference to your Model file. Again, very important.
        this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

       // float radians = (float) Math.toRadians(rangle * 1.12);
        //float scam = Math.max((float) ((float) (Math.sin(rangle / 7) * 0.2) + 0.25f), 0.25f);
        //float ram = Math.max((float) (-(((float) Math.sin(rangle / 7) * 0.2) / 2.0f) + 0.4), 0.9f);
       // GL11.glTranslatef(0.0f, ram - 0.25f, 0.0f);
        //GL11.glScaled(scam, 0.9 - 0.45, scam);

       // float ztrans = (float) (Math.cos(radians) * 0.00);
       // float ytrans = (float) -(Math.sin(radians)* 0.01);
       // GL11.glTranslatef(ztrans, 0, ytrans);
      //  GL11.glRotatef(rangle, 0, 1, 0);

        GL11.glPopMatrix();
        GL11.glPopMatrix();


    }
}