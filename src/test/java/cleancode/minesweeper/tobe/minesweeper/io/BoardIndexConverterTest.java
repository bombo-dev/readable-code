package cleancode.minesweeper.tobe.minesweeper.io;

import cleancode.minesweeper.tobe.minesweeper.exception.GameException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BoardIndexConverterTest {

    private BoardIndexConverter boardIndexConverter = new BoardIndexConverter();

    @DisplayName("1이상의 입력 row 좌표를 BoardIndex로 변환할 수 있다.")
    @CsvSource(value = {"a1:0", "z1:0", "a10:9"}, delimiter = ':')
    @ParameterizedTest
    void getSelectedRowIndex(String input, int expected) {
        // given

        // when
        int selectedRowIndex = boardIndexConverter.getSelectedRowIndex(input);

        // then
        Assertions.assertThat(selectedRowIndex).isEqualTo(expected);
    }

    @DisplayName("1미만의 입력 row 좌표를 BoardIndex로 변환할 수 없다.")
    @Test
    void getSelectedRowIndexWithInvalidInput() {
        // given
        String input = "a0";

        // when
        // then
        Assertions.assertThatThrownBy(() -> boardIndexConverter.getSelectedRowIndex(input))
                .isInstanceOf(GameException.class)
                .hasMessage("잘못된 입력입니다.");
    }

    @DisplayName("한 자리의 입력 col 좌표를 BoardIndex로 변환할 수 있다.")
    @CsvSource(value = {"a1:0", "z1:25", "A10:0", "Z99:25"}, delimiter = ':')
    @ParameterizedTest
    void getSelectedColIndex(String input, int expected) {
        // given

        // when
        int selectedColIndex = boardIndexConverter.getSelectedColIndex(input);

        // then
        Assertions.assertThat(selectedColIndex).isEqualTo(expected);
    }

    @DisplayName("범위를 벗어난 입력 col 좌표를 BoardIndex로 변환할 수 없다.")
    @ValueSource(strings = {"{", "@"})
    @ParameterizedTest
    void getSelectedColIndexWithInvalidInput(String input) {
        // given

        // when
        // then
        Assertions.assertThatThrownBy(() -> boardIndexConverter.getSelectedColIndex(input))
                .isInstanceOf(GameException.class)
                .hasMessage("잘못된 입력입니다.");
    }
}
