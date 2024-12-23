package mk.aoc24.day09;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import mk.aoc24.PuzzleSolver;

public class DiskDefragment extends PuzzleSolver {

    protected List<Block> diskMap;

    @Override
    protected void parseLines(Stream<String> lines) {
        parseDiskMap(lines.collect(Collectors.joining()));
    }

    private void parseDiskMap(String mapShort) {
        this.diskMap = new ArrayList<>();
        for (int i = 0; i < mapShort.length(); i++) {
            int blockSize = Integer.parseInt(mapShort.substring(i, i + 1));
            if (i % 2 == 0) {
                String fileId = String.valueOf(i / 2);
                diskMap.add(new Block(blockSize, fileId));
            } else {
                diskMap.add(new Block(blockSize, "."));
            }
        }
    }

    @Override
    protected String computeResult() {
        defragment();
        return String.valueOf(computeChecksum());
    }

    protected void defragment() {
        int lastFileBlockIdx = diskMap.size() - 1;
        int firstFreeBlockIdx = 1;
        while (firstFreeBlockIdx < lastFileBlockIdx) {
            Block lastFileBlock = diskMap.get(lastFileBlockIdx);
            Block firstFreeBlock = diskMap.get(firstFreeBlockIdx);
            while (lastFileBlock.isAnyNonFree()) {
                if (firstFreeBlock.getFreeSize() > 0) {
                    firstFreeBlock.insertIntoFirstFree(lastFileBlock.getLastNonFree());
                    lastFileBlock.makeLastPlaceFree();
                } else {
                    firstFreeBlockIdx += 2;
                    if (firstFreeBlockIdx > lastFileBlockIdx) {
                        break;
                    }
                    firstFreeBlock = diskMap.get(firstFreeBlockIdx);
                }
            }
            lastFileBlockIdx -= 2;
        }
    }

    protected long computeChecksum() {
        long checksum = 0;
        long pos = 0;
        for (Block block : diskMap) {
            for (String fileId : block.getContent()) {
                long id = 0;
                if (!fileId.equals(".")) {
                    id = Long.parseLong(fileId);
                }
                checksum += pos * id;
                pos++;
            }
        }
        return checksum;
    }

    protected String getFootprint() {
        StringBuilder footprint = new StringBuilder();
        for (Block block : diskMap) {
            for (String fileId : block.getContent()) {
                footprint.append(fileId);
            }
        }
        return footprint.toString();
    }

}
