package knuckles.PixelmonEventIntegrationMod.PixelmonListener;

import com.pixelmonmod.pixelmon.api.daycare.event.DayCareEvent;
import knuckles.PixelmonEventIntegrationMod.Communication.DaycareCommunication;
import knuckles.PixelmonEventIntegrationMod.PixelmonEventIntegrationMod;
import knuckles.PixelmonEventIntegrationMod.Utils.EventsINTutils;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class OnDayCareEvent extends EventsINTutils {
    private final Intermediary intermediary = PixelmonEventIntegrationMod.INSTANCE.getIntermediary();


    @SubscribeEvent
    public void onDayCare(DayCareEvent.PreCollect event){
        if(!LIST_LISTENERS_ONLINE.contains(this.getClass().getName())){
            return;
        }
        addMyEventDataToData(this.getClass(),new DaycareCommunication(event.getPlayer().getUUID(),event.getChildGiven()));
    }
}
