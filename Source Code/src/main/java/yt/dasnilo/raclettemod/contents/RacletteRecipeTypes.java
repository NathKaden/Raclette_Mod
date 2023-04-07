package yt.dasnilo.raclettemod.contents;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yt.dasnilo.raclettemod.RacletteMod;
import yt.dasnilo.raclettemod.recipe.RacletteMachineRecipe;
import yt.dasnilo.raclettemod.recipe.TestBlockRecipe;

public class RacletteRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, RacletteMod.MODID);

    public static final RegistryObject<RecipeType<RacletteMachineRecipe>> RACLETTE_COOKING_TYPE = TYPES.register("raclette_cooking", () -> RacletteMachineRecipe.Type.INSTANCE);
    public static final RegistryObject<RecipeType<TestBlockRecipe>> TEST_COOKING_TYPE = TYPES.register("test_cooking", () -> TestBlockRecipe.Type.INSTANCE);
    
    public static void register(IEventBus eventBus){
        TYPES.register(eventBus);
    }    
}
