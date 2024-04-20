package knuckles.PixelmonEventIntegrationMod.Utils;

import knuckles.PixelmonEventIntegrationMod.Interfaces.IEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EventsINTutils implements IEvent {
    /**
     * Filters the event list data based on the plugin, listener, and type.
     * If the event list is not empty, clears the data for the specific plugin and listener.
     *
     * @param plugin   The plugin class.
     * @param listener The listener class.
     * @param type     The type of data to filter.
     * @param <T>      The generic type.
     * @return A filtered list based on the type.
     */
    @Override
    public <T> List<T> filterEventListData(Class<?> plugin, Class<?> listener, Class<T> type) {
        // Initialize a list to hold the filtered data
        List<T> filteredList = new ArrayList<>();

        // Retrieve the event list for the listener
        List<Object> eventList = DATA.get(listener);

        // Get the plugin name without inner class information
        String pluginName = plugin.getName().replaceAll("\\$.*", "");

        // Check if the event list is not empty and the plugin and listener are active
        if (eventList != null && LISTENERS.containsKey(pluginName) && LISTENERS_BOOLEAN.get(pluginName)) {
            // Iterate through the event list
            Iterator<Object> iterator = eventList.iterator();
            while (iterator.hasNext()) {
                Object obj = iterator.next();
                // Check if the object is of the specified type and add it to the filtered list
                if (type.isInstance(obj)) {
                    filteredList.add(type.cast(obj));
                }
            }
        }

        // If the filtered list is not empty, clear the data for the plugin and listener
        if (!filteredList.isEmpty()) {
            clearData(pluginName, listener);
        }

        // Return the filtered list
        return filteredList;
    }
}
