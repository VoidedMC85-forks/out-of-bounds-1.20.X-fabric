package net.voidedmc85.outofbounds.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.voidedmc85.outofbounds.OutOfBounds;

public class ModItems {
    public static final Item XENON_MARBLE = registerItem("xenon_marble", new Item(new FabricItemSettings()));
    public static final Item SMILER_REPELLENT = registerItem("smiler_repellent", new Item(new FabricItemSettings()));


    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(XENON_MARBLE);
        entries.add(SMILER_REPELLENT);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(OutOfBounds.MOD_ID, name), item);
    }

    public static void registerModItems() {
        OutOfBounds.LOGGER.info("Registering Mod Items for " + OutOfBounds.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}