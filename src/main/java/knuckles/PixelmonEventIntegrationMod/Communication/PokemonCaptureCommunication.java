package knuckles.PixelmonEventIntegrationMod.Communication;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import knuckles.PixelmonEventIntegrationMod.Enum.PokemonType;
import org.apache.logging.log4j.LogManager;

import java.util.UUID;

public class PokemonCaptureCommunication {
    private final UUID player;
    private final Pokemon pokemon;
    private boolean isFossil = false;
    public PokemonCaptureCommunication(UUID player, Pokemon pokemon) {
        this.player = player;
        this.pokemon = pokemon;
    }

    public PokemonCaptureCommunication(UUID player, Pokemon pokemon, boolean isFossil) {
        this.player = player;
        this.pokemon = pokemon;
        this.isFossil = isFossil;
    }

    public UUID getPlayer(){
        return player;
    }
    public String getPlayerString(){
        return player.toString();
    }


    public String getPokemonType(){
        //LogManager.getLogger().info("Sending getPokemonType");
        if(pokemon.isShiny()){
            return "shiny";
        }else if(pokemon.isUltraBeast()){
            return "ultra";
        }else if(pokemon.isLegendary()){
            return "legendary";
        }else{
            return "common";
        }
    }
    public boolean isFossil(){
        return isFossil;
    }

    public boolean isFossilShiny(){
        if(pokemon.isShiny()){
            return true;
        }
        return false;
    }
}
