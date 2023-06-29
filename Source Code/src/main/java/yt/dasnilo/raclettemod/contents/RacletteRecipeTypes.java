package yt.dasnilo.raclettemod.contents;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yt.dasnilo.raclettemod.RacletteMod;
import yt.dasnilo.raclettemod.recipe.RacletteRecipe;

public class RacletteRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, RacletteMod.MODID);

    public static final RegistryObject<RecipeType<RacletteRecipe>> RACLETTE_COOKING_TYPE = TYPES.register("raclette_cooking", () -> RacletteRecipe.Type.INSTANCE);    
    
    public static void register(IEventBus eventBus){
        TYPES.register(eventBus);
    }    
}