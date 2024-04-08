package knuckles.PixelmonEventIntegrationMod.PixelmonListener;

import com.pixelmonmod.pixelmon.api.events.*;
import com.pixelmonmod.pixelmon.blocks.machines.AnvilBlock;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import knuckles.PixelmonEventIntegrationMod.Communication.PokemonEntityCommunication;
import knuckles.PixelmonEventIntegrationMod.PixelmonEventIntegrationMod;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;

public class OnDropEvent {
    private  final Intermediary intermediary = PixelmonEventIntegrationMod.INSTANCE.getIntermediary();

    @SubscribeEvent
    public void onDropEvent(DropEvent event){
        //if(!event.isPokemon()) return;
        PixelmonEntity pokemon = (PixelmonEntity) event.entity;

        if(pokemon.isBossPokemon()){
            intermediary.addPokemonEntityCommunication(new PokemonEntityCommunication(pokemon,event.player.getUUID(),false,true));
            return;
        }
//        if(pokemon.isBossPokemon()){
//
//        }

//        pokemon.getPokemon().isShiny();
//        pokemon.getPokemon().isLegendary();
//        pokemon.getPokemon().isMega();
//        pokemon.getPokemon().isUltraBeast();
//
//        LogManager.getLogger().info("pokemon is : shiny " +pokemon.getPokemon().isShiny());
//        LogManager.getLogger().info("pokemon is : legend " +pokemon.getPokemon().isLegendary());
//        LogManager.getLogger().info("pokemon is : mega " +pokemon.getPokemon().isMega());
//        LogManager.getLogger().info("pokemon is : ultra " +pokemon.getPokemon().isUltraBeast());

        intermediary.addPokemonEntityCommunication(new PokemonEntityCommunication(pokemon,event.player.getUUID(),event.isTrainer()));
    }
//    @SubscribeEvent
//    public void test(ApricornEvent event){
//        LogManager.getLogger().info("ApricornEvent : " +event.getApricorn());
//    }
//
//    USE THIS
//    @SubscribeEvent
//    public void onApricornCollect(ApricornEvent.Pick event){
//        LogManager.getLogger().info("ApricornEvent.Pick : " + event.getApricorn());
//    }

    @SubscribeEvent
    public void onBeatTrainer(BeatTrainerEvent event){
        intermediary.addPokemonEntityCommunication(new PokemonEntityCommunication(event.player.getUUID(),true));
    }

}
