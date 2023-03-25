package yt.dasnilo.raclettemod.contents;
 
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.function.Supplier;
import yt.dasnilo.raclettemod.RacletteMod;
import yt.dasnilo.raclettemod.blocks.RacletteMachineBlock;

public class RacletteBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RacletteMod.MODID);
    
    public static final RegistryObject<Block> appareilRaclette = createBlock("appareil_raclette", () -> new RacletteMachineBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.ANVIL).noOcclusion()));

    public static RegistryObject<Block> createBlock(String name, Supplier<? extends Block> supplier){
        RegistryObject<Block> block = BLOCKS.register(name, supplier);
        RacletteItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(RacletteTab.RACLETTE_TAB)));
        return block;
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
