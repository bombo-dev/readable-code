package cleancode.minesweeper.tobe.minesweeper.io;

import cleancode.minesweeper.tobe.minesweeper.exception.GameException;

public class BoardIndexConverter {

    private static final char BASE_CHAR_FOR_COL = 'a';
    public static final int MIN_COL_INDEX = 0;
    public static final int MAX_COL_INDEX = 25;

    public int getSelectedRowIndex(String cellInput) {
        String cellInputRow = cellInput.substring(1);
        return convertRowFrom(cellInputRow);
    }

    public int getSelectedColIndex(String cellInput) {
        char cellInputCol = cellInput.charAt(0);
        return convertColFrom(cellInputCol);
    }

    private int convertRowFrom(String cellInputRow) {
        int rowIndex = Integer.parseInt(cellInputRow) - 1;
        if (rowIndex < 0) {
            throw new GameException("잘못된 입력입니다.");
        }

        return rowIndex;
    }

    private int convertColFrom(char cellInputCol) {
        if (Character.isAlphabetic(cellInputCol) && Character.isUpperCase(cellInputCol)) {
            cellInputCol = Character.toLowerCase(cellInputCol);
        }

        int colIndex = cellInputCol - BASE_CHAR_FOR_COL;
        if (colIndex < MIN_COL_INDEX || colIndex > MAX_COL_INDEX) {
            throw new GameException("잘못된 입력입니다.");
        }

        return colIndex;
    }

}
