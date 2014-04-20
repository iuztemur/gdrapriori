package edami.gdrapriori.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Reads text files of format presented by in/weather-1.
 *
 * @author kjrz
 *
 */
public class TextFileReader {

    public static final String REGEX_FOR_ANY_NUMBER_OF_SPACES = "\\s++";

    private final String[] labels;
    private final List<String[]> records;

    public TextFileReader(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        labels = readLabels(br);
        records = readData(br);
        br.close();
    }

    private String[] readLabels(BufferedReader br) throws IOException {
        return br.readLine().split(REGEX_FOR_ANY_NUMBER_OF_SPACES);
    }

    private List<String[]> readData(BufferedReader br) throws IOException {
        List<String[]> lines = new LinkedList<String[]>();

        String line = br.readLine();
        while (line != null) {
            lines.add(line.trim().split("\\s++"));
            line = br.readLine();
        }

        return lines;
    }

    public String[] getLabels() {
        return labels;
    }

    public List<String[]> getRecords() {
        return records;
    }
}
