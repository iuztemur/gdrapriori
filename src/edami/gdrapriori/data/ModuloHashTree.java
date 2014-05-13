package edami.gdrapriori.data;

/**
 * @author kjrz
 */
public class ModuloHashTree extends TransactionsHashTree {

    private static final int MAX_LEAF_SIZE = 3;
    // TODO: this one is for mod 2,
    // TODO: it has to be adjusted otherwise the split won't work

    private final int divisor;

    public ModuloHashTree(Labels labels) {
        divisor = (int) Math.sqrt((double) labels.conditionalValueMax());
    }

    @Override
    public int[] hashFunction(Transaction t) {
        int[] conditionalValues = t.getConditionalValues();
        int[] hash = new int[conditionalValues.length];

        for (int i = 0; i < conditionalValues.length; i++) {
            hash[i] = hashFunction(conditionalValues[i]);
        }

        return hash;
    }

    private int hashFunction(int c) {
        return c % divisor;
    }

    @Override
    public int maxLeafSize() {
        return MAX_LEAF_SIZE;
    }
}
