package yt.dasnilo.raclettemod.contents;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yt.dasnilo.raclettemod.RacletteMod;
import yt.dasnilo.raclettemod.blocks.RacletteMachineBlockEntity;

public class RacletteBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RacletteMod.MODID);
    
    public static final RegistryObject<BlockEntityType<RacletteMachineBlockEntity>> RACLETTE_MACHINE = BLOCK_ENTITIES.register("appareil_raclette", () -> BlockEntityType.Builder.of(RacletteMachineBlockEntity::new, RacletteBlocks.appareilRaclette.get()).build(null));
    
    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
