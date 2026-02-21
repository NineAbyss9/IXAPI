
package com.github.NineAbyss9.ix_api;

import com.github.NineAbyss9.ix_api.api.ModOfNineAbyss;
import com.github.NineAbyss9.ix_api.client.ClientAgent;
import com.github.NineAbyss9.ix_api.init.ApiEntities;
import com.github.NineAbyss9.ix_api.init.ApiItems;
import com.github.NineAbyss9.ix_api.init.ServerAgent;
import com.github.NineAbyss9.ix_api.util.ResourceLocations;
import com.github.NineAbyss9.ix_api.init.ApiAgent;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import javax.annotation.Nonnull;

@Mod("ix_api")
public class IXApi implements ModOfNineAbyss {
    public static final String MOD_ID = "ix_api";
    public static final String NOIXMODAPI = "noixmodapi";
    public static final String BLUE_OCEANS = "blue_oceans";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static ApiAgent AGENT;

    public IXApi(FMLJavaModLoadingContext context) {
        AGENT = DistExecutor.unsafeRunForDist(()-> ClientAgent::new, ()-> ServerAgent::new);
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::addTabs);
        ApiItems.REGISTER.register(modEventBus);
        ApiEntities.REGISTER.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void addTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS)
            event.accept(ApiItems.TESTER_SPAWN_EGG.get());
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
