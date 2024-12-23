package mk.aoc24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

public abstract class PuzzleSolver {

    public String solve(URL url) {
        try {
            URLConnection connection = url.openConnection();
            try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                parseLines(reader.lines());
            }
        } catch (IOException e) {
            throw new ParseException(e);
        }
        return computeResult();
    }

    protected abstract void parseLines(Stream<String> lines);

    protected abstract String computeResult();

}
