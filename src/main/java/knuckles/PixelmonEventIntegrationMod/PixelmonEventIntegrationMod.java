package knuckles.PixelmonEventIntegrationMod;

import com.pixelmonmod.pixelmon.Pixelmon;
import knuckles.PixelmonEventIntegrationMod.PixelmonListener.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("pixelmoneventintegrationmod")
public class PixelmonEventIntegrationMod {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    private final Intermediary intermediary = new Intermediary();
    public static PixelmonEventIntegrationMod INSTANCE;

    public Intermediary getIntermediary(){
        return intermediary;
    }

    public PixelmonEventIntegrationMod() {
        INSTANCE = this;
        MinecraftForge.EVENT_BUS.register(new PlayerRightClickListener());
        Pixelmon.EVENT_BUS.register(new OnDropEvent());
        Pixelmon.EVENT_BUS.register(new OnPixelmonReceiveEvent());
        Pixelmon.EVENT_BUS.register(new OnFishingEvent());
        Pixelmon.EVENT_BUS.register(new OnDayCareEvent());
    }


    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        //LOGGER.info("HELLO from server starting");
    }
}
