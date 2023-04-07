package yt.dasnilo.raclettemod.recipe;

import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import yt.dasnilo.raclettemod.RacletteMod;
import yt.dasnilo.raclettemod.contents.RacletteBlocks;

public class TestBlockRecipe implements Recipe<SimpleContainer>{

    private final ResourceLocation id;
    private final ItemStack result;
    private final NonNullList<Ingredient> recipeItems;
    private final int cookingTime;
    private final float experience;

    public TestBlockRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> inputs, int cookingTime, float experience){
        this.id = id;
        this.result = output;
        this.recipeItems = inputs;
        this.cookingTime = cookingTime;
        this.experience = experience;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if(pLevel.isClientSide()) {
            return false;
       }

       return recipeItems.get(0).test(pContainer.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        return result;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(RacletteBlocks.testBlock.get());
    }

    @Override
    public ItemStack getResultItem() {
        return result.copy();
    }

    public int getCookingTime() {
        return this.cookingTime;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<TestBlockRecipe>{
        private Type(){}
        public static final Type INSTANCE = new Type();
        public static final String ID = "test_block";
    }

    public static class Serializer implements RecipeSerializer<TestBlockRecipe>{
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(RacletteMod.MODID, "test_cooking");
        @Override
        public TestBlockRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "result"));
            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }
            float experience = GsonHelper.getAsFloat(pSerializedRecipe, "experience", 0.0F);
            int cookingTime = GsonHelper.getAsInt(pSerializedRecipe, "cookingtime");
            return new TestBlockRecipe(pRecipeId, result, inputs, cookingTime, experience);
        }
        @Override
        public @Nullable TestBlockRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);
            for(int i=0;i<inputs.size();i++){
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack result = pBuffer.readItem();
            float exp = pBuffer.readFloat();
            int cook = pBuffer.readVarInt();
            return new TestBlockRecipe(pRecipeId, result, inputs, cook, exp);
        }
        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, TestBlockRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.getIngredients().size());
            for(Ingredient ing : pRecipe.getIngredients()){
                ing.toNetwork(pBuffer);
            }
            pBuffer.writeItemStack(pRecipe.getResultItem(), false);
            pBuffer.writeFloat(pRecipe.experience);
            pBuffer.writeVarInt(pRecipe.cookingTime);
        }
        
    }
    
}