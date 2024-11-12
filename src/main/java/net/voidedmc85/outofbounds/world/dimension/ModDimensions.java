package net.voidedmc85.outofbounds.world.dimension;

import net.fabricmc.fabric.api.event.registry.DynamicRegistryView;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.voidedmc85.outofbounds.OutOfBounds;

import java.util.OptionalLong;

public class ModDimensions {
    public static final RegistryKey<DimensionOptions> LEVEL0_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            new Identifier(OutOfBounds.MOD_ID, "level0"));
    public static final RegistryKey<World> LEVEL0_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier(OutOfBounds.MOD_ID, "level0"));
    public static final RegistryKey<DimensionType> LEVEL0_DIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            new Identifier(OutOfBounds.MOD_ID, "level0_type"));

    public static void bootstrapType(Registerable<DimensionType> context) {
        RegistryEntry.Reference<DimensionType> register = context.register(LEVEL0_DIM_TYPE, new DimensionType(
                OptionalLong.of(12000), // fixedTime
                false, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                true, // natural
                1.0, // coordinateScale
                false, // bedWorks
                false, // respawnAnchorWorks
                0, // minY
                256, // height
                256, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                new Identifier(OutOfBounds.MOD_ID, "level0_ambience"), // effectsLocation
                1.0f, // ambientLight
                new DimensionType.MonsterSettings(
                        false, // monster_spawn_light_test
                        false, // monster_spawn_block_light_limit
                        UniformIntProvider.create(10, 15), // monster_spawn_rate
                        0 // monster_spawn_cap
                )));

    }

    public static void bootstrapType(Class<? extends DynamicRegistryView> aClass) {
    }
}