/**
 * Created by omar on 30/07/17.
 */
public class AntiChess {
    Piece pawns[], queen;
    boolean[] isDead;
    int NUM_PAWNS;
    int NUM_DIED;
    int maximizerMove (int guranteedMin, int guranteedMax) {
        if (guranteedMin == guranteedMax)
            return guranteedMax;
        if (NUM_DIED > guranteedMax)
            guranteedMax = NUM_DIED;
        if (NUM_DIED > guranteedMin)
            return guranteedMin;
        for (int i = 0; i < NUM_PAWNS; i++)
            if (!isDead[i]) {
                if (pawns[i].row == queen.row - 1 && Math.abs(queen.col- pawns[i].col) == 1)
                    return guranteedMax;
            }
        for (int i = 0; i < NUM_PAWNS; i++) {
            if (!isDead[i]) {
                if (pawns[i].row == 7 || pawns[i].row == queen.row - 1 && pawns[i].col == queen.col)
                    continue;
                pawns[i].row++;
                guranteedMax = minimizerMove(guranteedMin, guranteedMax);
                pawns[i].row--;
                if (pawns[i].row == 1 && !(pawns[i].col == queen.col && (pawns[i].row == queen.row - 1 || pawns[i].row == queen.row - 2))) {
                    pawns[i].row += 2;
                    guranteedMax = minimizerMove(guranteedMin, guranteedMax);
                    pawns[i].row -= 2;
                }
                if (guranteedMin < guranteedMax) return guranteedMax;
            }
        }
        return Math.min(guranteedMin, guranteedMax);
    }

    int minimizerMove (int guranteedMin, int guranteedMax) {
        if (guranteedMin == guranteedMax)
            return guranteedMin;
        if (NUM_DIED >= guranteedMin)
            return guranteedMin;

        boolean found = false;
        // left
        main : for (int nc = queen.col, nr = queen.row; nc >= 0; nc--) {
            for (int j = 0; j < NUM_PAWNS; j++)
                if (!isDead[j] && pawns[j].row == nr && pawns[j].col == nc) {
                    found = true;
                    int tmpC = queen.col, tmpR = queen.row;
                    queen.col = nc;
                    queen.row = nr;
                    isDead[j] = true;
                    NUM_DIED++;
                    guranteedMin = maximizerMove(guranteedMin, guranteedMax);
                    NUM_DIED--;
                    isDead[j] = false;
                    queen.col = tmpC;
                    queen.row = tmpR;
                    break main;
                }
        }

        // right
        main : for (int nc = queen.col, nr = queen.row; nc < 8; nc++) {
            for (int j = 0; j < NUM_PAWNS; j++)
                if (!isDead[j] && pawns[j].row == nr && pawns[j].col == nc) {
                    found = true;
                    int tmpC = queen.col, tmpR = queen.row;
                    queen.col = nc;
                    queen.row = nr;
                    isDead[j] = true;
                    NUM_DIED++;
                    guranteedMin = maximizerMove(guranteedMin, guranteedMax);
                    NUM_DIED--;
                    isDead[j] = false;
                    queen.col = tmpC;
                    queen.row = tmpR;
                    break main;
                }
        }

        // down
        main : for (int nc = queen.col, nr = queen.row; nr < 8; nr++) {
            for (int j = 0; j < NUM_PAWNS; j++)
                if (!isDead[j] && pawns[j].row == nr && pawns[j].col == nc) {
                    found = true;
                    int tmpC = queen.col, tmpR = queen.row;
                    queen.col = nc;
                    queen.row = nr;
                    isDead[j] = true;
                    NUM_DIED++;
                    guranteedMin = maximizerMove(guranteedMin, guranteedMax);
                    NUM_DIED--;
                    isDead[j] = false;
                    queen.col = tmpC;
                    queen.row = tmpR;
                    break main;
                }
        }

        // up
        main : for (int nc = queen.col, nr = queen.row; nr >= 0; nr--) {
            for (int j = 0; j < NUM_PAWNS; j++)
                if (!isDead[j] && pawns[j].row == nr && pawns[j].col == nc) {
                    found = true;
                    int tmpC = queen.col, tmpR = queen.row;
                    queen.col = nc;
                    queen.row = nr;
                    isDead[j] = true;
                    NUM_DIED++;
                    guranteedMin = maximizerMove(guranteedMin, guranteedMax);
                    NUM_DIED--;
                    isDead[j] = false;
                    queen.col = tmpC;
                    queen.row = tmpR;
                    break main;
                }
        }

        // up right
        main : for (int nc = queen.col, nr = queen.row; nr >= 0 && nc < 8; nr--, nc++) {
            for (int j = 0; j < NUM_PAWNS; j++)
                if (!isDead[j] && pawns[j].row == nr && pawns[j].col == nc) {
                    found = true;
                    int tmpC = queen.col, tmpR = queen.row;
                    queen.col = nc;
                    queen.row = nr;
                    isDead[j] = true;
                    NUM_DIED++;
                    guranteedMin = maximizerMove(guranteedMin, guranteedMax);
                    NUM_DIED--;
                    isDead[j] = false;
                    queen.col = tmpC;
                    queen.row = tmpR;
                    break main;
                }
        }

        // up left
        main : for (int nc = queen.col, nr = queen.row; nr >= 0 && nc >= 0; nr--, nc--) {
            for (int j = 0; j < NUM_PAWNS; j++)
                if (!isDead[j] && pawns[j].row == nr && pawns[j].col == nc) {
                    found = true;
                    int tmpC = queen.col, tmpR = queen.row;
                    queen.col = nc;
                    queen.row = nr;
                    isDead[j] = true;
                    NUM_DIED++;
                    guranteedMin = maximizerMove(guranteedMin, guranteedMax);
                    NUM_DIED--;
                    isDead[j] = false;
                    queen.col = tmpC;
                    queen.row = tmpR;
                    break main;
                }
        }

        // down left
        main : for (int nc = queen.col, nr = queen.row; nr < 8 && nc >= 0; nr++, nc--) {
            for (int j = 0; j < NUM_PAWNS; j++)
                if (!isDead[j] && pawns[j].row == nr && pawns[j].col == nc) {
                    found = true;
                    int tmpC = queen.col, tmpR = queen.row;
                    queen.col = nc;
                    queen.row = nr;
                    isDead[j] = true;
                    NUM_DIED++;
                    guranteedMin = maximizerMove(guranteedMin, guranteedMax);
                    NUM_DIED--;
                    isDead[j] = false;
                    queen.col = tmpC;
                    queen.row = tmpR;
                    break main;
                }
        }

        // down right
        main : for (int nc = queen.col, nr = queen.row; nr < 8 && nc < 8; nr++, nc++) {
            for (int j = 0; j < NUM_PAWNS; j++)
                if (!isDead[j] && pawns[j].row == nr && pawns[j].col == nc) {
                    found = true;
                    int tmpC = queen.col, tmpR = queen.row;
                    queen.col = nc;
                    queen.row = nr;
                    isDead[j] = true;
                    NUM_DIED++;
                    guranteedMin = maximizerMove(guranteedMin, guranteedMax);
                    NUM_DIED--;
                    isDead[j] = false;
                    queen.col = tmpC;
                    queen.row = tmpR;
                    break main;
                }
        }

        if (!found) return Math.min(guranteedMin, Math.max(guranteedMax, NUM_DIED));
        else return Math.max(guranteedMax, guranteedMin);
    }
    public int sacrifice(String[] white, String black) {
        pawns = new Piece[NUM_PAWNS = white.length];
        isDead = new boolean[NUM_PAWNS];
        for (int i = 0; i < NUM_PAWNS; i++)
            pawns[i] = new Piece(white[i].charAt(1) - '0', white[i].charAt(0) - 'a');
        queen = new Piece(black.charAt(1) - '0', black.charAt(0) - 'a');
        return maximizerMove ((int)1e9, (int)-1e9);
    }

    class Piece {
        int row, col;
        Piece (int r, int c) {
            row = r - 1;
            col = c;
        }
    }
}
