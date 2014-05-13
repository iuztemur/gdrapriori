package edami.gdrapriori.data;

import org.apache.jorphan.collections.HashTree;

import java.util.Arrays;

/**
 * @author kjrz
 */
public abstract class TransactionsHashTree {

    private final HashTree tree = new HashTree();

    public void add(Transaction t) {
        if (t.getConditionalValues().length == 0) {
            return; // not of interest
        }

        int[] hash = hashFunction(t);

        if (tree.size() == 0) {
            tree.add(Arrays.asList(hash[0]), t);
            return;
        }

        HashTree subtree = targetSubtree(hash);

        subtree.add(t);

        if (subtree.size() > maxLeafSize()) {
            split(subtree);
        }
    }

    private HashTree targetSubtree(int[] hash) {
        HashTree subtree = tree.get(hash[0]);

        int pathLength = 0;

        while (subtree != null && !isLeaf(subtree)) {
            subtree = subtree.get(hash[++pathLength]);
        }

        return subtree;
    }

    private boolean isLeaf(HashTree subtree) {
        for (Object key : subtree.list()) {
            if (key instanceof Integer)
                return false;
        }

        return true;
    }

    private void split(HashTree subtree) {
        HashTree toSplit = (HashTree) subtree.clone();
        subtree.clear();

        for (Object key : toSplit.list()) {
            int[] hash = hashFunction((Transaction) key);

            if (hash.length < 2) {
                subtree.add(Arrays.asList(hash[0]), key);
            } else {
                subtree.add(Arrays.asList(hash[0], hash[1]), key);
            }
        }

        // TODO: split recursively in case any new leaf > maxLeafSize
    }

    public abstract int[] hashFunction(Transaction t);

    public abstract int maxLeafSize();
}
