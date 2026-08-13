package com.greekoasc.fakewindow.mixin;

import net.minecraft.client.util.Window;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Window.class)
public class WindowMixin {

    /**
     * Injects code right before GLFW creates the window in Minecraft.
     * This makes the window borderless (Fake Window concept).
     */
    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwCreateWindow(IILjava/lang/CharSequence;JJ)J", shift = At.Shift.BEFORE))
    private void makeWindowBorderless(CallbackInfo ci) {
        
        // Remove the window border and title bar
        GLFW.glfwWindowHint(GLFW.GLFW_DECORATED, GLFW.GLFW_FALSE);
        
        // Prevent the window from being resized manually by the user
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
        
        System.out.println("[GreekoASC] Window hints modified: Borderless Fake Window Applied.");
    }
}
