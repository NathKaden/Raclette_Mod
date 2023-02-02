package yt.dasnilo.raclettemod.contents;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class RacletteTab {
    public static final CreativeModeTab RACLETTE_TAB = new CreativeModeTab("raclettemod") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(RacletteItems.COUPELLE_FROMAGE_FONDU.get());
        }
    };
}
