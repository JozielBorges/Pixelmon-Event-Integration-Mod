package knuckles.PixelmonEventIntegrationMod;

import com.pixelmonmod.pixelmon.Pixelmon;
import knuckles.PixelmonEventIntegrationMod.PixelmonListener.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("pixelmoneventintegrationmod")
public class PixelmonEventIntegrationMod {

    // Directly reference a log4j logger.
    private final Intermediary intermediary = new Intermediary();
    public static PixelmonEventIntegrationMod INSTANCE;

    public static HashMap<Class<?>,Object> ALL_EVENTS = new HashMap<>();

    public Intermediary getIntermediary(){
        return intermediary;
    }

    public PixelmonEventIntegrationMod() {
        INSTANCE = this;
        LogManager.getLogger().warn("Registering all events to the forge");
        ALL_EVENTS.put(PlayerRightClickListener.class,new PlayerRightClickListener());
        ALL_EVENTS.put(OnDropEvent.class,new OnDropEvent());
        ALL_EVENTS.put(OnPixelmonReceiveEvent.class,new OnPixelmonReceiveEvent());
        ALL_EVENTS.put(OnFishingEvent.class,new OnFishingEvent());
        ALL_EVENTS.put(OnDayCareEvent.class,new OnDayCareEvent());

        MinecraftForge.EVENT_BUS.register(ALL_EVENTS.get(PlayerRightClickListener.class));

        ALL_EVENTS.forEach((aClass, o) -> {
            Pixelmon.EVENT_BUS.register(o);
        });
    }


    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        //LOGGER.info("HELLO from server starting");
    }
}
