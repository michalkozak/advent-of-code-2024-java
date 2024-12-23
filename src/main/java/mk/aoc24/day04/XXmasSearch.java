package mk.aoc24.day04;

public class XXmasSearch extends XmasSearch {

    @Override
    protected String computeResult() {
        countX('M', 'S', 'A', 'M', 'S');
        countX('S', 'M', 'A', 'S', 'M');
        countX('S', 'S', 'A', 'M', 'M');
        countX('M', 'M', 'A', 'S', 'S');
        return String.valueOf(numberOfXmas);
    }

    private void countX(char ch11, char ch13, char ch22, char ch31, char ch33) {
        for (int i = 0; i < characters.size() - 2; i++) {
            for (int j = 0; j < characters.get(i).size() - 2; j++) {
                if (characters.get(i).get(j) == ch11 && characters.get(i+2).get(j) == ch13
                    && characters.get(i+1).get(j+1) == ch22
                    && characters.get(i).get(j+2) == ch31 && characters.get(i+2).get(j+2) == ch33) {
                    numberOfXmas++;
                }
            }
        }
    }

}
