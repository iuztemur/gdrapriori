package edami.gdrapriori.data;

import edami.gdrapriori.io.TextFileReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

/**
 * @author kjrz
 */
public class ModuloHashTreeSpecification {

    private TextFileReader reader = null;
    private Labels labels = null;
    private TransactionsHashTree tree = null;

    @Before
    public void readInput() {
        try {
            reader = new TextFileReader("./input/weather-2");
        } catch (IOException e) {
            fail("Exception thrown on reading text file: \n" + e.getMessage());
        }

        labels = new Labels(reader.getLabels());
        tree = new ModuloHashTree(labels);
    }

    @Test
    public void shouldGetHashFunctionRight() {
        Transaction t1 = new Transaction(labels, reader.getRecords().get(0));
        Transaction t2 = new Transaction(labels, reader.getRecords().get(1));
        Transaction t4 = new Transaction(labels, reader.getRecords().get(3));

        int[] hash = tree.hashFunction(t1);
        assertArrayEquals(new int[]{1}, hash);


        hash = tree.hashFunction(t2);
        assertArrayEquals(new int[]{0, 1}, hash);

        hash = tree.hashFunction(t4);
        assertArrayEquals(new int[]{0}, hash);
    }

    @Test
    public void shouldAddTransactionOne() {
        Transaction t1 = new Transaction(labels, reader.getRecords().get(0));

        tree.add(t1);
    }

    @Test
    public void shouldAddAllTransactions() {
        for (String[] record : reader.getRecords()) {
            tree.add(new Transaction(labels, record));
        }
    }

}
