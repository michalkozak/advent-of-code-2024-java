package mk.aoc24.day09;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Block {

    private final List<String> content;

    public Block(int blockSize, String initElement) {
        content = new ArrayList<>();
        IntStream.range(0, blockSize).forEach(ignored -> content.add(initElement));
    }

    public List<String> getContent() {
        return content;
    }

    public int getTotalSize() {
        return content.size();
    }

    public int getFreeSize() {
        return (int) content.stream().filter(e -> e.equals(".")).count();
    }

    public void insertIntoFirstFree(String fileId) {
        for (int i = 0; i < content.size(); i++) {
            if (content.get(i).equals(".")) {
                content.set(i, fileId);
                return;
            }
        }
    }

    public void makeLastPlaceFree() {
        for (int i = content.size() - 1; i >= 0; i--) {
            if (!content.get(i).equals(".")) {
                content.set(i, ".");
                break;
            }
        }
    }

    public void makeBlockFree() {
        content.replaceAll(ignored -> ".");
    }

    public boolean isAnyNonFree() {
        return !content.reversed().stream().allMatch(e -> e.equals("."));
    }

    public String getLastNonFree() {
        return content.reversed().stream().filter(e -> !e.equals(".")).findFirst().orElseThrow();
    }

}
