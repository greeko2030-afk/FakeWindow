package com.greekoasc.fakewindow.mixin;

import net.minecraft.client.util.Window;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Window.class)
public class WindowMixin {

    @Shadow private long handle;

    /**
     * 1. Remove borders BEFORE the window is created.
     */
    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwCreateWindow(IILjava/lang/CharSequence;JJ)J", shift = At.Shift.BEFORE))
    private void makeWindowBorderless(CallbackInfo ci) {
        GLFW.glfwWindowHint(GLFW.GLFW_DECORATED, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
    }

    /**
     * 2. Force the window size to match the monitor exactly AFTER creation.
     * This fixes the black screen issue by naturally using the default resolution.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void forceNativeResolution(CallbackInfo ci) {
        long monitor = GLFW.glfwGetPrimaryMonitor();
        GLFWVidMode vidMode = GLFW.glfwGetVideoMode(monitor);
        
        if (vidMode != null) {
            // Set window to the monitor's full native resolution
            GLFW.glfwSetWindowSize(this.handle, vidMode.width(), vidMode.height());
            
            // Snap window to the top-left corner
            GLFW.glfwSetWindowPos(this.handle, 0, 0);
            
            System.out.println("[GreekoASC] Fake Window active: Borderless at " + vidMode.width() + "x" + vidMode.height());
        }
    }
}
