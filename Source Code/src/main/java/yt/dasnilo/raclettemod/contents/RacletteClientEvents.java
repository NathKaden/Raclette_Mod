package yt.dasnilo.raclettemod.contents;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yt.dasnilo.raclettemod.RacletteMod;
import yt.dasnilo.raclettemod.blocks.RacletteMachineRenderer;

public class RacletteClientEvents {
    @Mod.EventBusSubscriber(modid = RacletteMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents{
        @SubscribeEvent
        public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event){
            event.registerBlockEntityRenderer(RacletteBlockEntities.RACLETTE_MACHINE.get(), RacletteMachineRenderer::new);
        }
    }
}
