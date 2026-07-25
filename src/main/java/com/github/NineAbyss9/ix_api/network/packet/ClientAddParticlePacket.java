
package com.github.NineAbyss9.ix_api.network.packet;

import com.github.NineAbyss9.ix_api.util.ParticleUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public class ClientAddParticlePacket
implements Packet<ClientGamePacketListener>
{
    private final double x;
    private final double y;
    private final double z;
    private final float xDist;
    private final float yDist;
    private final float zDist;
    private final float maxSpeed;
    private final int count;
    private final boolean overrideLimiter;
    private final ParticleOptions particle;

    public <T extends ParticleOptions> ClientAddParticlePacket(T pParticle, boolean pForceAdd,
                                                               double pX, double pY, double pZ, float pXDist, float pYDist, float pZDist,
                                                               float pMaxSpeed, int pCount) {
        this.particle = pParticle;
        this.overrideLimiter = pForceAdd;
        this.x = pX;
        this.y = pY;
        this.z = pZ;
        this.xDist = pXDist;
        this.yDist = pYDist;
        this.zDist = pZDist;
        this.maxSpeed = pMaxSpeed;
        this.count = pCount;
    }

    public ClientAddParticlePacket(FriendlyByteBuf buf) {
        ParticleType<?> particletype = buf.readById(BuiltInRegistries.PARTICLE_TYPE);
        this.overrideLimiter = buf.readBoolean();
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        this.xDist = buf.readFloat();
        this.yDist = buf.readFloat();
        this.zDist = buf.readFloat();
        this.maxSpeed = buf.readFloat();
        this.count = buf.readInt();
        this.particle = this.readParticle(buf, particletype);
    }

    private <T extends ParticleOptions> T readParticle(FriendlyByteBuf buf, ParticleType<T> type) {
        return type.getDeserializer().fromNetwork(type, buf);
    }

    public void write(FriendlyByteBuf p_132313_) {
        p_132313_.writeId(BuiltInRegistries.PARTICLE_TYPE, this.particle.getType());
        p_132313_.writeBoolean(this.overrideLimiter);
        p_132313_.writeDouble(this.x);
        p_132313_.writeDouble(this.y);
        p_132313_.writeDouble(this.z);
        p_132313_.writeFloat(this.xDist);
        p_132313_.writeFloat(this.yDist);
        p_132313_.writeFloat(this.zDist);
        p_132313_.writeFloat(this.maxSpeed);
        p_132313_.writeInt(this.count);
        this.particle.writeToNetwork(p_132313_);
    }

    public boolean isOverrideLimiter() {
        return this.overrideLimiter;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public float getXDist() {
        return this.xDist;
    }

    public float getYDist() {
        return this.yDist;
    }

    public float getZDist() {
        return this.zDist;
    }

    public float getMaxSpeed() {
        return this.maxSpeed;
    }

    public int getCount() {
        return this.count;
    }

    public ParticleOptions getParticle() {
        return this.particle;
    }

    public static void encode(ClientAddParticlePacket packet, FriendlyByteBuf buffer) {
        packet.write(buffer);
    }

    public static ClientAddParticlePacket decode(FriendlyByteBuf buffer) {
        return new ClientAddParticlePacket(buffer);
    }

    public static void handle(ClientAddParticlePacket pPacket, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            if (ctx.get().getDirection().getReceptionSide() != LogicalSide.CLIENT)
            {
                return;
            }
            Minecraft minecraft = Minecraft.getInstance();
            ClientLevel level = minecraft.level;
            if (level == null) return;
            ThreadLocalRandom random = ThreadLocalRandom.current();
            for (int i = 0;i < pPacket.getCount();i++) {
                double d1 = random.nextGaussian() * (double)pPacket.xDist;
                double d3 = random.nextGaussian() * (double)pPacket.yDist;
                double d5 = random.nextGaussian() * (double)pPacket.zDist;
                double d6 = random.nextGaussian() * (double)pPacket.maxSpeed;
                double d7 = random.nextGaussian() * (double)pPacket.maxSpeed;
                double d8 = random.nextGaussian() * (double)pPacket.maxSpeed;
                level.addParticle(pPacket.particle, pPacket.overrideLimiter, pPacket.x + d1,
                        pPacket.y + d3, pPacket.z + d5, d6, d7, d8);
            }
        });
        ctx.get().setPacketHandled(true);
    }

    public void handle(ClientGamePacketListener listener) {
    }
}
