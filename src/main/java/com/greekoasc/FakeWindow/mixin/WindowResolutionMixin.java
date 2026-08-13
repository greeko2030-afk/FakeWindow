package com.greekoasc.fakewindow.mixin;

import com.greekoasc.fakewindow.FakeWindowConfig;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Window.class)
public class WindowResolutionMixin {

    /**
     * Tricks Minecraft into thinking the Framebuffer width is smaller.
     * This applies the Fake Width for rendering.
     */
    @Inject(method = "getFramebufferWidth", at = @At("HEAD"), cancellable = true)
    private void overrideFramebufferWidth(CallbackInfoReturnable<Integer> cir) {
        if (FakeWindowConfig.ENABLED) {
            cir.setReturnValue(FakeWindowConfig.FAKE_WIDTH);
        }
    }

    /**
     * Tricks Minecraft into thinking the Framebuffer height is smaller.
     * This applies the Fake Height for rendering.
     */
    @Inject(method = "getFramebufferHeight", at = @At("HEAD"), cancellable = true)
    private void overrideFramebufferHeight(CallbackInfoReturnable<Integer> cir) {
        if (FakeWindowConfig.ENABLED) {
            cir.setReturnValue(FakeWindowConfig.FAKE_HEIGHT);
        }
    }
}
