package edami.gdrapriori.data;

import java.util.Arrays;

/**
 * Holds conditional and decision values encoded as integers.
 *
 * @author kjrz
 */
public class Transaction {

    private final int[] conditionalValues;
    private final int decisionValue;

    public Transaction(Labels labels, String[] record) {
        conditionalValues = readConditionalValues(labels, record);
        decisionValue = readDecisionValue(labels, record);
    }

    private int[] readConditionalValues(Labels labels, String[] record) {
        int[] values = new int[record.length - 1];
        for (int i = 0; i < record.length - 1; i++) {
            values[i] = labels.encodeConditionalValue(record[i]);
        }
        return values;
    }

    private int readDecisionValue(Labels labels, String[] record) {
        return labels.encodeDecisionValue(record[record.length-1]);
    }

    public int[] getConditionalValues() {
        return Arrays.copyOf(conditionalValues, conditionalValues.length);
    }

    public int getDecisionValue() {
        return decisionValue;
    }
}
