package net.voidedmc85.outofbounds;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.voidedmc85.outofbounds.block.ModBlocks;
import net.voidedmc85.outofbounds.config.OutOfBoundsConfig;
import net.voidedmc85.outofbounds.item.ModItemGroups;
import net.voidedmc85.outofbounds.item.ModItems;
import net.voidedmc85.outofbounds.world.dimension.ModDimensions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.registry.RegistryKey;
import java.util.List;
import java.util.Random;
import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;

public class OutOfBounds implements ModInitializer {
	public static final String MOD_ID = "outofbounds";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private final Random random = new Random();

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
		DynamicRegistrySetupCallback.EVENT.register(registryManager -> {
			ModDimensions.bootstrapType(registryManager.getClass());
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			net.voidedmc85.outofbounds.command.TeleportChanceCommand.register(dispatcher);
			net.voidedmc85.outofbounds.command.DisableTeleportChanceCommand.register(dispatcher);
		});

		SoundEvent LEVEL0_AMBIENCE_SOUND = SoundEvent.of(new Identifier(MOD_ID, "level0_ambience"));
		Registry.register(Registries.SOUND_EVENT, new Identifier(MOD_ID, "level0_ambience"), LEVEL0_AMBIENCE_SOUND);

		ServerTickEvents.END_SERVER_TICK.register(server -> {
			List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();
			for (ServerPlayerEntity player : players) {
				if (player.isSprinting() && player.getWorld().getRegistryKey() != RegistryKey.of(RegistryKeys.WORLD, new Identifier(MOD_ID, "level0"))) {
					handleSprinting(player);
				}
			}
		});
	}

	private void handleSprinting(ServerPlayerEntity player) {
		float chance = OutOfBoundsConfig.getTeleportChance();
		LOGGER.info("Teleport chance: ", + chance);
		if (random.nextFloat() < (chance / 100.0f)) {
			LOGGER.info("Teleporting player to dimension...");
			teleportPlayer(player);
		}
	}

	private void teleportPlayer(ServerPlayerEntity player) {
		RegistryKey<World> customDimensionKey = RegistryKey.of(RegistryKeys.WORLD, new Identifier(MOD_ID, "level0"));
		ServerWorld targetWorld = player.getServer().getWorld(customDimensionKey);

		if (targetWorld != null) {
			// Define coordinates
			int targetX = 0;
			int targetZ = 0;

			// Define height limits
			int maxHeight = 100;
			int minHeight = 20;
			int targetY = maxHeight;
			BlockPos targetPos;

			// Search for a solid block to teleport
			while (targetY >= minHeight) {
				targetPos = new BlockPos(targetX, targetY, targetZ);
				if (!targetWorld.isAir(targetPos)) {
					player.teleport(targetWorld, targetX, targetY + 1, targetZ, player.getYaw(), player.getPitch());
					return;
				}
				targetY--;
			}

			LOGGER.warn("No solid block found at coordinates (0,0) in the custom dimension between heights " + minHeight + " and " + maxHeight + "!");
		} else {
			LOGGER.error("Target world does not exist!");
		}
	}
}