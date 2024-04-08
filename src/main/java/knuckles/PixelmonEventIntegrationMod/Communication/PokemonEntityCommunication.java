package knuckles.PixelmonEventIntegrationMod.Communication;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import knuckles.PixelmonEventIntegrationMod.Enum.PokemonType;

import java.util.UUID;

public class PokemonEntityCommunication {
    private PixelmonEntity pixelmonEntity;
    private final UUID player;
    private final boolean isNPC;
    private boolean isBOSS;

    public PokemonEntityCommunication(PixelmonEntity poke, UUID player, boolean isNPC) {
        this.pixelmonEntity = poke;
        this.player = player;
        this.isNPC = isNPC;
    }
    public PokemonEntityCommunication(PixelmonEntity poke, UUID player, boolean isNPC,boolean isBOSS) {
        this.pixelmonEntity = poke;
        this.player = player;
        this.isNPC = isNPC;
        this.isBOSS = isBOSS;
    }

    public PokemonEntityCommunication(UUID player, boolean isNPC) {
        this.player = player;
        this.isNPC = isNPC;
    }

    public String getBossTier(){
        if(!isNPC){
            return pixelmonEntity.getBossTier().getName();
        }
        return "npc";
    }

    public boolean isPokemonBoss(){
        return isBOSS;
    }
    public boolean isNPC(){
        return isNPC;
    }

    /*
    @
     */
    public String getPokemonType(){
        Pokemon pk = pixelmonEntity.getPokemon();
        if(pk.isShiny()){
            return "shiny";
        }else if(pk.isUltraBeast()){
            return "ultra";
        }else if(pk.isLegendary()){
            return "legendary";
        }else{
            return "common";
        }
    }


    public UUID getPlayer(){
        return player;
    }
}
