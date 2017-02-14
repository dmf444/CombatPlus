package dmf444.CombatPlus.Client.gui;


import dmf444.CombatPlus.Common.TileEntity.*;
import dmf444.CombatPlus.Common.container.*;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiEnergyCreator extends GuiContainer {
    TileEnergyCreator te;


    public GuiEnergyCreator(InventoryPlayer inventoryPlayer, TileEnergyCreator tileEntity) {
        super(new ContainerEnergyCreator(inventoryPlayer, tileEntity));
        te = tileEntity;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {
        //draw text and stuff here
        //the parameters for drawString are: string, x, y, color
        fontRendererObj.drawString("Redstone Liquifactor", 8, 6, 4210752);
        //draws "Inventory" or your regional equivalent
        fontRendererObj.drawString(I18n.format("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        //draw your Gui here, only thing you need to change is the path

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.renderEngine.bindTexture(new ResourceLocation("combatplus:gui/EnergyCreator.png"));
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        // This is where you can draw the arrow-thingy
        // this.drawTexturedModalRect(renderx, rendery, positionx, positiony, sizetodraww, sizetodrawh)
    		/*
    		so:
    		renderx and y : where to draw
    		positionx and y : where in the image to start drawing
    		sizetodraww and h : how large to draw
    		*/
        // Example: this.drawTexturedModalRect(100, 100, 256, 0, 50, 10, 50, this.tileentity.charge)
        int drawheight = (te.getEnergyStored(EnumFacing.DOWN) * 42) / te.getMaxEnergyStored(EnumFacing.DOWN);
            this.drawTexturedModalRect(x + 140, y + 21, 176, 0, 14, drawheight);

}

}
