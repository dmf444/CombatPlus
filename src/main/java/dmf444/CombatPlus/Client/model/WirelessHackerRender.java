package dmf444.CombatPlus.Client.model;


import dmf444.CombatPlus.Common.TileEntity.TileSetTurretBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.text.DecimalFormat;

public class WirelessHackerRender extends TileEntitySpecialRenderer {

    //The model of your block
    private final WirelessHackerModel model;

    public WirelessHackerRender() {
        this.model = new WirelessHackerModel();
    }


    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale, int destroyStage) {
        TileSetTurretBase tile = (TileSetTurretBase) te;
        //The PushMatrix tells the renderer to "start" doing something.
        GL11.glPushMatrix();
        //This is setting the initial location.
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        int i = te.getBlockMetadata();

        short short1 = 0;

        if (i == 2)
        {
            short1 = 360;
        }

        if (i == 3)
        {
            short1 = 180;
        }

        if (i == 4)
        {
            short1 = 90; //-90
        }

        if (i == 5)
        {
            short1 = -90; //90
        }
        GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);
        //Use in 1.6.2  this
        //Use in 1.6.2  this
        ResourceLocation textures = (new ResourceLocation("combatplus:textures/blocks/blockturrethacker.png"));
        //the ':' is very important
        //binding the textures
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);

        //This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        //A reference to your Model file. Again, very important.
        this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        GL11.glPushMatrix();
        if(tile.isHacking()) {
            String percentComplete = DecimalFormat.getPercentInstance().format(((double) (tile.getTicks() + 50 * tile.getTotalComplete())/ 250));

            GL11.glScaled(0.003, 0.003, 0.003);
            GL11.glTranslated(2, 75, 221);
            GL11.glRotated(-40, 1, 0, 0);
            GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.0f);
            Minecraft.getMinecraft().fontRendererObj.drawString("hacking....", -60, 350, 000000);
            GL11.glTranslated(0, 12, 0);
            Minecraft.getMinecraft().fontRendererObj.drawString(String.format("%s done", percentComplete), -60, 350, 000000);

        } else{
            GL11.glScaled(0.003, 0.003, 0.003);
            GL11.glTranslated(0, 85, 210);
            GL11.glRotated(-40, 1, 0, 0);
            GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.0f);
            Minecraft.getMinecraft().fontRendererObj.drawString("STANDBY", -60, 350, 0000000);
        }
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

}