package com.sogeti.helpers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Methods for interacting with strings
 *
 * @author Dennis Colburn
 *
 */
public class StringHelper {

    /**
     * Recursive method that substitutes each key in stringVars with its
     * corresponding value.
     *
     * @param originalString
     *            The original string
     * @param queryVars
     *            Values to substitute in the original string
     * @return formattedString The original string after substitutions are made
     */
    public static String formatString(String originalString, HashMap<String, Object> queryVars) {
        String formattedString = "";
        String key = null;
        try {
            Iterator<?> keyValuePairs = queryVars.entrySet().iterator();

            if (keyValuePairs.hasNext()) {
                Map.Entry<String, String> keyValuePair = (Map.Entry<String, String>) keyValuePairs.next();
                key = keyValuePair.getKey();
                String value = null;

                if (keyValuePair.getValue() == null) {
                    System.out.println("Null value for key: " + key);
                } else if (keyValuePair.getValue().isEmpty()) {
                    value = " ";
                } else {
                    value = keyValuePair.getValue().charAt(0) == "$".charAt(0) ? "\\" + keyValuePair.getValue()
                            : keyValuePair.getValue();
                }

                formattedString = originalString.replaceAll(key, value);
                queryVars.remove(keyValuePair.getKey());

                if (!queryVars.isEmpty()) {
                    formattedString = formatString(formattedString, queryVars);
                }
            }
        } catch (ClassCastException e) {
            System.out.println("The value's class for the key, " + key + ", is not a String!");
            e.printStackTrace();
        }
        return formattedString;
    }

    /**
     * Wrap the given string in single quotes if the value of the string is not
     * equal to "null"
     *
     * @param s
     *            String
     * @return formatted String
     */
    public static String formatIfNull(String s) {
        String returnStr;
        //TODO Check if s is null, then check if value is "null"
        if (s.equals("null")) {
            returnStr = s;
        } else {
            returnStr = "'" + s + "'";
        }
        return returnStr;
    }

}

