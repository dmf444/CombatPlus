package dmf444.CombatPlus.Client.model;


import com.google.common.base.Function;
import dmf444.CombatPlus.Common.TileEntity.ITickTile;
import dmf444.CombatPlus.Core.lib.ModInfo;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.model.TRSRTransformation;
import org.lwjgl.opengl.GL11;

public class WirelessEnergyRender extends TileEntitySpecialRenderer {

    //The model of your block
    private final WirelessEnergyModel model;
    private int ticks;
    private IModel modelo;
    private IBakedModel bakedModel;

    private IBakedModel getBakedModel() {
        // Since we cannot bake in preInit() we do lazy baking of the model as soon as we need it
        // for rendering
        if (bakedModel == null) {
            try {
                modelo = ModelLoaderRegistry.getModel(new ResourceLocation(ModInfo.MODID, "block/sphere.obj"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            bakedModel = modelo.bake(TRSRTransformation.identity(), DefaultVertexFormats.ITEM,
                new Function<ResourceLocation, TextureAtlasSprite>() {
                    @Nullable
                    @Override
                    public TextureAtlasSprite apply(@Nullable ResourceLocation input) {
                        return  Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(input.toString());
                    }
                });
        }
        return bakedModel;
    }


    public WirelessEnergyRender() {
        this.model = new WirelessEnergyModel();
    }


    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale, int destroyStage) {
      ResourceLocation textures = null;
      if(te instanceof ITickTile){
        ITickTile tile = (ITickTile) te;
        ticks = tile.getTicks();
        textures = tile.getTexture();
      }


      GlStateManager.pushMatrix();
      GlStateManager.translate((float) x + 0.5F, (float) y - 0.5, (float) z + 0.5F);
      Minecraft.getMinecraft().renderEngine.bindTexture(textures);
      this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      GlStateManager.popMatrix();

      GlStateManager.pushAttrib();
      GlStateManager.pushMatrix();
      GlStateManager.translate(x, y, z);
      GlStateManager.disableRescaleNormal();


      RenderHelper.disableStandardItemLighting();
      this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
      GlStateManager.shadeModel(GL11.GL_SMOOTH);

      GlStateManager.pushMatrix();
      GlStateManager.translate(.5, .15, .5);
      long angle = (System.currentTimeMillis() / 50) % 45;
      long angleRotate = (System.currentTimeMillis() / 10) % 360;
      float scam = Math.max((float) ((float) (Math.sin(angle / 7) * 0.2) + 0.25f), 0.25f);
      GlStateManager.scale(scam, 0.45, scam);
      GlStateManager.rotate(angleRotate, 0, 1, 0);
      RenderHelper.disableStandardItemLighting();
      this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
      if (Minecraft.isAmbientOcclusionEnabled()) {
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
      } else {
        GlStateManager.shadeModel(GL11.GL_FLAT);
      }
      World world = te.getWorld();
      GlStateManager.translate(-te.getPos().getX(), -te.getPos().getY(), -te.getPos().getZ());
      Tessellator tessellator = Tessellator.getInstance();
      tessellator.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
      Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer().renderModel(world, getBakedModel(), world.getBlockState(te.getPos()), te.getPos(), Tessellator.getInstance().getBuffer(), true);
      tessellator.draw();
      RenderHelper.enableStandardItemLighting();
      GlStateManager.popMatrix();


      GlStateManager.popMatrix();
      GlStateManager.popAttrib();



    }
}