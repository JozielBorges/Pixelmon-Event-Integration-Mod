package knuckles.PixelmonEventIntegrationMod.PixelmonListener;

import knuckles.PixelmonEventIntegrationMod.Communication.BerryCatchCommunication;
import knuckles.PixelmonEventIntegrationMod.Communication.ItemCraftingCommunication;
import knuckles.PixelmonEventIntegrationMod.PixelmonEventIntegrationMod;
import net.minecraft.block.BlockState;
import net.minecraft.state.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Collection;

public class PlayerRightClickListener {
    private String ageBerry = "0";
    private int pokeBallCount = 0;

    private final Intermediary intermediary = PixelmonEventIntegrationMod.INSTANCE.getIntermediary();

    @SubscribeEvent
    public void onPlayerRightClick(PlayerInteractEvent.RightClickBlock event){
        if(!event.getPlayer().getMainHandItem().isEmpty()){return;}
        BlockPos blockPos = event.getPos();

        BlockState blockState = event.getWorld().getBlockState(blockPos);

        if(ageBerry.equals("0")){
            Collection<Property<?>> properties = blockState.getProperties();
            for(Property<?> property : properties){
                Comparable<?> value = blockState.getValue(property);
                if(property.getName().equals("age")){
                    ageBerry = value.toString();
                }
            }
        }
        if (ageBerry.equals("2")){
            ageBerry = "0";
            intermediary.addBerryCatchCommunication(new BerryCatchCommunication(event.getPlayer().getUUID()));
        }else{
            ageBerry = "0";
        }
        //intermediary.addBlockCommunication(new RightClickBlockCommunication(event.getPos(), event.getPlayer().getUUID()));
    }
    @SubscribeEvent
    public void onCraftingItem(PlayerEvent.ItemCraftedEvent event){
        if(event.getCrafting().getItem().getRegistryName().toString().equals("pixelmon:poke_ball")){
            intermediary.addItemCraftingCommunication(new ItemCraftingCommunication(event.getPlayer().getUUID(),event.getCrafting()));
        }
        //intermediary.addItemCraftingCommunication(pokeballItem);
    }
}
