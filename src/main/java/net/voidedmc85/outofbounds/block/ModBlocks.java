package net.voidedmc85.outofbounds.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.voidedmc85.outofbounds.OutOfBounds;

public class ModBlocks {
    public static final Block BACKROOMS_WALL = registerBlock("backrooms_wall",
            new Block(FabricBlockSettings.copyOf(Blocks.REINFORCED_DEEPSLATE)));
    public static final Block BACKROOMS_CEILING = registerBlock("backrooms_ceiling",
            new Block(FabricBlockSettings.copyOf(Blocks.REINFORCED_DEEPSLATE)));
    public static final Block CEILING_LIGHT = registerBlock("ceiling_light",
            new Block(FabricBlockSettings.copyOf(Blocks.REINFORCED_DEEPSLATE)));
    public static final Block CEILING_LIGHT_ON = registerBlock("ceiling_light_on",
            new Block(FabricBlockSettings.copyOf(Blocks.GLOWSTONE).strength(55.0F, 1200.0F)));
    public static final Block BACKROOMS_FLOOR = registerBlock("backrooms_floor",
            new Block(FabricBlockSettings.copyOf(Blocks.REINFORCED_DEEPSLATE)));
    public static final Block CAUTION_BLOCK = registerBlock("caution_block",
            new Block(FabricBlockSettings.copyOf(Blocks.REINFORCED_DEEPSLATE)));
    public static final Block POOLROOMS_WALL = registerBlock("poolrooms_wall",
            new Block(FabricBlockSettings.copyOf(Blocks.REINFORCED_DEEPSLATE)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(OutOfBounds.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(OutOfBounds.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        OutOfBounds.LOGGER.info("Registering ModBlocks for " + OutOfBounds.MOD_ID);
    }
}
