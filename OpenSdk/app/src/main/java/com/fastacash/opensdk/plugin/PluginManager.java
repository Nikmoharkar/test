package com.fastacash.opensdk.plugin;

import java.util.HashMap;

/**
 * PluginManager will manage all the plugin
 * <p/>
 * Created by Nikhil on 10/21/2015.
 */
public class PluginManager {

    private static PluginManager pluginManager;
    /**
     * Mantains the plugins data and plugin instances those are registered
     */
    private HashMap<String, Plugin> plugins;

    private PluginManager() {
        plugins = new HashMap<>();
    }

    /**
     * This will return the instance of the PluginManager
     *
     * @return
     */
    public static PluginManager getInstance() {
        if (pluginManager == null) {
            pluginManager = new PluginManager();
        }
        return pluginManager;
    }

    /**
     * This method will register the plugin
     *
     * @param plugin
     */
    public void registerPlugin(Plugin plugin) {
        if (plugin != null) {
            plugins.put(plugin.getPluginName(), plugin);
        }

    }

    /**
     * This method will give the plugin
     *
     * @param pluginName
     * @return
     */
    public Plugin getPlugin(String pluginName) {
        return plugins.get(pluginName);
    }
}
