package dmf444.CombatPlus.Common.TileEntity;

;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by David on 03/07/2015.
 */
public interface ITickTile {

    public int getTicks();

    @SideOnly(Side.CLIENT)
    public ResourceLocation getTexture();

}
