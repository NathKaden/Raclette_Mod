package yt.dasnilo.raclettemod;

import com.mojang.logging.LogUtils;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

import yt.dasnilo.raclettemod.contents.RacletteBlockEntities;
import yt.dasnilo.raclettemod.contents.RacletteBlocks;
import yt.dasnilo.raclettemod.contents.RacletteItems;
import yt.dasnilo.raclettemod.contents.RacletteMenuTypes;
import yt.dasnilo.raclettemod.contents.RacletteRecipeSerializers;
import yt.dasnilo.raclettemod.contents.RacletteRecipeTypes;
import yt.dasnilo.raclettemod.screen.RacletteMachineScreen;

// The value here should match an entry in the META-INF/mods.toml file
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
      RacletteMenuTypes.register(modEventBus);
      modEventBus.addListener(this::commonSetup);
      MinecraftForge.EVENT_BUS.register(this);
  }
  private void commonSetup(final FMLCommonSetupEvent event){}

  @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
  public static class ClientModEvents{
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event){
      MenuScreens.register(RacletteMenuTypes.RACLETTE_MACHINE_MENU.get(), RacletteMachineScreen::new);
    }
  }
}
