package dmf444.CombatPlus.Client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by David on 04/07/2015.
 */
public class BlockRender implements IItemRenderer
{
    protected ModelBase model;
    protected String texturelocation;

    public BlockRender(ModelBase model, String texture){
        this.model = model;
        this.texturelocation = texture;
    }

    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        switch (type)
        {
            case ENTITY:
            case EQUIPPED:
            case INVENTORY:
            case EQUIPPED_FIRST_PERSON:
                return true;
            default:
                return false;
        }
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return false;
    }

    public void renderItem(ItemRenderType type, ItemStack var2, Object ... data)
    {
        GL11.glPushMatrix();
        switch(type){
            case ENTITY://DONE
                GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(0F, -2F, 0F);
                break;
            case EQUIPPED://DONE
                GL11.glRotatef(-180F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20F, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(0.0F, -1.3F, -0.4F);
                GL11.glScalef(1.2F, 1.2F, 1.2F);
                break;
            case EQUIPPED_FIRST_PERSON://DONE
                GL11.glRotatef(-180F, 0.0F, 0.0F, 1.0F);
                //GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
                //GL11.glRotatef(-20F, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(20F, 50F, 25F);

                break;
            case INVENTORY://DONE
                GL11.glRotatef(-180F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20F, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(0.0F, -1.3F, -0.4F);
                GL11.glScalef(1.2F, 1.2F, 1.2F);

                break;
            default://DONE
                break;
        }

        ResourceLocation textures = (new ResourceLocation(this.texturelocation));
        Minecraft.getMinecraft().renderEngine.bindTexture(textures);

        this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }
}

