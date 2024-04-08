package knuckles.PixelmonEventIntegrationMod.PixelmonListener;

import knuckles.PixelmonEventIntegrationMod.Communication.*;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Intermediary {
    //NOT IMPLEMENTED// THIS SECTION IS THE SYSTEM TO MANAGER WHO IS LISTENING TO an EVENT TO PREVENT DATA LOSS
    private List<String> pluginsUsingMeUwU = new ArrayList<>();
    private HashMap<String,List<String>> pluginsBusy = new LinkedHashMap<>();

    public boolean addPlugin(String pluginID){
        return pluginsUsingMeUwU.add(pluginID);
    }

    private void Cleaner(){

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private  final List<PokemonEntityCommunication> pokemonEntityCommunications = new ArrayList<>();
    protected void addPokemonEntityCommunication(PokemonEntityCommunication communication){
        pokemonEntityCommunications.add(communication);
    }
    public List<PokemonEntityCommunication> getPokemonEntityCommunications(){return pokemonEntityCommunications;}
    public void clearPokemonEntityCommunication(){pokemonEntityCommunications.clear();}

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private final List<PokemonCaptureCommunication> pokemonCaptureCommunications = new ArrayList<>();
    protected void addPokemonCaptureCommunication(PokemonCaptureCommunication a){pokemonCaptureCommunications.add(a);}
    public List<PokemonCaptureCommunication> getPokemonCaptureCommunication(){return pokemonCaptureCommunications;}
    public void clearPokemonCaptureCommunication(){pokemonCaptureCommunications.clear();}

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private final List<ItemCraftingCommunication> itemCraftingCommunications = new ArrayList<>();
    protected void addItemCraftingCommunication(ItemCraftingCommunication a){
        itemCraftingCommunications.add(a);
    }
    public List<ItemCraftingCommunication> getItemCraftingCommunications(){return itemCraftingCommunications;}
    public void clearItemCraftingCommunication(){
        itemCraftingCommunications.clear();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private final List<FishingCommunication> fishingCommunications = new ArrayList<>();

    protected void addFishingCommunication(FishingCommunication a){
        fishingCommunications.add(a);
    }

    public List<FishingCommunication> getFishingCommunications(){ return fishingCommunications;}
    public void clearFishingCommunication(){fishingCommunications.clear();}

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final List<DaycareCommunication> daycareCommunications = new ArrayList<>();

    protected void addDaycareCommunication(DaycareCommunication a){
        daycareCommunications.add(a);
    }

    public List<DaycareCommunication> getDaycareCommunications(){return daycareCommunications;}
    public void clearDaycareCommunication(){daycareCommunications.clear();}

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private final List<BerryCatchCommunication> berryCatchCommunications = new ArrayList<>();

    protected void addBerryCatchCommunication(BerryCatchCommunication a) { berryCatchCommunications.add(a);}

    public List<BerryCatchCommunication> getBerryCatchCommunication(){return berryCatchCommunications;}
    public void clearBerryCatchCommunication(){berryCatchCommunications.clear();}

}
