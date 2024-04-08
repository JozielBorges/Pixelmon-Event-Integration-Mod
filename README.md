# Pixelmon Event Integration Mod

This mod provides integration between Pixelmon servers and Bukkit/Spigot plugins by exposing specific Pixelmon events. Developers can utilize this mod to create plugins that respond to Pixelmon events, such as capturing a Pokémon or defeating a trainer.

## Features

- Listens to essential Pixelmon events, including Pokémon capture, Pokémon defeat, and more.
- Designed for seamless integration with Pixelmon servers and Bukkit/Spigot plugin development.

## Usage for DEVS

1. **Adding the Pixelmon Event Integration Mod to Your Plugin**

```xml
<dependency>
    <groupId>knuckles</groupId>
    <artifactId>PixelmonEventIntegrationMod</artifactId>
    <version>1.0</version>
    <scope>system</scope>
    <systemPath>"CHANGE ME"</systemPath>
</dependency>
```

2. Creating Your Event Executor Class: 
```java
  public class CommunicationExecutor {

    MYPLUGIN p;
    private final Intermediary intermediary;

    public CommunicationExecutor(MYPLUGIN plugin){
        p = plugin;
        intermediary = PixelmonEventIntegrationMod.INSTANCE.getIntermediary();
    }

    public final BukkitRunnable read = new BukkitRunnable() {
        @Override
        public void run() {
            //Get the list from the mod, and clear it to not send the same data(if not clear it, will be a infinite loop)
            HashSet<PokemonEntityCommunication> pokemonToDo = new HashSet<>(intermediary.getPokemonEntityCommunications());
            intermediary.clearPokemonEntityCommunication();

            //Check if the HashSet has data
            if(!pokemonToDo.isEmpty()){
                //For each data, do what we want...
                for(PokemonEntityCommunication comm : pokemonToDo){
                    pokeDropEvent(comm);
                }
            }
        }
    };
  
private void pokeDropEvent(PokemonEntityCommunication comm){
        Player player = Bukkit.getPlayer(comm.getPlayer());
        int XP = 0;
        if(comm.isNPC()){
            XP = 4;
            player.sendMessage("defeated an trainer");
        }else{
            String pkType = comm.getPokemonType();
            switch (pkType){
                case"shiny":
                    XP = 100;
                    break;
                case"legendary":
                    XP = 700;
                    break;
                case"ultra":
                    XP = 600;
                    break;
                case"common":
                    XP = 1;
                    break;
                default:
                    player.sendMessage("Default");
                    if(comm.isPokemonBoss()){
                        XP = 250;
                    }
            }
        }

        player.sendMessage("You got " + XP + " XP");
    }
   ```
3. Initializing Your Event Executor:
```java
   @EventHandler
    public void onServerLoad(ServerLoadEvent event){
        getLogger().info("Server fully loaded and now i can do my functions :D");
        CommunicationExecutor communicationExecutor = new CommunicationExecutor(this);
        communicationExecutor.read.runTaskTimer(this,0,5);
    }
```
 
4. Done!
   
## Installation

1. Download the latest release of the Pixelmon Event Integration Mod from the [Releases](https://github.com/yourusername/Pixelmon-Event-Integration-Mod/releases) page.
2. Place the downloaded JAR file into the `mods` folder of your Pixelmon server.

## Compatibility

- Requires Pixelmon mod.

## Contributing

Contributions to this mod are welcome! If you encounter any issues, have suggestions, or want to contribute code, feel free to open an issue or submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
