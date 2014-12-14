package to2.dice.ai;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;



public abstract class Bot {
	
	
    //Dice chosen to rethrow
    protected boolean[] result = new boolean[5];
    //to2.dice.ai.Bot's dice set
    protected int[] dice = null;
    //Other players' dice sets
    protected List<int[]> otherDice = null;
    //Maximum time for a move
    protected int time;
    //Score the bot is trying to reach
    protected int score;

    protected boolean[] getResult() {
        return result;
    }

    /**
     * Sets 'result' field to the best possible value
     *
     * @param dice bot's dice set
     * @param otherDice other player's dice sets
     */
    protected abstract void chooseResult(int dice[], List<int[]> otherDice);

    protected void setResult(boolean[] result) {
        this.result = result;
    }

    public void setScore(int score) {
        this.score = score;
    }

    protected void setTime(int time) {
        this.time = time;
    }

    //Prints result
    protected void resultToString() {
        for (int i = 0; i < result.length; i++) {
            if (result[i]) {
                System.out.print(1);
            } else {
                System.out.print(0);
            }
        }
        System.out.println();
        System.out.println();
    }

    /**
     * Returns information which dice should be rethrown. Returns result before
     * [time] seconds pass.
     *
     * @param dice array of 5 integers representing set of dice
     * @param otherDice list of int[5] arrays representing dice sets of other
     * players
     * @return array of 5 booleans, true if dice should be rethrown, false
     * otherwise
     */
    public boolean[] makeMove(int[] dice, List<int[]> otherDice) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<boolean[]> future = executor.submit(new Move(dice, otherDice));

        try {
            System.out.println("Started..");
            future.get(time, TimeUnit.SECONDS);
            System.out.println("Finished!");
        } catch (TimeoutException e) {
            System.out.println("Terminated!");
        } catch (Exception ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        }

        executor.shutdownNow();
        return result;
    }

    /**
     * Thread responsible for calling chooseResult method.
     */
    class Move implements Callable<boolean[]> {

        int[] dice;
        List<int[]> otherDice;

        public Move(int[] dice, List<int[]> otherDice) {
            this.dice = dice;
            this.otherDice = otherDice;
        }

        @Override
        public boolean[] call() throws Exception {
            chooseResult(dice, otherDice);
            return getResult();
        }

    }
}
