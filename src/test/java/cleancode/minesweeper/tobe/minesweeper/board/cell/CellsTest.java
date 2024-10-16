package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;


class CellsTest {

    @DisplayName("모든 셀이 열린 상태라면 true를 반환한다.")
    @Test
    void isAllChecked_True() {
        // given
        Cell numberCell = new NumberCell(3);
        Cell emptyCell = new EmptyCell();
        Cell numberCell2 = new NumberCell(4);

        numberCell.open();
        emptyCell.open();
        numberCell2.open();

        Cells cells = Cells.of(
                List.of(numberCell, numberCell2, emptyCell)
        );

        // when
        boolean result = cells.isAllChecked();

        // then
        Assertions.assertThat(result).isTrue();
    }

    @DisplayName("하나의 셀이라도 닫힌 상태라면 false를 반환한다.")
    @Test
    void isAllChecked_False() {
        // given
        Cell numberCell = new NumberCell(3);
        Cell emptyCell = new EmptyCell();
        Cell landMineCell = new LandMineCell();

        numberCell.open();
        emptyCell.open();

        Cells cells = Cells.of(
                List.of(numberCell, landMineCell, emptyCell)
        );

        // when
        boolean result = cells.isAllChecked();

        // then
        Assertions.assertThat(result).isFalse();
    }
}
