package yt.dasnilo.raclettemod.contents;


import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yt.dasnilo.raclettemod.RacletteMod;

public class RacletteItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RacletteMod.MODID);
    public static final RegistryObject<Item> COUPELLE = ITEMS.register("coupelle", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> COUPELLE_SALE = ITEMS.register("coupelle_sale", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    // Nourritures
    public static final RegistryObject<Item> FROMAGE = ITEMS.register("fromage", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));
    public static final RegistryObject<Item> COUPELLE_FROMAGE = ITEMS.register("coupelle_fromage", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
