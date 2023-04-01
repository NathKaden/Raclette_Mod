package yt.dasnilo.raclettemod.contents;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yt.dasnilo.raclettemod.RacletteMod;
import yt.dasnilo.raclettemod.recipe.RacletteMachineRecipe;

public class RacletteRecipes{
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, RacletteMod.MODID);
    public static final RegistryObject<RecipeSerializer<RacletteMachineRecipe>> RACLETTE_COOKING_SERIALIZER = SERIALIZERS.register("raclette_cooking", () -> RacletteMachineRecipe.Serializer.INSTANCE);
    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}