package edami.gdrapriori.io;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * JUnit 4.10 tests for {@link TextFileReader}.
 *
 * @author kjrz
 *
 */
public class TextFileReaderSpecification {

    @Test
    public void shouldReadTextFileWithoutExceptions() {
        try {
            new TextFileReader("./input/weather-1");
        } catch (IOException e) {
            fail("Exception thrown on reading text file: \n" + e.getMessage());
        }
    }

    @Test
    public void shouldReadLabelsAsArrayOfStrings() {
        TextFileReader reader = null;
        try {
            reader = new TextFileReader("./input/weather-1");
        } catch (IOException e) {
            fail("Exception thrown on reading text file: \n" + e.getMessage());
        }

        String[] expected = {"Sunny", "Mild", "High", "True", "P/N"};
        String[] labels = reader.getLabels();

        assertArrayEquals(expected, labels);
    }

    @Test
    public void shouldReadRecordsAsListOfArraysOfStrings() {
        TextFileReader reader = null;
        try {
            reader = new TextFileReader("./input/weather-1");
        } catch (IOException e) {
            fail("Exception thrown on reading text file: \n" + e.getMessage());
        }

        List<String[]> records = reader.getRecords();
        assertEquals(14, records.size());

        String[] expectedFirst = {"High", "P"};
        String[] first = records.get(0);
        assertArrayEquals(expectedFirst, first);

        String[] expectedLast = {"Mild", "High", "True", "N"};
        String[] last = records.get(records.size()-1);
        assertArrayEquals(expectedLast, last);
    }
}

