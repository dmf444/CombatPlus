package dmf444.CombatPlus.Common.TileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;

/**
 * Created by David on 03/07/2015.
 */
public interface ITickTile {

    public int getTicks();

    @SideOnly(Side.CLIENT)
    public ResourceLocation getTexture();

}
