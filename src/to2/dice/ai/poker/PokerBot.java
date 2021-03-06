package to2.dice.ai.poker;

import to2.dice.ai.Bot;

import java.util.HashMap;
import java.util.Map;

abstract class PokerBot extends Bot {

    protected Map<Integer, Integer> countDice; //map - number on dice : number of its occurences
    public Figure figure; //current figure

    public Figure checkFigure(int[] dice) {

        //Cleaning a map
        countDice = fillMap(dice);

        if (countDice.containsValue(5)) {
            figure = Figure.POKER;
        } else if (countDice.containsValue(4)) {
            figure = Figure.FOUR;
        } else if (countDice.containsValue(3)) {
            if (countDice.containsValue(2)) {
                figure = Figure.FULL;
            } else {
                figure = Figure.THREE;
            }
        } else if ((countDice.get(2) == 1) && (countDice.get(3) == 1) && (countDice.get(4) == 1) && (countDice.get(5) == 1)) {
            figure = Figure.STRIT;
        } else {
            int repeat = 0;
            for (int i = 1; i < 7; i++) {
                if (countDice.get(i) == 2) {
                    repeat++;
                }
            }
            if (repeat == 2) {
                figure = Figure.TWO_PAIRS;
            } else if (repeat == 1) {
                figure = Figure.PAIR;
            } else {
                figure = Figure.NOTHING;
            }
        }

        return figure;
    }

    //Filling a map
    protected Map<Integer, Integer> fillMap(int[] dice) {
        Map<Integer, Integer> mapCountDice = new HashMap<Integer, Integer>();
        for (int i = 1; i <= 6; i++) {
            mapCountDice.put(i, 0);
        }
        for (int i = 0; i < dice.length; i++) {
            mapCountDice.put(dice[i], mapCountDice.get(dice[i]) + 1);
        }
        return mapCountDice;
    }

}
