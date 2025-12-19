package io.github.ndys.patto.patterns.singleton.example1_config_manager;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    private static ConfigManager instance;
    
    private final Map<String, String> config = new HashMap<>();

    private ConfigManager() {
        config.put("app.name", "Patto");
        config.put("app.version", "1.0.0");
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    public String get(String key) {
        return config.get(key);
    }

    public void set(String key, String value) {
        config.put(key, value);
    }

}

