package knuckles.PixelmonEventIntegrationMod.PixelmonListener;

import com.pixelmonmod.pixelmon.api.daycare.event.DayCareEvent;
import knuckles.PixelmonEventIntegrationMod.Communication.DaycareCommunication;
import knuckles.PixelmonEventIntegrationMod.PixelmonEventIntegrationMod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;

public class OnDayCareEvent {
    private final Intermediary intermediary = PixelmonEventIntegrationMod.INSTANCE.getIntermediary();
    @SubscribeEvent
    public void onDayCare(DayCareEvent.PreCollect event){
        //LogManager.getLogger().info("DayCareEvent.PreCollect " + event.getChildGiven());
        intermediary.addDaycareCommunication(new DaycareCommunication(event.getPlayer().getUUID(),event.getChildGiven()));
    }
}
