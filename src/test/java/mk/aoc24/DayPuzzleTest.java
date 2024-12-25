package mk.aoc24;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DayPuzzleTest {

    @ParameterizedTest
    @MethodSource("provideSolutions")
    void shouldSolveDayPuzzle(DayPuzzle dayPuzzle, String expected) {
        assertThat(dayPuzzle.solve()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideSolutions() {
        return Stream.of(
            Arguments.of(DayPuzzle.DAY_01_PUZZLE_1, "765748"),
            Arguments.of(DayPuzzle.DAY_01_PUZZLE_2, "27732508"),
            Arguments.of(DayPuzzle.DAY_02_PUZZLE_1, "246"),
            Arguments.of(DayPuzzle.DAY_02_PUZZLE_2, "318"),
            Arguments.of(DayPuzzle.DAY_03_PUZZLE_1, "153469856"),
            Arguments.of(DayPuzzle.DAY_03_PUZZLE_2, "77055967"),
            Arguments.of(DayPuzzle.DAY_04_PUZZLE_1, "2532"),
            Arguments.of(DayPuzzle.DAY_04_PUZZLE_2, "1941"),
            Arguments.of(DayPuzzle.DAY_05_PUZZLE_1, "5268"),
            Arguments.of(DayPuzzle.DAY_05_PUZZLE_2, "5799"),
            Arguments.of(DayPuzzle.DAY_06_PUZZLE_1, "4647"),
            Arguments.of(DayPuzzle.DAY_06_PUZZLE_2, "1723"),
            Arguments.of(DayPuzzle.DAY_07_PUZZLE_1, "850435817339"),
            Arguments.of(DayPuzzle.DAY_07_PUZZLE_2, "104824810233437"),
            Arguments.of(DayPuzzle.DAY_08_PUZZLE_1, "329"),
            Arguments.of(DayPuzzle.DAY_08_PUZZLE_2, "1190"),
            Arguments.of(DayPuzzle.DAY_09_PUZZLE_1, "6385338159127"),
            Arguments.of(DayPuzzle.DAY_09_PUZZLE_2, "6415163624282"),
            Arguments.of(DayPuzzle.DAY_10_PUZZLE_1, "482"),
            Arguments.of(DayPuzzle.DAY_10_PUZZLE_2, "1094"),
            Arguments.of(DayPuzzle.DAY_11_PUZZLE_1, "189167"),
            Arguments.of(DayPuzzle.DAY_11_PUZZLE_2, "225253278506288"),
            Arguments.of(DayPuzzle.DAY_12_PUZZLE_1, "1522850"),
            Arguments.of(DayPuzzle.DAY_12_PUZZLE_2, "953738"),
            Arguments.of(DayPuzzle.DAY_13_PUZZLE_1, "31623"),
            Arguments.of(DayPuzzle.DAY_13_PUZZLE_2, "93209116744825"),
            Arguments.of(DayPuzzle.DAY_14_PUZZLE_1, "216027840"),
            Arguments.of(DayPuzzle.DAY_14_PUZZLE_2, "6876"),
            Arguments.of(DayPuzzle.DAY_15_PUZZLE_1, "1563092"),
            Arguments.of(DayPuzzle.DAY_15_PUZZLE_2, "1582688"),
            Arguments.of(DayPuzzle.DAY_16_PUZZLE_1, "74392"),
            Arguments.of(DayPuzzle.DAY_16_PUZZLE_2, "426"),
            Arguments.of(DayPuzzle.DAY_17_PUZZLE_1, "3,7,1,7,2,1,0,6,3"),
            Arguments.of(DayPuzzle.DAY_17_PUZZLE_2, "37221334433268"),
            Arguments.of(DayPuzzle.DAY_18_PUZZLE_1, "336"),
            Arguments.of(DayPuzzle.DAY_18_PUZZLE_2, "24,30"),
            Arguments.of(DayPuzzle.DAY_19_PUZZLE_1, "260"),
            Arguments.of(DayPuzzle.DAY_19_PUZZLE_2, "639963796864990"),
            Arguments.of(DayPuzzle.DAY_20_PUZZLE_1, "1360"),
            Arguments.of(DayPuzzle.DAY_20_PUZZLE_2, "1005476"),
            Arguments.of(DayPuzzle.DAY_21_PUZZLE_1, "179444"),
            Arguments.of(DayPuzzle.DAY_21_PUZZLE_2, "223285811665866"),
            Arguments.of(DayPuzzle.DAY_22_PUZZLE_1, "14623556510"),
            Arguments.of(DayPuzzle.DAY_22_PUZZLE_2, "1701"),
            Arguments.of(DayPuzzle.DAY_23_PUZZLE_1, "1054"),
            Arguments.of(DayPuzzle.DAY_23_PUZZLE_2, "ch,cz,di,gb,ht,ku,lu,tw,vf,vt,wo,xz,zk"),
            Arguments.of(DayPuzzle.DAY_24_PUZZLE_1, "56939028423824"),
            Arguments.of(DayPuzzle.DAY_24_PUZZLE_2, "frn,gmq,vtj,wnf,wtt,z05,z21,z39"),
            Arguments.of(DayPuzzle.DAY_25_PUZZLE_1, "3155")
        );
    }
}
