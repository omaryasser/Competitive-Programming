import java.util.Arrays;

/**
 * Created by omar on 20/07/17.
 */
public class QuizShow {
    public int wager(int[] scores, int wager1, int wager2) {
        int best = 0, bestWins = 0;
        for (int i = 0; i <= scores[0]; i++) {
            int cntWins = 0;
            int[] wager = {i, wager1, wager2};
            for (int j = 0; j < 1 << 3; j++) {
                int[] newScores = scores.clone();
                for (int k = 0; k < 3; k++) {
                    if ((j & (1 << k)) != 0)
                        newScores[k] += wager[k];
                    else
                        newScores[k] -= wager[k];
                }
                if (newScores[0] > newScores[1] && newScores[0] > newScores[2])
                    cntWins++;
            }
            if (cntWins > bestWins) {
                bestWins = cntWins;
                best = i;
            }
        }
        return best;
    }
    
}
