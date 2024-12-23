package mk.aoc24.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

public final class CollectorHelper {

    private CollectorHelper(){
    }

    public static Collector<String, List<List<String>>, List<List<String>>> toBlocks(int size) {
        return Collector.of(
            ArrayList::new,
            (blocks, line) -> {
                if (blocks.isEmpty()) {
                    blocks.add(new ArrayList<>());
                }
                List<String> block = blocks.getLast();
                if (block.size() == size) {
                    block = new ArrayList<>(size);
                    blocks.add(block);
                }
                block.add(line);
            },
            (b, c) -> {
                throw new UnsupportedOperationException();
            }
        );
    }

}
