package org.legomountain14.createsimplepizza.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.legomountain14.createsimplepizza.CreateSimplePizza;
import org.legomountain14.createsimplepizza.fluid.ModFluids;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateSimplePizza.MOD_ID);

    public static final DeferredItem<Item> PIZZA_DOUGH = ITEMS.register("pizza_dough",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).build()).stacksTo(16)));

    public static final DeferredItem<Item> CHEESE_PIZZA = ITEMS.register("cheese_pizza",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(20).saturationModifier(0.5f).build()).stacksTo(16)));

    public static final DeferredItem<Item> CHEESE_PIZZA_SLICE = ITEMS.register("cheese_pizza_slice",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationModifier(0.5f).build())));

    public static final DeferredItem<Item> CHEESE = ITEMS.register("cheese",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.5f).build())));

    public static final DeferredItem<Item> PIZZA_SAUCE_BUCKET = ITEMS.register("pizza_sauce_bucket",
            () -> new BucketItem(ModFluids.SOURCE_PIZZA_SAUCE_FLUID.get(), new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}