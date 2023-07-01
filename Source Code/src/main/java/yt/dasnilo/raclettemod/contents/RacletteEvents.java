package yt.dasnilo.raclettemod.contents;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yt.dasnilo.raclettemod.RacletteMod;
import yt.dasnilo.raclettemod.renderers.RacletteMachineRenderer;

@Mod.EventBusSubscriber(modid = RacletteMod.MODID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class RacletteEvents {
    @SubscribeEvent
  public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
    event.registerBlockEntityRenderer(RacletteBlockEntities.RACLETTE_MACHINE.get(), RacletteMachineRenderer::new);
  }
}
