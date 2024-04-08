package knuckles.PixelmonEventIntegrationMod.PixelmonListener;

import com.pixelmonmod.pixelmon.api.events.PokemonReceivedEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import knuckles.PixelmonEventIntegrationMod.Communication.PokemonCaptureCommunication;
import knuckles.PixelmonEventIntegrationMod.Communication.PokemonEntityCommunication;
import knuckles.PixelmonEventIntegrationMod.Enum.PokemonType;
import knuckles.PixelmonEventIntegrationMod.PixelmonEventIntegrationMod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;

public class OnPixelmonReceiveEvent {
    private  final Intermediary intermediary = PixelmonEventIntegrationMod.INSTANCE.getIntermediary();
    @SubscribeEvent
    public void onPixelmonReceive(PokemonReceivedEvent e){
        Pokemon pokemon = e.getPokemon();



        if(e.getCause().equals("Fossil")){
            intermediary.addPokemonCaptureCommunication(new PokemonCaptureCommunication(e.getPlayer().getUUID(),pokemon,true));
        }

        if(e.getCause().equals("PokeBall")){
            intermediary.addPokemonCaptureCommunication(new PokemonCaptureCommunication(e.getPlayer().getUUID(),pokemon));
        }

//        if(pokemon.isLegendary()){
//            type = PokemonType.legendary;
//            LogManager.getLogger().info("legendary");
//        }else if(pokemon.isUltraBeast()){
//            type = PokemonType.ultra;
//            LogManager.getLogger().info("ultra");
//        }else if(pokemon.isMega()){
//            type = PokemonType.mega;
//            LogManager.getLogger().info("mega");
//        }else if(pokemon.isShiny()){
//            type = PokemonType.shiny;
//            LogManager.getLogger().info("shiny");
//        }
//        LogManager.getLogger().info("Sending the event to intermediary");
//        LogManager.getLogger().info("type : " +type);
        //intermediary.addPokemonCaptureCommunication(new PokemonCaptureCommunication(e.getPlayer().getUUID(),pokemon,type));
        //intermediary.addPokemonEntityCommunication(new PokemonEntityCommunication(null,pokemon,e.getPlayer().getUUID(),false));
    }
}
