package to2.dice.ai.ngames.nmul;

import java.util.List;

public class NMulHard extends NMul {

    public NMulHard() {
        allResults = initList();
    }

    @Override
    public void chooseResult(int dice[], List<int[]> otherDice) {

        this.dice = dice;

        int mulLeft;		//product of dice which are not going to be rethrown
        double bestProb = 0;	//best propability
        boolean[] bestResult;	//best result
        double prob = 0;            //propability of current result
        int target;		//what must be the product of rethrown dice
        int diceToThrow;        //amount of dice to rethrow in current result
        int allThrows;          //amount of possible results in current result
        int currMatches;        //amount of matching results in current result
        boolean[] result;
        
        //iterating over all possible results
        for (boolean[] rslt : allResults) {

            result = rslt;
            mulLeft = leftToScore(dice, result);

            //Checking if current result allows reaching target
            if (score % mulLeft == 0) {
                target = score / mulLeft;

                diceToThrow = 0;
                for (int j = 0; j < result.length; j++) {
                    if (result[j]) {
                        diceToThrow++;
                    }
                }

                countMatches(diceToThrow, 1, target);
                currMatches = matches;
                matches = 0;

                allThrows = 1;
                for (int j = 0; j < diceToThrow; j++) {
                    allThrows *= 6;
                }

                prob = (1.0 * currMatches) / allThrows;
               

                if (Double.compare(prob, bestProb) == 1) {
                    bestProb = prob;
                    bestResult = result;
                    setResult(bestResult);
                }
            }
        }
    }

}
