package net.voidedmc85.outofbounds.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.voidedmc85.outofbounds.config.OutOfBoundsConfig;

public class TeleportChanceCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("setTeleportChance")
                .requires(source -> OutOfBoundsConfig.isTeleportCommandEnabled()) // Check if the command is enabled
                .then(CommandManager.argument("chance", FloatArgumentType.floatArg(0, 100))
                        .executes(context -> {
                            float chance = FloatArgumentType.getFloat(context, "chance");
                            OutOfBoundsConfig.setTeleportChance(chance);
                            context.getSource().sendMessage(Text.of("Teleport chance set to " + chance + "%"));
                            return Command.SINGLE_SUCCESS;
                        })));
    }
}
