package edami.gdrapriori.data;

import edami.gdrapriori.io.TextFileReader;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * JUnit 4.10 tests for {@link edami.gdrapriori.data.Labels}.
 *
 * @author kjrz
 */
public class LabelsSpecification {

    @Test
    public void shouldReadFromTextFileReader() {
        try {
            new Labels(new TextFileReader("./input/weather-1").getLabels());
        } catch (IOException e) {
            fail("Exception thrown on reading text file: \n" + e.getMessage());
        }
    }

    @Test
    public void shouldMapConditionalValuesInOrder() {
        Labels labels = null;
        try {
            labels = new Labels(new TextFileReader("./input/weather-1").getLabels());
        } catch (IOException e) {
            fail("Exception thrown on reading text file: \n" + e.getMessage());
        }

        assertEquals(1, labels.encodeConditionalValue("Sunny"));
        assertEquals(2, labels.encodeConditionalValue("Mild"));
        assertEquals(3, labels.encodeConditionalValue("High"));
        assertEquals(4, labels.encodeConditionalValue("True"));
    }

    @Test
    public void shouldMapDecisionValuesInOrder() {
        Labels labels = null;
        try {
            labels = new Labels(new TextFileReader("./input/weather-1").getLabels());
        } catch (IOException e) {
            fail("Exception thrown on reading text file: \n" + e.getMessage());
        }

        assertEquals(1, labels.encodeDecisionValue("P"));
        assertEquals(2, labels.encodeDecisionValue("N"));
    }
}
