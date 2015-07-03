package dmf444.CombatPlus.Client.model;


import dmf444.CombatPlus.Common.TileEntity.TileInfiniteEnergy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class WirelessEnergyRender extends TileEntitySpecialRenderer {

    //The model of your block
    private final WirelessEnergyModel model;
    private final SphereModel Smodel = new SphereModel();
    private int ticks;

    public WirelessEnergyRender() {
        this.model = new WirelessEnergyModel();
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
        //This is setting the initial location.
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

        this.adjustRotatePivotViaMeta(te.getWorldObj(), (int)x, (int)y, (int)z);
        //Use in 1.6.2  this
        //Use in 1.6.2  this
        ResourceLocation textures = null;
        if(te instanceof TileInfiniteEnergy){

        }else {
            textures = (new ResourceLocation("combatplus:textures/blocks/WirelessEnergy.png"));
        }
        //the ':' is very important
        //binding the textures
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);

        //This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        //A reference to your Model file. Again, very important.
        this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);


        float zoffset = 0.871f;
        float yoffset = 0.871f;
        float xoffset = 0.0255f;
        // GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);
       // GL11.glRotatef(0F, 0, 1, 0);
        //GL11.glTranslatef(xoffset + 0, yoffset + -0.95F, zoffset + -1.9F);

        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("combatplus:textures/blocks/sphere.png"));
        float rangle = ticks;
        float radians = (float) Math.toRadians(rangle * 1.12);
        float scam = Math.max((float) ((float) (Math.sin(rangle / 7) * 0.2) + 0.2f), 0f);
        float ram = Math.max((float) (-(((float) Math.sin(rangle / 7) * 0.2) / 2.0f) + 0.4), 0);
        GL11.glTranslatef(0.0f, ram + 0.4f, 0.0f);
        GL11.glScaled(scam, scam, scam);

        float ztrans = (float) (Math.cos(radians) * 0.41);
        float ytrans = (float) -(Math.sin(radians)* 0.41);
        GL11.glTranslatef(ztrans, 0, ytrans);
        GL11.glRotatef(rangle, 0, 1, 0);
        this.Smodel.render();
        //Tell it to stop rendering for both the PushMatrix's
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
        GL11.glPopMatrix();

        if(ticks >= 360){
            ticks = 0;
        }
    }
}