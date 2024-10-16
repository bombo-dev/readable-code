package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CellStateTest {

    @DisplayName("셀 초기화 시에는 플래그와 오픈 상태가 모두 false이다.")
    @Test
    void initialize() {
        // given
        // when
        CellState cellState = CellState.initialize();

        // then
        Assertions.assertThat(cellState).satisfies(state -> {
            Assertions.assertThat(state.isFlagged()).isFalse();
            Assertions.assertThat(state.isOpened()).isFalse();
        });
    }

    @DisplayName("셀을 플래그하면 플래그 상태가 true이다.")
    @Test
    void flag() {
        // given
        CellState cellState = CellState.initialize();

        // when
        cellState.flag();

        // then
        Assertions.assertThat(cellState.isFlagged()).isTrue();
    }

    @DisplayName("셀을 열면 오픈 상태가 true이다.")
    @Test
    void open() {
        // given
        CellState cellState = CellState.initialize();

        // when
        cellState.open();

        // then
        Assertions.assertThat(cellState.isOpened()).isTrue();
    }

    @DisplayName("셀을 플래그 한 이후 셀을 열면 플래그 상태는 false이고 셀은 열린다.")
    @Test
    void openAfterFlag() {
        // given
        CellState cellState = CellState.initialize();
        cellState.flag();

        // when
        cellState.open();

        // then
        Assertions.assertThat(cellState).satisfies(state -> {
            Assertions.assertThat(state.isFlagged()).isFalse();
            Assertions.assertThat(state.isOpened()).isTrue();
        });
    }
}
