package to2.dice.ai.ngames.nmul;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

public class NMulEasy extends NMul {

    public NMulEasy() {
        allResults = initList();
    }

    @Override
    public void chooseResult(int dice[], List<int[]> otherDice) {

        this.dice = dice;

        HashMap<boolean[], Double> map = new HashMap<boolean[], Double>();
        ValueComparator bvc = new ValueComparator(map);
        TreeMap<boolean[], Double> sorted_map = new TreeMap<boolean[], Double>(bvc);

        int mulLeft;            //product of dice which are not going to be rethrown
        double prob;            //propability of current option
        double bestProb = 0;	//best propability
        int target;		//what must be the product of rethrown dice
        int diceToThrow;        //amount of dice to rethrow in current option
        int allThrows;          //amount of possible results in current option
        int currMatches;        //amount of matching results in current option

        //iterating over all possible results
        for (boolean[] rslt : allResults) {
            result = rslt;
            mulLeft = leftToScore(dice, result);
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
            //Checking if current option is the best
            if (Double.compare(prob, bestProb) == 1) {
                bestProb = prob;
                setResult(result);
            }
            map.put(rslt, prob);
        }

        //sorting map
        sorted_map.putAll(map);

        Random generator = new Random();
        int index = generator.nextInt(3);
        Iterator<boolean[]> iterator = sorted_map.keySet().iterator();

        for (int j = 0; j < index && iterator.hasNext(); j++) {
            iterator.next();
        }
        result = (boolean[]) iterator.next();

        setResult(result);

    }

}
