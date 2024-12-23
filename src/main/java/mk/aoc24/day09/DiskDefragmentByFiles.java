package mk.aoc24.day09;

public class DiskDefragmentByFiles extends DiskDefragment {

    @Override
    protected void defragment() {
        int lastFileBlockIdx = diskMap.size() - 1;
        int firstFreeBlockIdx = 1;
        while (lastFileBlockIdx > 0) {
            while (firstFreeBlockIdx < lastFileBlockIdx) {
                Block lastFileBlock = diskMap.get(lastFileBlockIdx);
                Block firstFreeBlock = diskMap.get(firstFreeBlockIdx);
                if (firstFreeBlock.getFreeSize() >= lastFileBlock.getTotalSize()) {
                    for (int i = 0 ; i < lastFileBlock.getTotalSize() ; i++) {
                        firstFreeBlock.insertIntoFirstFree(lastFileBlock.getContent().get(i));
                    }
                    lastFileBlock.makeBlockFree();
                    firstFreeBlockIdx = 1;
                    lastFileBlockIdx -= 2;
                } else {
                    firstFreeBlockIdx += 2;
                }
            }
            firstFreeBlockIdx = 1;
            lastFileBlockIdx -= 2;
        }
    }

}
