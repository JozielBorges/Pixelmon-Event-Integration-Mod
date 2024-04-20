package knuckles.PixelmonEventIntegrationMod.Interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface IEvent {
    final HashMap<String, List<Class<?>>> LISTENERS = new HashMap<>();
    final HashMap<String, Boolean> LISTENERS_BOOLEAN = new HashMap<>();
    final HashMap<Class<?>,List<Object>> DATA = new HashMap<>();
    final List<String> LIST_LISTENERS_ONLINE = new ArrayList<>();

    /**
     * Registers a listener to a specific event for a given plugin.
     *
     * @param plugin the plugin class to register the listener for
     * @param event the event class to listen for
     */
    default void registerListenerToEvent(Class<?> plugin, Class<?> event) {
        // Get the name of the plugin
        String pluginName = plugin.getName();

        // Check if the plugin is already registered
        if (!LISTENERS.containsKey(pluginName)) {
            // If not registered, add a new entry for the plugin
            LISTENERS.put(pluginName, new ArrayList<>());
            LISTENERS.get(pluginName).add(event);
            LISTENERS_BOOLEAN.put(pluginName, false);
            if (!LIST_LISTENERS_ONLINE.contains(event.getName())) {
                LIST_LISTENERS_ONLINE.add(event.getName());
            }
        } else {
            // If plugin is already registered, update the event list
            LISTENERS.get(pluginName).add(event);
            LISTENERS_BOOLEAN.put(pluginName, false);
            if (!LIST_LISTENERS_ONLINE.contains(event.getName())) {
                LIST_LISTENERS_ONLINE.add(event.getName());
            }
        }
    }

    /**
     * Clears the data related to the specified plugin and event class if none of the listeners are currently active.
     *
     * @param pluginName the name of the plugin
     * @param event the event class to clear data for
     */
    default void clearData(String pluginName, Class<?> event) {
        // Set the listener status of the specified plugin to false
        LISTENERS_BOOLEAN.replace(pluginName, false);

        // Count the number of active listeners for the specified event
        AtomicInteger count = new AtomicInteger();
        LISTENERS.forEach((s, classes) -> {
            if (classes.contains(event)) {
                if (LISTENERS_BOOLEAN.get(s)) {
                    count.addAndGet(1);
                }
            }
        });

        // If there are active listeners, do not clear the data
        if (count.intValue() > 0) {
            return;
        }

        // Clear the data related to the specified event
        DATA.get(event).clear();
    }
    /**
     * Adds the provided data to the corresponding event in the dataTest map and updates the LISTENERS_BOOLEAN map accordingly.
     *
     * @param event The event class to add data to
     * @param data The data to add
     */
    default void addMyEventDataToData(Class<?> event, Object data){

        // If the event is not already in the dataTest map, initialize it with an empty ArrayList
        if(!DATA.containsKey(event)){
            DATA.put(event,new ArrayList<>());
        }

        // Add the data to the event in the dataTest map
        DATA.get(event).add(data);

        // Update the LISTENERS_BOOLEAN map based on the event
        LISTENERS.forEach((s, classes) -> {
            if(classes.contains(event)){
                LISTENERS_BOOLEAN.replace(s,true);
            }
        });
    }

    /**
     * Check if the specified key has a non-null value in the data map.
     * If the value is not null, return whether the value is empty or not.
     * If the value is null, return true.
     *
     * @param key The key to check in the data map
     * @return true if the key is null or empty, false otherwise
     */
    default boolean hasValueInThisKey(Class<?> key){
        return DATA.get(key) != null ? DATA.get(key).isEmpty() : true;
    }

    /**
     * Filters event list data based on the provided plugin, listener, and type.
     *
     * @param plugin the plugin class to filter by
     * @param listener the listener class to filter by
     * @param type the type of data to filter
     * @return a list of filtered data of the specified type
     */
    <T> List<T> filterEventListData(Class<?> plugin, Class<?> listener, Class<T> type);
}
