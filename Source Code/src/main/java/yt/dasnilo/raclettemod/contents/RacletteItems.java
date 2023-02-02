package yt.dasnilo.raclettemod.contents;


import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yt.dasnilo.raclettemod.RacletteMod;

import static yt.dasnilo.raclettemod.contents.RacletteTab.RACLETTE_TAB;

public class RacletteItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RacletteMod.MODID);
    // Items basique
    public static final RegistryObject<Item> COUPELLE = ITEMS.register("coupelle", () -> new Item(new Item.Properties().tab(RACLETTE_TAB)));
    public static final RegistryObject<Item> COUPELLE_SALE = ITEMS.register("coupelle_sale", () -> new Item(new Item.Properties().tab(RACLETTE_TAB)));
    // Nourritures
    public static final RegistryObject<Item> FROMAGE = ITEMS.register("fromage", () -> new Item(new Item.Properties().tab(RACLETTE_TAB)));
    public static final RegistryObject<Item> TRANCHE_FROMAGE = ITEMS.register("tranche_fromage", () -> new Item(new Item.Properties().tab(RACLETTE_TAB)));
    public static final RegistryObject<Item> COUPELLE_FROMAGE = ITEMS.register("coupelle_fromage", () -> new Item(new Item.Properties().tab(RACLETTE_TAB)));
    public static final RegistryObject<Item> PATATE_EPLUCHEE = ITEMS.register("patate_epluchee", () -> new Item(new Item.Properties().tab(RACLETTE_TAB)));
    public static final RegistryObject<Item> COUPELLE_FROMAGE_FONDU = ITEMS.register("coupelle_fromage_fondu", () -> new Item(new Item.Properties().tab(RACLETTE_TAB)));
    public static final RegistryObject<Item> RACLETTE = ITEMS.register("raclette", () -> new Item(new Item.Properties().tab(RACLETTE_TAB)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
