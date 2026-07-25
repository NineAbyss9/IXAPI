
package com.github.NineAbyss9.ix_api.network;

import com.github.NineAbyss9.ix_api.IXApi;
import com.github.NineAbyss9.ix_api.api.annotation.ServerOnly;
import com.github.NineAbyss9.ix_api.network.packet.BossBarUpdatePacket;
import com.github.NineAbyss9.ix_api.network.packet.ClientAddParticlePacket;
import com.github.NineAbyss9.ix_api.util.ResourceLocations;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.NineAbyss9.util.function.FunctionCollector;

public class APINetwork {
    private static final String PROTOCOL = "ix_api_packet";
    private static SimpleChannel INSTANCE;
    private static int ID = 0;

    public static void register() {
        INSTANCE = NetworkRegistry.newSimpleChannel(ResourceLocations.fromNamespaceAndPath
                        (IXApi.MOD_ID, "main"),
                () -> PROTOCOL, FunctionCollector.alwaysTrue(), FunctionCollector.alwaysTrue()
        );
        INSTANCE.registerMessage(nextId(), BossBarUpdatePacket.class, BossBarUpdatePacket::encode,
                BossBarUpdatePacket::decode, BossBarUpdatePacket::handle
        );
        INSTANCE.registerMessage(nextId(), ClientAddParticlePacket.class, ClientAddParticlePacket::encode,
                ClientAddParticlePacket::decode, ClientAddParticlePacket::handle);
    }

    private static int nextId() {
        return ID++;
    }

    public static <MSG> void sendToClient(ServerPlayer player, MSG msg) {
        INSTANCE.sendTo(msg, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    @ServerOnly
    public static void sendActionBar(Player player, Component component)
    {
        ((ServerPlayer)player).connection.connection.send(new ClientboundSetActionBarTextPacket(component));
    }
}
