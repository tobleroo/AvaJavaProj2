import java.util.ArrayList;
import java.util.List;

public class MinMax {

    public static int minMax(Board board, int depth, boolean isMaximizing) {
        int score = board.isWinner();
        depth--;

        if (score == 10) return score - depth;
        if (score == -10) return score + depth;
        if (!board.hasEmptyCells()) return 0;
        if(depth > 0){
            if (isMaximizing) {
                int best = Integer.MIN_VALUE;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board.isValidMove(i, j)) {
                            board.placeMark("X", i, j);
                            best = Math.max(best, minMax(board, depth, false));
                            board.removeMark(i, j);  // Backtrack
                        }
                    }
                }
                return best;
            } else {
                int best = Integer.MAX_VALUE;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board.isValidMove(i, j)) {
                            board.placeMark("O", i, j);
                            best = Math.min(best, minMax(board, depth, true));
                            board.removeMark(i, j);  // Backtrack
                        }
                    }
                }
                return best;
            }
        }else return 0;

    }

    public static int findBestMove(Board board, boolean isMaximizing, int depth) {
        int bestMove = -1;
        int bestValue = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isValidMove(i, j)) {
                    if (isMaximizing) {
                        board.placeMark("X", i, j);
                        int moveValue = minMax(board, depth, false);
                        board.removeMark(i, j);

                        if (moveValue > bestValue) {
                            bestValue = moveValue;
                            bestMove = i * 3 + j;
                        }
                    } else {
                        board.placeMark("O", i, j);
                        int moveValue = minMax(board, depth, true);
                        board.removeMark(i, j);

                        if (moveValue < bestValue) {
                            bestValue = moveValue;
                            bestMove = i * 3 + j;
                        }
                    }
                }
            }
        }
        return bestMove;
    }
}
