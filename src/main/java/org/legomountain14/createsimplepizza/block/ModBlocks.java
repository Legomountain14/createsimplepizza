package org.legomountain14.createsimplepizza.block;

import org.legomountain14.createsimplepizza.CreateSimplePizza;
import org.legomountain14.createsimplepizza.fluid.ModFluids;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static org.legomountain14.createsimplepizza.item.ModItems.ITEMS;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(CreateSimplePizza.MOD_ID);

    // Pizza Sauce Fluid Block
    public static final DeferredBlock<LiquidBlock> PIZZA_SAUCE = BLOCKS.register("pizza_sauce",
            () -> new LiquidBlock(ModFluids.SOURCE_PIZZA_SAUCE_FLUID.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noCollission()));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}