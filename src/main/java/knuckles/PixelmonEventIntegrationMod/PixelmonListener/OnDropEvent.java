package knuckles.PixelmonEventIntegrationMod.PixelmonListener;

import com.pixelmonmod.pixelmon.api.events.*;
import com.pixelmonmod.pixelmon.blocks.machines.AnvilBlock;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import knuckles.PixelmonEventIntegrationMod.Communication.PokemonEntityCommunication;
import knuckles.PixelmonEventIntegrationMod.Interfaces.IEvent;
import knuckles.PixelmonEventIntegrationMod.PixelmonEventIntegrationMod;
import knuckles.PixelmonEventIntegrationMod.Utils.EventsINTutils;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OnDropEvent extends EventsINTutils {
    private  final Intermediary intermediary = PixelmonEventIntegrationMod.INSTANCE.getIntermediary();


    @SubscribeEvent
    public void onDropEvent(DropEvent event){
        if(!LIST_LISTENERS_ONLINE.contains(this.getClass().getName())){
            return;
        }
        PixelmonEntity pokemon = (PixelmonEntity) event.entity;
        if(pokemon.isBossPokemon()){
            addMyEventDataToData(this.getClass(),new PokemonEntityCommunication(pokemon,event.player.getUUID(),false,true));
            return;
        }

        addMyEventDataToData(this.getClass(),new PokemonEntityCommunication(pokemon,event.player.getUUID(),event.isTrainer()));
    }

    @SubscribeEvent
    public void onBeatTrainer(BeatTrainerEvent event){
        addMyEventDataToData(this.getClass(),new PokemonEntityCommunication(event.player.getUUID(),true));
    }
}
