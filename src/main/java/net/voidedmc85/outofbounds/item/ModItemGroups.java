package net.voidedmc85.outofbounds.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.voidedmc85.outofbounds.OutOfBounds;
import net.voidedmc85.outofbounds.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup BACKROOMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(OutOfBounds.MOD_ID, "backrooms"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.backrooms"))
                    .icon(() -> new ItemStack(ModBlocks.BACKROOMS_WALL)).entries((displayContext, entries) -> {
                        entries.add(ModItems.XENON_MARBLE);
                        entries.add(ModItems.SMILER_REPELLENT);

                        entries.add(ModBlocks.BACKROOMS_WALL);
                        entries.add(ModBlocks.CEILING_LIGHT_ON);
                        entries.add(ModBlocks.CEILING_LIGHT);
                        entries.add(ModBlocks.BACKROOMS_CEILING);
                        entries.add(ModBlocks.BACKROOMS_FLOOR);
                        entries.add(ModBlocks.POOLROOMS_WALL);
                        entries.add(ModBlocks.CAUTION_BLOCK);

                    }).build());


    public static void registerItemGroups() {
        OutOfBounds.LOGGER.info("Registering Item Groups for " + OutOfBounds.MOD_ID);
    }
}
