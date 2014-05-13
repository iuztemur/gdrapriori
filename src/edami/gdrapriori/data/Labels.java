package edami.gdrapriori.data;

import java.util.Map;
import java.util.TreeMap;

/**
 * Encodes conditional and decision values to integers.
 *
 * @author kjrz
 */
public class Labels {

    private final Map<String, Integer> conditionalValues = new TreeMap<String, Integer>();
    private final Map<String, Integer> decisionValues = new TreeMap<String, Integer>();

    // in case reverse translation comes in necessary, use guava's BiMap

    public Labels(String[] labels) {
        readConditionalValues(labels);
    }

    private void readConditionalValues(String[] labels) {
        int code = 1;

        for (String label : labels) {
            // last string are decision classes separated by "/"
            if (label.contains("/")) {
                readDecisionValues(label);
                break;
            }
            // all previous strings get int value assigned
            conditionalValues.put(label, code);
            code++;
        }
    }

    private void readDecisionValues(String label) {
        int code = 1;

        for (String decisionValue : label.split("/")) {
            decisionValues.put(decisionValue, code);
            code++;
        }
    }

    public int encodeConditionalValue(String key) {
        Integer value = conditionalValues.get(key);

        if (value == null) {
            throw new IllegalArgumentException(
                    "Key \"" + key + "\" not found in conditional values.");
        }

        return value;
    }


    public int encodeDecisionValue(String key) {
        Integer value = decisionValues.get(key);

        if (value == null) {
            throw new IllegalArgumentException(
                    "Key \"" + key + "\" not found in decision values.");
        }

        return value;
    }

    public int conditionalValueMax() { return conditionalValues.size(); }

}
