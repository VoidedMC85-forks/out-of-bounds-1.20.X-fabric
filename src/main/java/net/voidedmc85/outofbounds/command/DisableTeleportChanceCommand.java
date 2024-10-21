package net.voidedmc85.outofbounds.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.voidedmc85.outofbounds.config.OutOfBoundsConfig;

public class DisableTeleportChanceCommand {

    // Method to register the disable command
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("disableTeleportChance")
                .requires(source -> source.hasPermissionLevel(2)) // Operator-only
                .then(CommandManager.argument("disable", BoolArgumentType.bool())
                        .executes(context -> {
                            boolean disable = BoolArgumentType.getBool(context, "disable");
                            OutOfBoundsConfig.setTeleportCommandEnabled(!disable); // Set enabled based on argument
                            String status = disable ? "disabled" : "enabled";
                            context.getSource().sendMessage(Text.of("Teleport chance command has been " + status + "!"));
                            return Command.SINGLE_SUCCESS;
                        })));
    }
}
