package knuckles.PixelmonEventIntegrationMod.Communication;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import knuckles.PixelmonEventIntegrationMod.Enum.PokemonType;

import java.util.UUID;

public class DaycareCommunication {
    private UUID player;
    private Pokemon pokemon;

    private PokemonType type;

    public DaycareCommunication(UUID player, Pokemon pokemon) {
        this.player = player;
        this.pokemon = pokemon;
    }

    public UUID getPlayer() {
        return player;
    }
    public String getType() {
        if(pokemon.isShiny()){
            type = PokemonType.shiny;
        }else if(pokemon.isUltraBeast()){
            type = PokemonType.ultra;
        }else if(pokemon.isMega()){
            type = PokemonType.mega;
        }else if(pokemon.isLegendary()){
            type = PokemonType.legendary;
        }else{
            type = PokemonType.commom;
        }

        return type.toString();
    }
}
