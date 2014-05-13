package edami.gdrapriori.data;

import edami.gdrapriori.io.TextFileReader;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * JUnit 4.10 tests for {@link edami.gdrapriori.data.Transaction}.
 *
 * @author kjrz
 */
public class TransactionSpecification {

    @Test
    public void shouldReadTransactionsFromTextFileReader() {
        TextFileReader reader = null;
        try {
            reader = new TextFileReader("./input/weather-1");
        } catch (IOException e) {
            fail("Exception thrown on reading text file: \n" + e.getMessage());
        }
        Labels labels = new Labels(reader.getLabels());

        for (String[] record : reader.getRecords()) {
            new Transaction(labels, record);
        }
    }

    @Test
    public void shouldReadConditionalValues() {
        TextFileReader reader = null;
        try {
            reader = new TextFileReader("./input/weather-1");
        } catch (IOException e) {
            fail("Exception thrown on reading text file: \n" + e.getMessage());
        }
        Labels labels = new Labels(reader.getLabels());

        Transaction first = new Transaction(labels, reader.getRecords().get(0));
        int[] expectedFirst = new int[]{3};
        assertArrayEquals(expectedFirst, first.getConditionalValues());

        int lastIndex = reader.getRecords().size() - 1;
        Transaction last = new Transaction(labels, reader.getRecords().get(lastIndex));
        int[] expectedLast = new int[]{2, 3, 4};
        assertArrayEquals(expectedLast, last.getConditionalValues());
    }

    @Test
    public void shouldReadDecisionValues() {
        TextFileReader reader = null;
        try {
            reader = new TextFileReader("./input/weather-1");
        } catch (IOException e) {
            fail("Exception thrown on reading text file: \n" + e.getMessage());
        }
        Labels labels = new Labels(reader.getLabels());

        Transaction first = new Transaction(labels, reader.getRecords().get(0));
        int expectedFirst = 1;
        assertEquals(expectedFirst, first.getDecisionValue());

        int lastIndex = reader.getRecords().size() - 1;
        Transaction last = new Transaction(labels, reader.getRecords().get(lastIndex));
        int expectedLast = 2;
        assertEquals(expectedLast, last.getDecisionValue());
    }
}
