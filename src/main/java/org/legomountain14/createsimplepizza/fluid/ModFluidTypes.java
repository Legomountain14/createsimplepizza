package org.legomountain14.createsimplepizza.fluid;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import org.legomountain14.createsimplepizza.CreateSimplePizza;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.SoundAction;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;


public class ModFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, CreateSimplePizza.MOD_ID);

    // Texture paths for pizza sauce
    public static final ResourceLocation PIZZA_SAUCE_STILL_RL = ResourceLocation.fromNamespaceAndPath(CreateSimplePizza.MOD_ID, "fluid/pizza_sauce_still");
    public static final ResourceLocation PIZZA_SAUCE_FLOWING_RL = ResourceLocation.fromNamespaceAndPath(CreateSimplePizza.MOD_ID, "fluid/pizza_sauce_flow");
    public static final ResourceLocation PIZZA_SAUCE_OVERLAY_RL = ResourceLocation.fromNamespaceAndPath(CreateSimplePizza.MOD_ID, "fluid/pizza_sauce_overlay");

    // Register pizza sauce fluid type
    public static final DeferredHolder<FluidType, FluidType> PIZZA_SAUCE_FLUID_TYPE = FLUID_TYPES.register("pizza_sauce_fluid_type",
        () -> new FluidType(FluidType.Properties.create()
            .canSwim(true)
            .canDrown(true)
            .canHydrate(true)
            .lightLevel(2)
            .density(1400)
            .viscosity(1500)
            .sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK)));

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);

        // Register client extensions for fluid types
        eventBus.addListener(ModFluidTypes::registerClientExtensions);
    }

    private static void registerClientExtensions(RegisterClientExtensionsEvent event) {

        // Register client extensions for pizza sauce fluid
        event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public @NotNull ResourceLocation getStillTexture() {
                return PIZZA_SAUCE_STILL_RL;
            }

            @Override
            public @NotNull ResourceLocation getFlowingTexture() {
                return PIZZA_SAUCE_FLOWING_RL;
            }

            @Override
            public ResourceLocation getOverlayTexture() {
                return PIZZA_SAUCE_OVERLAY_RL;
            }

            @Override
            public int getTintColor() {
                return 0xFFFFFFFF; // No tint
            }

            @Override
            public @NotNull Vector3f modifyFogColor(@NotNull Camera camera, float partialTick, @NotNull ClientLevel level, int renderDistance, float darkenWorldAmount, @NotNull Vector3f fluidFogColor) {
                return new Vector3f(0.29f, 0.0f, 0.51f); // Royal Purple fog color
            }

            @Override
            public void modifyFogRender(@NotNull Camera camera, FogRenderer.@NotNull FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, @NotNull FogShape shape) {
                RenderSystem.setShaderFogColor(0.29f, 0.0f, 0.51f); // Royal Purple fog color
                RenderSystem.setShaderFogStart(0.2f); // Adjust start distance
                RenderSystem.setShaderFogEnd(1.5f);   // Adjust end distance
                RenderSystem.setShaderFogShape(FogShape.CYLINDER); // Set the fog shape
            }
        }, PIZZA_SAUCE_FLUID_TYPE.get());
    }
}