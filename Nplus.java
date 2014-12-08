package DiceAI;

public abstract class NPlus extends NBot {

    @Override
    public int leftToScore(int[] dice, boolean[] result) {

        int left = 0;

        for (int i = 0; i < result.length; i++) {
            if (!result[i]) {
                left += dice[i];
            }
        }
        return left;
    }

    @Override
    public void countMatches(int toThrow, int current, int target) {
        if (toThrow == 0) {
            if (current == target) {
                matches++;
            }
        } else {
            for (int j = 1; j < 7; j++) {
                countMatches(toThrow - 1, current + j, target);
            }
        }
    }

}
