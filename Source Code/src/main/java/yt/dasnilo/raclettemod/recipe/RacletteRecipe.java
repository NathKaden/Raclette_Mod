package yt.dasnilo.raclettemod.recipe;

import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import yt.dasnilo.raclettemod.RacletteMod;
import yt.dasnilo.raclettemod.contents.RacletteBlocks;

public class RacletteRecipe implements Recipe<Container>{

    private final ResourceLocation id;
    private final ItemStack result;
    private final Ingredient recipeItem;
    private final int cookingTime;
    private final float experience;

    public RacletteRecipe(ResourceLocation id, ItemStack output, Ingredient input, int cookingTime, float experience){
        this.id = id;
        this.result = output;
        this.recipeItem = input;
        this.cookingTime = cookingTime;
        this.experience = experience;
    }

    @Override
    public boolean matches(Container pContainer, Level pLevel) {
        if(pLevel.isClientSide()) {
            return false;
       }

       return recipeItem.test(pContainer.getItem(0));
    }

    @Override
    public ItemStack assemble(Container pContainer) {
        return result;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.recipeItem);
        return nonnulllist;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(RacletteBlocks.appareilRaclette.get());
    }

    @Override
    public ItemStack getResultItem() {
        return result.copy();
    }

    public int getCookingTime() {
        return this.cookingTime;
    }

    public float getExperience(){
        return this.experience;
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

    public static class Type implements RecipeType<RacletteRecipe>{
        private Type(){}
        public static final Type INSTANCE = new Type();
        public static final String ID = "raclette_machine";
    }

    public static class Serializer implements RecipeSerializer<RacletteRecipe>{
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(RacletteMod.MODID, "raclette_cooking");
        @Override
        public RacletteRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            JsonElement jsonelement = (JsonElement)(GsonHelper.isArrayNode(pSerializedRecipe, "ingredient") ? GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredient") : GsonHelper.getAsJsonObject(pSerializedRecipe, "ingredient"));
            Ingredient ingredient = Ingredient.fromJson(jsonelement);
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "result"));
            float experience = GsonHelper.getAsFloat(pSerializedRecipe, "experience", 0.0F);
            int cookingTime = GsonHelper.getAsInt(pSerializedRecipe, "cookingtime");
            return new RacletteRecipe(pRecipeId, result, ingredient, cookingTime, experience);
        }
        @Override
        public @Nullable RacletteRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            Ingredient ingredient = Ingredient.fromNetwork(pBuffer);
            ItemStack result = pBuffer.readItem();
            float exp = pBuffer.readFloat();
            int cook = pBuffer.readVarInt();
            return new RacletteRecipe(pRecipeId, result, ingredient, cook, exp);
        }
        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, RacletteRecipe pRecipe) {
            pRecipe.recipeItem.toNetwork(pBuffer);
            pBuffer.writeItemStack(pRecipe.getResultItem(), false);
            pBuffer.writeFloat(pRecipe.experience);
            pBuffer.writeVarInt(pRecipe.cookingTime);
        }
        
    }
    
}