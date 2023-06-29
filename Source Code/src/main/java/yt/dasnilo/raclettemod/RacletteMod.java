package yt.dasnilo.raclettemod;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.renderer.blockentity.CampfireRenderer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import yt.dasnilo.raclettemod.contents.RacletteBlockEntities;
import yt.dasnilo.raclettemod.contents.RacletteBlocks;
import yt.dasnilo.raclettemod.contents.RacletteItems;
import yt.dasnilo.raclettemod.contents.RacletteRecipeSerializers;
import yt.dasnilo.raclettemod.contents.RacletteRecipeTypes;

@Mod(RacletteMod.MODID)
public class RacletteMod
{
  public static final String MODID = "raclettemod";
  public static final Logger logger = LogUtils.getLogger();

  public RacletteMod() {
      IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
      logger.info("Mod démarré avec succés !");
      RacletteItems.register(modEventBus);
      RacletteBlocks.register(modEventBus);
      RacletteBlockEntities.register(modEventBus);
      RacletteRecipeSerializers.register(modEventBus);
      RacletteRecipeTypes.register(modEventBus);
      modEventBus.addListener(this::commonSetup);
      MinecraftForge.EVENT_BUS.register(this);
  }
  private void commonSetup(final FMLCommonSetupEvent event){}
}
