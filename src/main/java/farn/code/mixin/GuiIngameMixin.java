package farn.code.mixin;

import farn.code.*;
import net.minecraft.src.*;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Shadow;
import org.lwjgl.input.Keyboard;

@Mixin(value = GuiIngame.class, remap = false)
public class GuiIngameMixin extends Gui {
    @Shadow private Minecraft mc;
    private static boolean showCoord = true;
    private static boolean showCoordBuffer;

    @Inject(method = "renderGameOverlay", at = @At(value = "TAIL"))
    private void renderGameOverlayMixin(CallbackInfo info) {
        FontRenderer fontRenderer = this.mc.fontRenderer;
        int padding = MathHelper.floor_float(this.mc.gameSettings.screenPadding.value * 25F);

        String PlayerCoordinates =  String.format("XYZ: %.3f / %.3f / %.3f", new Object[]{this.mc.thePlayer.posX, this.mc.thePlayer.posY, this.mc.thePlayer.posZ});
        if(this.mc.gameSettings.showDebugScreen.value) {
            this.drawString(fontRenderer, PlayerCoordinates,FarnModMain.LabelX + padding,FarnModMain.LabelY + padding,14737632);
        }
    }
}
