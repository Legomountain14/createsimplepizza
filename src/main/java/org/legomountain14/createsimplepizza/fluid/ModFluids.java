package org.legomountain14.createsimplepizza.fluid;

import org.legomountain14.createsimplepizza.CreateSimplePizza;
import org.legomountain14.createsimplepizza.block.ModBlocks;
import org.legomountain14.createsimplepizza.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(Registries.FLUID, CreateSimplePizza.MOD_ID);
    //Pizza Sauce Fluid
    public static final DeferredHolder<Fluid, FlowingFluid> SOURCE_PIZZA_SAUCE_FLUID = FLUIDS.register("pizza_sauce_fluid",
        () -> new BaseFlowingFluid.Source(ModFluids.PIZZA_SAUCE_FLUID_PROPERTIES));
    public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_PIZZA_SAUCE_FLUID = FLUIDS.register("flowing_pizza_sauce",
        () -> new BaseFlowingFluid.Flowing(ModFluids.PIZZA_SAUCE_FLUID_PROPERTIES));

    public static final BaseFlowingFluid.Properties PIZZA_SAUCE_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(
        ModFluidTypes.PIZZA_SAUCE_FLUID_TYPE, SOURCE_PIZZA_SAUCE_FLUID, FLOWING_PIZZA_SAUCE_FLUID)
            .slopeFindDistance(3)
            .levelDecreasePerBlock(2)
            .block(ModBlocks.PIZZA_SAUCE)
            .bucket(ModItems.PIZZA_SAUCE_BUCKET)
            .tickRate(25);


    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
