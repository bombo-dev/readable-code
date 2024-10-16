package cleancode.minesweeper.tobe.minesweeper.board.position;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CellPositionTest {

    @DisplayName("0이상의 좌표인 경우에 Cell 위치를 생성 할 수 있다.")
    @CsvSource(value = {"0:1", "1:0", "1:1"}, delimiter = ':')
    @ParameterizedTest
    void createCellPositionWithinRange() {
        // when

        // then
        Assertions.assertThatCode(() -> CellPosition.of(1, 1)
        ).doesNotThrowAnyException();
    }

    @DisplayName("0미만의 좌표인 경우에 Cell 위치 생성 시 예외가 발생한다.")
    @CsvSource(value = {"1:-1", "-1:1"}, delimiter = ':')
    @ParameterizedTest
    void createCellPositionWithOutOfRange(int row, int col) {
        // when

        // then
        Assertions.assertThatThrownBy(() -> CellPosition.of(row, col)
                )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 좌표입니다.");
    }

    @DisplayName("RelativePosition에 의해 CellPosition이 0미만으로 벗어나지 않으면 통과한다.")
    @CsvSource({
            "0, 0, 0, 0, true",
            "0, 0, 0, 1, true",
            "0, 0, 1, 0, true",
            "0, 0, 1, 1, true",
            "1, 0, -1, 0, true",
            "0, 1, 0, -1, true",
            "1, 1, -1, -1, true",
    })
    @ParameterizedTest
    void canCalculatePositionByHigherThanZero(int rowIndex, int colIndex, int deltaRow, int deltaCol, boolean expected) {
        // given
        CellPosition cellPosition = CellPosition.of(rowIndex, colIndex);
        RelativePosition relativePosition = RelativePosition.of(deltaRow, deltaCol);

        // when
        boolean result = cellPosition.canCalculatePositionBy(relativePosition);

        // then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @DisplayName("RelativePosition에 의해 CellPosition이 0미만으로 벗어나면 실패한다.")
    @CsvSource({
            "0, 0, -1, 0, false",
            "0, 0, 0, -1, false",
            "1, 0, -1, -1, false",
            "0, 1, -1, -1, false",
    })
    @ParameterizedTest
    void canCalculatePositionByLowerThanZero(int rowIndex, int colIndex, int deltaRow, int deltaCol, boolean expected) {
        // given
        CellPosition cellPosition = CellPosition.of(rowIndex, colIndex);
        RelativePosition relativePosition = RelativePosition.of(deltaRow, deltaCol);

        // when
        boolean result = cellPosition.canCalculatePositionBy(relativePosition);

        // then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @DisplayName("CellPosition을 주변 좌표를 이용하여 계산할 수 있다.")
    @CsvSource({
            "0, 0, 0, 0, 0, 0",
            "0, 0, 0, 1, 0, 1",
            "0, 0, 1, 0, 1, 0",
            "0, 0, 1, 1, 1, 1",
            "1, 0, -1, 0, 0, 0",
            "0, 1, 0, -1, 0, 0",
            "1, 1, -1, -1, 0, 0",
    })
    @ParameterizedTest
    void calculatePositionBySuccess(int rowIndex, int colIndex, int deltaRow, int deltaCol, int expectedRow, int expectedCol) {
        // given
        CellPosition cellPosition = CellPosition.of(rowIndex, colIndex);
        RelativePosition relativePosition = RelativePosition.of(deltaRow, deltaCol);

        // when
        CellPosition newPosition = cellPosition.calculatePositionBy(relativePosition);

        // then
        Assertions.assertThat(newPosition).isEqualTo(CellPosition.of(expectedRow, expectedCol));
    }

    @DisplayName("CellPosition을 주변 좌표를 이용하여 계산할 수 없는 경우 예외가 발생한다.")
    @CsvSource({
            "0, 0, -1, 0",
            "0, 0, 0, -1",
            "1, 0, -1, -1",
            "0, 1, -1, -1",
    })
    @ParameterizedTest
    void calculatePositionByFailure(int rowIndex, int colIndex, int deltaRow, int deltaCol) {
        // given
        CellPosition cellPosition = CellPosition.of(rowIndex, colIndex);
        RelativePosition relativePosition = RelativePosition.of(deltaRow, deltaCol);

        // when
        // then
        Assertions.assertThatThrownBy(() -> cellPosition.calculatePositionBy(relativePosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 있는 좌표가 아닙니다.");
    }
}
