package knuckles.PixelmonEventIntegrationMod.PixelmonListener;

import com.pixelmonmod.pixelmon.api.events.PokemonReceivedEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import knuckles.PixelmonEventIntegrationMod.Communication.PokemonCaptureCommunication;
import knuckles.PixelmonEventIntegrationMod.Communication.PokemonEntityCommunication;
import knuckles.PixelmonEventIntegrationMod.Enum.PokemonType;
import knuckles.PixelmonEventIntegrationMod.Interfaces.IEvent;
import knuckles.PixelmonEventIntegrationMod.PixelmonEventIntegrationMod;
import knuckles.PixelmonEventIntegrationMod.Utils.EventsINTutils;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class OnPixelmonReceiveEvent extends EventsINTutils {
    private  final Intermediary intermediary = PixelmonEventIntegrationMod.INSTANCE.getIntermediary();

    @SubscribeEvent
    public void onPixelmonReceive(PokemonReceivedEvent e){
        if(!LIST_LISTENERS_ONLINE.contains(this.getClass().getName())){
            return;
        }
        Pokemon pokemon = e.getPokemon();

        if(e.getCause().equals("Fossil")){
            addMyEventDataToData(this.getClass(),new PokemonCaptureCommunication(e.getPlayer().getUUID(),pokemon,true));
        }

        if(e.getCause().equals("PokeBall")){
            addMyEventDataToData(this.getClass(),new PokemonCaptureCommunication(e.getPlayer().getUUID(),pokemon));
        }
    }
}
