package yt.dasnilo.raclettemod.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.CampfireBlock;
import yt.dasnilo.raclettemod.blocks.RacletteMachineBlock;
import yt.dasnilo.raclettemod.blocks.RacletteMachineBlockEntity;
import yt.dasnilo.raclettemod.contents.RacletteBlocks;

public class RacletteMachineRenderer implements BlockEntityRenderer<RacletteMachineBlockEntity>{
   private final BlockEntityRendererProvider.Context context;

   public RacletteMachineRenderer(BlockEntityRendererProvider.Context context) {
      this.context = context;
   }

   @Override
   public void render(RacletteMachineBlockEntity be, float partialTicks, PoseStack stack, MultiBufferSource buf, int overlay, int light){
      final ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
      NonNullList<ItemStack> nonnulllist = be.getItems();
      for (ItemStack item : nonnulllist) {
         if(item != ItemStack.EMPTY){
            System.out.println(item);
         }
      }
      final LocalPlayer player = Minecraft.getInstance().player;
      final ItemStack item = nonnulllist.get(0);
      itemRenderer.renderStatic(player, item, TransformType.FIXED, false, stack, buf, Minecraft.getInstance().level, light, overlay, 0);
   }
}