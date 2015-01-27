package to2.dice.ai.ngames.nplus;

import java.util.List;

public class NPlusHard extends NPlus {

    public NPlusHard() {
        allResults = initList();
    }

    @Override
    public void chooseResult(int dice[], List<int[]> otherDice) {

        /*try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(NPlusHard.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        this.dice = dice;
        boolean[] option;

        int sumLeft;		//sum of dice which are not going to be rethrown
        double bestProb = 0;	//best propability
        boolean[] bestResult;	//best option
        double prob;            //propability of current option
        int target;		//what must be the product of rethrown dice
        int diceToThrow;        //amount of dice to rethrow in current option
        int allThrows;          //amount of possible results in current option
        int currMatches;        //amount of matching results in current option

        //iterating over all possible results
        for (boolean[] rslt : allResults) {

            option = rslt;
            sumLeft = leftToScore(dice, option);
            target = score - sumLeft;

            diceToThrow = 0;
            for (int j = 0; j < option.length; j++) {
                if (option[j]) {
                    diceToThrow++;
                }
            }

            countMatches(diceToThrow, 0, target);
            currMatches = matches;
            matches = 0;

            allThrows = 1;
            for (int j = 0; j < diceToThrow; j++) {
                allThrows *= 6;
            }

            prob = (1.0 * currMatches) / allThrows;
            if (Double.compare(prob , bestProb) == 1) {
                bestProb = prob;
                bestResult = option;
                setResult(bestResult);
            }
        }
    }

}
