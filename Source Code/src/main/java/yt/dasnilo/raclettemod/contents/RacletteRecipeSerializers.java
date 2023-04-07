package yt.dasnilo.raclettemod.contents;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yt.dasnilo.raclettemod.RacletteMod;
import yt.dasnilo.raclettemod.recipe.RacletteMachineRecipe;
import yt.dasnilo.raclettemod.recipe.TestBlockRecipe;

public class RacletteRecipeSerializers{
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, RacletteMod.MODID);

    public static final RegistryObject<RecipeSerializer<RacletteMachineRecipe>> RACLETTE_COOKING_SERIALIZER = SERIALIZERS.register("raclette_cooking", () -> RacletteMachineRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<TestBlockRecipe>> TEST_COOKING_SERIALIZER = SERIALIZERS.register("test_cooking", () -> TestBlockRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}