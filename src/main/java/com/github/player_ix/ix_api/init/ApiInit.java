
package com.github.player_ix.ix_api.init;

import com.github.player_ix.ix_api.commands.APICommand;
import com.github.player_ix.ix_api.network.APINetwork;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ApiInit {
    private ApiInit() {
    }

    public static void onCommonSetup(FMLCommonSetupEvent event) {
        APINetwork.register();
    }

    @SubscribeEvent
    public static void onRegisterCommandEvent(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> commandDispatcher = event.getDispatcher();
        APICommand.register(commandDispatcher, event.getBuildContext());
    }
}
