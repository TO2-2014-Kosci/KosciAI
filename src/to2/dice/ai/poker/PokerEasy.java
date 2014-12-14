package to2.dice.ai.poker;

import java.util.List;

public class PokerEasy extends PokerBot {

    public PokerEasy() {
    }

    @Override
    public void chooseResult(int[] dice, List<int[]> otherDice) {
        this.dice = dice;

        figure = checkFigure(dice);

        for (int i = 0; i < result.length; i++) {
            result[i] = false;
        }

        setResult(result);

        switch (figure) {
            //if poker, full, or straight, rethrow nothing
            case POKER:
            case FULL:
            case STRIT:
                break;
            //if 4, 3, 2x2 or 2, rethrow dice with 1 occurence
            case FOUR:
            case THREE:
            case TWO_PAIRS:
            case PAIR:
                for (int i = 0; i < dice.length; i++) {
                    if (countDice.get(dice[i]) == 1) {
                        result[i] = true;
                    }
                }
                break;
            //if nothing, rethrow 1, maybe you'll get straight :)
            case NOTHING:
                for (int i = 0; i < dice.length; i++) {
                    if (dice[i] == 1) {
                        result[i] = true;
                    }
                }
                break;
            default:
                break;

        }

        setResult(result);

    }

}
