package knuckles.PixelmonEventIntegrationMod.PixelmonListener;

import com.pixelmonmod.pixelmon.api.events.FishingEvent;
import knuckles.PixelmonEventIntegrationMod.Communication.FishingCommunication;
import knuckles.PixelmonEventIntegrationMod.PixelmonEventIntegrationMod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnFishingEvent {
    private  final Intermediary intermediary = PixelmonEventIntegrationMod.INSTANCE.getIntermediary();
    @SubscribeEvent
    public void onFishing(FishingEvent.Catch event){
        intermediary.addFishingCommunication(new FishingCommunication(event.player.getUUID()));
    }
}
