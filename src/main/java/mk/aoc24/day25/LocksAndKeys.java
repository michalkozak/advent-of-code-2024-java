package mk.aoc24.day25;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;
import mk.aoc24.board.Board;

import mk.aoc24.helper.CollectorHelper;

public class LocksAndKeys extends PuzzleSolver {

    protected final int boardHeight;

    private final List<List<Integer>> locksPins = new ArrayList<>();
    private final List<List<Integer>> keysPins = new ArrayList<>();

    public LocksAndKeys(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    @Override
    protected void parseLines(Stream<String> lines) {
        lines.collect(CollectorHelper.toBlocks(boardHeight + 1)).forEach(this::parseBlock);
    }

    private void parseBlock(List<String> block) {
        if (block.size() > boardHeight) {
            block.removeLast();
        }
        Board board = new Board(block.stream());
        if (isBoardLock(board)) {
            locksPins.add(getBoardPins(board));
        } else {
            keysPins.add(getBoardPins(board));
        }
    }

    private boolean isBoardLock(Board board) {
        for (int x = 0; x < board.width(); x++) {
            if (board.get(x, 0) == '#') {
                return false;
            }
        }
        return true;
    }

    private List<Integer> getBoardPins(Board board) {
        List<Integer> pin = new ArrayList<>();
        for (int x = 0; x < board.width(); x++) {
            int code = -1;
            for (int y = 0; y < board.height(); y++) {
                if (board.get(x, y) == '#') {
                    code++;
                }
            }
            pin.add(code);
        }
        return pin;
    }

    @Override
    protected String computeResult() {
        int result = 0;
        for (List<Integer> lockPin : locksPins) {
            for (List<Integer> keyPin : keysPins) {
                if (areLockAndKeyFitTogether(lockPin, keyPin)) {
                    result++;
                }
            }
        }
        return String.valueOf(result);
    }

    protected boolean areLockAndKeyFitTogether(List<Integer> lockPin, List<Integer> keyPins) {
        for (int i = 0; i < lockPin.size(); i++) {
            if (lockPin.get(i) + keyPins.get(i) > boardHeight - 2) {
                return false;
            }
        }
        return true;
    }

}
