package yt.dasnilo.raclettemod;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import yt.dasnilo.raclettemod.contents.RacletteBlocks;
import yt.dasnilo.raclettemod.contents.RacletteItems;

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
      MinecraftForge.EVENT_BUS.register(this);
  }
  private void commonSetup(final FMLCommonSetupEvent event){}
  public void ha(){this.commonSetup(null);}
}
