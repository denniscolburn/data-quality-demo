package com.sogeti.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

/**
 * Class contains methods for loading yaml files and returning their values.
 *
 * @author Dennis Colburn
 *
 */
public class ConfigHelper {

    /**
     * Object that represents the given yaml in a Java Map object
     */
    private Map<String, ?> config;

    /**
     * Class constructor specifying the yaml file.
     *
     * @param yamlFile
     *            File containing configuration data
     */
    public ConfigHelper(String yamlFile) {
        loadConfigFile(yamlFile);
    }

    /**
     * Load the data in the yaml file into the config Map.
     *
     * @param yamlFile
     *            File containing configuration data
     */
    private void loadConfigFile(String yamlFile) {
        try {
            Yaml yaml = new Yaml();
            config = (Map<String, ?>) yaml.load(new FileReader(new File(yamlFile)));
        } catch (FileNotFoundException e) {
            System.out.println("Error loading config file: " + yamlFile);
            System.out.println(e);
        }
    }

    /**
     * Returns the value for the given key.
     *
     * @param key
     *            Configuration key associated with value
     * @return Value associated with key
     * @throws Exception
     */
    public Object get(String key) throws Exception {
        Object value = null;
        value = config.get((Object) key);
        if (value == null) {
            throw new Exception("Corresponding value not found for key: " + key);
        }
        return value;
    }

    /**
     * Return configuration data for the given key. Use this method when you
     * expect the value to be another HashMap.
     *
     * @param key
     *            Configuration key associated with sub-configuration
     * @return Configuration associated with key
     */
    public HashMap<String, ?> getConfiguration(String key) {
        return (HashMap<String, ?>) config.get(key);
    }

}

