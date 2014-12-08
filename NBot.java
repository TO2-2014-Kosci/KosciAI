package DiceAI;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

abstract class NBot extends Bot {

    //Amount of matches for current option
    protected int matches = 0;
    //List of all possible options of results
    protected List<boolean[]> allResults = null;

    /**
     *
     * @param dice bot's dice
     * @param result current combination of rethrown / not rethrown dice
     * @return
     */
    abstract public int leftToScore(int[] dice, boolean[] result);

    /**
     *
     * @param toThrow
     * @param current
     * @param target
     */
    abstract public void countMatches(int toThrow, int current, int target);

    //Initializes allResults list
    protected ArrayList<boolean[]> initList() {
        ArrayList<boolean[]> tab = new ArrayList<>();
        for (int liczba = 0; liczba < 32; liczba++) {
            result = new boolean[5];
            //Adding one of options
            for (int i = 0; i < result.length; i++) {
                result[4 - i] = (liczba >> i & 1) != 0;
            }
            tab.add(result);
        }
        return tab;
    }

    //Class required for sorting possible results
    class ValueComparator implements Comparator<boolean[]> {

        Map<boolean[], Double> base;

        public ValueComparator(Map<boolean[], Double> base) {
            this.base = base;
        }

        @Override
        public int compare(boolean[] a, boolean[] b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
