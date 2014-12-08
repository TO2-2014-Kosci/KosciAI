package DiceAI;

import java.util.List;

class NMulHard extends NMul {

    public NMulHard() {
        allResults = initList();
    }

    @Override
    public void chooseResult(int dice[], List<int[]> otherDice) {

        this.dice = dice;

        int mulLeft;		//product of dice which are not going to be rethrown
        double bestProb = 0;	//best propability
        boolean[] bestResult;	//best option
        double prob;            //propability of current option
        int target;		//what must be the product of rethrown dice
        int diceToThrow;        //amount of dice to rethrow in current option
        int allThrows;          //amount of possible results in current option
        int currMatches;        //amount of matching results in current option

        //iterating over all possible results
        for (boolean[] rslt : allResults) {

            result = rslt;
            mulLeft = leftToScore(dice, result);

            //Checking if current option allows reaching target
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

                if (prob > bestProb) {
                    bestProb = prob;
                    bestResult = result;
                    setResult(bestResult);
                }
            }
        }
    }

}
