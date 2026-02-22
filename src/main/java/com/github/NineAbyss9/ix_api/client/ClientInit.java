
package com.github.NineAbyss9.ix_api.client;

import com.github.NineAbyss9.ix_api.IXApi;
import com.github.NineAbyss9.ix_api.api.renderer.BaseEntityRenderer;
import com.github.NineAbyss9.ix_api.init.ApiEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IXApi.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD,
value = Dist.CLIENT)
public class ClientInit {
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ApiEntities.DAMAGE_TESTER.get(), BaseEntityRenderer::new);
    }
}
