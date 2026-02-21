
package com.github.player_ix.ix_api;

import com.github.player_ix.ix_api.api.ModOfNineAbyss;
import com.github.player_ix.ix_api.init.ApiEntities;
import com.github.player_ix.ix_api.init.ApiItems;
import com.github.player_ix.ix_api.util.ResourceLocations;
import com.github.player_ix.ix_api.client.ClientAgent;
import com.github.player_ix.ix_api.init.Agent;
import com.github.player_ix.ix_api.init.ServerAgent;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(IXApi.MOD_ID)
public class IXApi implements ModOfNineAbyss {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "ix_api";
    public static final String NOIXMODAPI = "noixmodapi";
    public static final String BLUE_OCEANS = "blue_oceans";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static Agent AGENT;

    public IXApi(FMLJavaModLoadingContext context) {
        AGENT = DistExecutor.unsafeRunForDist(()-> ClientAgent::new, ()-> ServerAgent::new);
        IEventBus modEventBus = context.getModEventBus();
        ApiItems.REGISTER.register(modEventBus);
        ApiEntities.REGISTER.register(modEventBus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static boolean isNoIXApiLoaded() {
        return ModList.get().isLoaded(NOIXMODAPI);
    }

    public static boolean isBlueOceansLoaded() {
        return ModList.get().isLoaded(BLUE_OCEANS);
    }

    @Nonnull
    public static ResourceLocation location(String st) {
        return ResourceLocations.fromNamespaceAndPath(MOD_ID, st);
    }
}
