package knuckles.PixelmonEventIntegrationMod.PixelmonListener;

import knuckles.PixelmonEventIntegrationMod.Utils.EventsINTutils;

import java.util.*;

import static knuckles.PixelmonEventIntegrationMod.PixelmonEventIntegrationMod.ALL_EVENTS;

public class Intermediary {

    /**
     * Register a listener for a specific event.
     *
     * @param plugin the class of your plugin
     * @param event the class of the event
     */
    public void registerListener(Class<?> plugin, Class<?> event) {
        // Get the EventsINTutils instance for the specified event
        EventsINTutils eventsIntUtils = (EventsINTutils) ALL_EVENTS.get(event);

        // Register the listener for the specified plugin and event
        eventsIntUtils.registerListenerToEvent(plugin, event);
    }

    /**
     * Get the List with the event data
     *
     * @param  plugin	        The plugin class registered
     * @param  event	        The event registered
     * @param  communication	The Communication class sent by the event
     * @return         	    List of event data if available, else an empty ArrayList
     */
    public List<Object> getCommunication(Class<?> plugin, Class<?> event, Class<?> communication) {
        // Retrieve the EventsINTutils object associated with the event
        EventsINTutils eventUtils = (EventsINTutils) ALL_EVENTS.get(event);

        // Check if the event has data stored
        if (!eventUtils.hasValueInThisKey(event)) {
            // If data exists, return the filtered event list data
            return (List<Object>) eventUtils.filterEventListData(plugin, event, communication);
        } else {
            // If no data, return an empty ArrayList
            return new ArrayList<>();
        }
    }
}
