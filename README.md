# Pixelmon Event Integration Mod

This mod provides integration between Pixelmon servers and Bukkit/Spigot plugins by exposing specific Pixelmon events. Developers can utilize this mod to create plugins that respond to Pixelmon events, such as capturing a Pokémon or defeating a trainer.

## Features

- Listens to essential Pixelmon events, including Pokémon capture, Pokémon defeat, and more.
- Designed for seamless integration with Pixelmon servers and Bukkit/Spigot plugin development.

## HOW IT WORKS?
The mod registers listeners during the Forge load process.
When a plugin registers itself with the mod,
all the events designated for listening are stored in memory.
The plugin can then retrieve these events using the specific function.
> > getCommunication()
> 
> Example: my registered class is asking for the `OnDropEvent`(When a pokemon is defeated) and sending the class we want to be returned `PokemonEntityCommunication` 
>
> > getCommunication(this.getClass(), OnDropEvent.class, PokemonEntityCommunication.class)

>### Note: The mod will not return the data if the event and communication are not compatible. Refer to the list below for better understanding.

>  ## LIST OF EVENTS AND COMMUNICATION AVAILABLE
>
> > onDayCare --> DaycareCommunication
>
> >  onDropEvent --> PokemonEntityCommunication
>
> >  onFishing --> FishingCommunication
>
> >  onPixelmonReceive --> PokemonCaptureCommunication
>
> >  onPlayerRightClick --> BerryCatchCommunication & ItemCraftingCommunication
> 

## Usage for DEVS

1. **Adding the Pixelmon Event Integration Mod to Your Plugin**

```xml
<dependency>
    <groupId>knuckles</groupId>
    <artifactId>PixelmonEventIntegrationMod</artifactId>
    <version>2.0</version>
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
        // REGISTER THE EVENTS YOU WANT TO LISTEN
        intermediary.registerListener(this.getClass(), OnDropEvent.class);
        intermediary.registerListener(this.getClass(), PlayerRightClickListener.class);
    }

    public final BukkitRunnable read = new BukkitRunnable() {
        @Override
        public void run() {
            
            intermediary.getCommunication(this.getClass(), OnDropEvent.class, PokemonEntityCommunication.class).forEach(o -> {
                // THE MOD RETURN THE SAME CLASS YOU SET ABOVE (PokemonEntityCommunication) AS JAVA OBJECT CLASS
                if(o instanceof PokemonEntityCommunication){
                    PokemonEntityCommunication b = (PokemonEntityCommunication) o;
                    pokeDropEvent(b);
                }
            });
            
            intermediary.getCommunication(this.getClass(), PlayerRightClickListener.class, BerryCatchCommunication.class).forEach(o -> {
                if(o instanceof BerryCatchCommunication){
                    BerryCatchCommunication b = (BerryCatchCommunication) o;
                    Player player = Bukkit.getPlayer(b.getPlayer());

                    player.sendMessage("You got berry");
                }
            });
            intermediary.getCommunication(this.getClass(), PlayerRightClickListener.class, ItemCraftingCommunication.class).forEach(o -> {
                if(o instanceof ItemCraftingCommunication){
                    ItemCraftingCommunication b = (ItemCraftingCommunication) o;
                    Player player = Bukkit.getPlayer(b.getPlayer());

                    player.sendMessage("You got an item " + b.getItem());
                }
            });
            
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
