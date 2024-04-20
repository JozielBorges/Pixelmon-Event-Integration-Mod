package knuckles.PixelmonEventIntegrationMod.PixelmonListener;

import knuckles.PixelmonEventIntegrationMod.Communication.BerryCatchCommunication;
import knuckles.PixelmonEventIntegrationMod.Communication.ItemCraftingCommunication;
import knuckles.PixelmonEventIntegrationMod.PixelmonEventIntegrationMod;
import knuckles.PixelmonEventIntegrationMod.Utils.EventsINTutils;
import net.minecraft.block.BlockState;
import net.minecraft.state.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class PlayerRightClickListener extends EventsINTutils {
    private String ageBerry = "0";

    private final Intermediary intermediary = PixelmonEventIntegrationMod.INSTANCE.getIntermediary();

    @SubscribeEvent
    public void onPlayerRightClick(PlayerInteractEvent.RightClickBlock event){
        if(!LIST_LISTENERS_ONLINE.contains(this.getClass().getName())){
            return;
        }
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
            addMyEventDataToData(this.getClass(),new BerryCatchCommunication(event.getPlayer().getUUID()));
        }else{
            ageBerry = "0";
        }
    }
    @SubscribeEvent
    public void onCraftingItem(PlayerEvent.ItemCraftedEvent event){
        if(!LIST_LISTENERS_ONLINE.contains(this.getClass().getName())){
            return;
        }
        if(event.getCrafting().getItem().getRegistryName().toString().equals("pixelmon:poke_ball")){
            addMyEventDataToData(this.getClass(),new ItemCraftingCommunication(event.getPlayer().getUUID(),event.getCrafting()));
        }
    }
}
