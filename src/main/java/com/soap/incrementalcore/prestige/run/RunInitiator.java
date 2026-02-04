package com.soap.incrementalcore.prestige.run;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;

import com.soap.incrementalcore.prestige.PrestigeRun;

public class RunInitiator {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("start")
                        .then(Commands.literal("prestige")
                                .executes(context -> {
                                    CommandSourceStack source = context.getSource();
                                    MinecraftServer server = source.getServer();

                                    PrestigeRun run = PrestigeRun.get(server);

                                    if (run.isRunning()) {
                                        source.sendFailure(
                                                Component.literal("A prestige run is already active.")
                                        );
                                        return 0; //successful execution of command
                                    }

                                    run.beginRun();

                                    source.sendSuccess(
                                            () -> Component.literal("Prestige run started!"),
                                            true
                                    );

                                    return 1; //failure to execute command
                                })
                        )
        );
    }
}
