package board;

public class Board implements BoardOperations {

    private char [][] board;
    private Board[] nextBoards;
    private String currentPlayer = "X";
    int index = 0;

    private int lastMoveRow;
    private int lastMoveCol;


    public Board() {
        this.board = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j <3; j++){
                this.board[i][j] = '-';
            }
        }
        this.nextBoards = new Board[9];
    }

    public Board(char[][] currentBoard, Board[] children){
        this.board = currentBoard;
        this.nextBoards = children;
    }

    @Override
    public boolean isValidMove(int row, int col) {
        return board[row][col] == '-';
    }

    @Override
    public void placeMark(String mark, int row, int col) {
        this.board[row][col] = mark.charAt(0);
        this.lastMoveRow = row;
        this.lastMoveCol = col;
    }

    @Override
    public int isWinner() {
        if(isXWinner()){
            return 10;
        }
        if(isOWinner()){
            return -10;
        }
        if(isDraw()){
            return 0;
        }
        return 0;  // default
    }

    @Override
    public boolean isDraw() {
        return !isXWinner() && !isOWinner() && !hasEmptyCells();
    }

    @Override
    public boolean isXWinner() {
        for (int i = 0; i < 3; i++) {
            if (this.board[i][0] == 'X' && this.board[i][1] == 'X' && this.board[i][2] == 'X') {
                return true; // 'X' wins horizontally
            }
        }

        // Check vertically
        for (int j = 0; j < 3; j++) {
            if (this.board[0][j] == 'X' && this.board[1][j] == 'X' && this.board[2][j] == 'X') {
                return true; // 'X' wins vertically
            }
        }

        // Check diagonally (from top-left to bottom-right)
        if (this.board[0][0] == 'X' && this.board[1][1] == 'X' && this.board[2][2] == 'X') {
            return true; // 'X' wins diagonally
        }

        // Check diagonally (from top-right to bottom-left)
        if (this.board[0][2] == 'X' && this.board[1][1] == 'X' && this.board[2][0] == 'X') {
            return true; // 'X' wins diagonally
        }

        return false; // 'X' does not win
    }

    @Override
    public boolean isOWinner() {

        for (int i = 0; i < 3; i++) {
            if (this.board[i][0] == 'O' && this.board[i][1] == 'O' && this.board[i][2] == 'O') {
                return true; // 'X' wins horizontally
            }
        }

        // Check vertically
        for (int j = 0; j < 3; j++) {
            if (this.board[0][j] == 'O' && this.board[1][j] == 'O' && this.board[2][j] == 'O') {
                return true; // 'X' wins vertically
            }
        }

        // Check diagonally (from top-left to bottom-right)
        if (this.board[0][0] == 'O' && this.board[1][1] == 'O' && this.board[2][2] == 'O') {
            return true; // 'X' wins diagonally
        }

        // Check diagonally (from top-right to bottom-left)
        if (this.board[0][2] == 'O' && this.board[1][1] == 'O' && this.board[2][0] == 'O') {
            return true; // 'X' wins diagonally
        }

        return false; // 'X' does not win
    }

    @Override
    public Board copyBoard() {
        // Create a new board.Board object.
        Board childBoard = new Board();

        // Copy the state of the current board.Board object to the new object.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                childBoard.board[i][j] = this.board[i][j];
            }
        }


        childBoard.nextBoards = new Board[9];

        return childBoard;
    }

    @Override
    public boolean hasEmptyCells() {
        boolean hasEmptyPiece = false;
        for(int i = 0; i<3; i++){
            for(int j = 0; j < 3; j++){
                if (this.board[i][j] == '-') {
                    hasEmptyPiece = true;
                    break;
                }
            }
        }
        return hasEmptyPiece;
    }

    @Override
    public void printBoard() {
        for(int i = 0; i <3; i++){
            StringBuilder oneRow = new StringBuilder();
            for(int j = 0; j <3; j++){
                oneRow.append(this.board[i][j]).append(" ");
            }
            System.out.println(oneRow);
            oneRow = new StringBuilder();
        }
    }

    @Override
    public void setPreviousCell(int row, int col) {
        this.board[lastMoveRow][lastMoveCol] = '-';
    }


    @Override
    public Board[] getChildBoards() {
        return nextBoards;
    }

    @Override
    public void createPossibleChildBoards() {
        if(hasEmptyCells()){
            index = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '-') {
                        // Create a new child board.
                        Board childBoard = copyBoard();

                        // Place the current player's mark in the empty square.
                        String player = currentPlayer.equals("X") ? "O" : "X";
                        childBoard.setPlayerTurn(player);
                        childBoard.placeMark(player ,i, j);

                        // Add the child board to the list of child boards.
                        nextBoards[index] = childBoard;
                        index++;

                        childBoard.createPossibleChildBoards();
                    }
                }
            }
        }
    }

    public void setPlayerTurn(String playerName){
        this.currentPlayer = playerName;
    }

    public void removeMark(int row, int col) {
        this.board[row][col] = '-';
    }
}
