package knuckles.PixelmonEventIntegrationMod.PixelmonListener;

import com.pixelmonmod.pixelmon.api.events.FishingEvent;
import knuckles.PixelmonEventIntegrationMod.Communication.FishingCommunication;
import knuckles.PixelmonEventIntegrationMod.PixelmonEventIntegrationMod;
import knuckles.PixelmonEventIntegrationMod.Utils.EventsINTutils;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;

public class OnFishingEvent extends EventsINTutils {
    private  final Intermediary intermediary = PixelmonEventIntegrationMod.INSTANCE.getIntermediary();


    @SubscribeEvent
    public void onFishing(FishingEvent.Catch event){
        if(!LIST_LISTENERS_ONLINE.contains(this.getClass().getName())){
            LogManager.getLogger().warn("gathering ");
            return;
        }
        addMyEventDataToData(this.getClass(),new FishingCommunication(event.player.getUUID()));
    }
}
