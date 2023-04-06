package yt.dasnilo.raclettemod.contents;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yt.dasnilo.raclettemod.RacletteMod;

public class RacletteRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, RacletteMod.MODID);
    public static void register(IEventBus eventBus){
        RECIPE_TYPES.register(eventBus);
    }
    
}
