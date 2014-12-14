package to2.dice.ai;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import to2.dice.game.BotLevel;
import to2.dice.game.GameType;

public class TestKAI {

    Bot botNPlusEasy;
    Bot botNPlusHard;
    Bot botNMulEasy;
    Bot botNMulHard;
    Bot botPokerEasy;
    Bot botPokerHard;
    public List<int[]> otherDice = new ArrayList<int[]>();

    public TestKAI() {

        botNPlusEasy = BotFactory.createBot(GameType.NPLUS, BotLevel.EASY, 3);
        botNPlusHard = BotFactory.createBot(GameType.NPLUS, BotLevel.HARD, 1);
        botNMulEasy = BotFactory.createBot(GameType.NMUL, BotLevel.EASY, 3);
        botNMulHard = BotFactory.createBot(GameType.NMUL, BotLevel.HARD, 3);
        botPokerEasy = BotFactory.createBot(GameType.POKER, BotLevel.EASY, 3);
        botPokerHard = BotFactory.createBot(GameType.POKER, BotLevel.HARD, 3);
    }

    public static void main(String[] args) throws Exception {

        TestKAI testkai = new TestKAI();

        testkai.testNPlusEasy();
        testkai.testNplusHard();

        testkai.testNmulEasy();
        testkai.testNmulHard();

        testkai.testPokerEasyNothing();
        testkai.testPokerEasyPair();
        testkai.testPokerEasyTwoPairs();
        testkai.testPokerEasyThree();
        testkai.testPokerEasyStrit();
        testkai.testPokerEasyFull();
        testkai.testPokerEasyFour();
        testkai.testPokerEasyPoker();

        System.out.println("to2.dice.ai.poker.Poker hard");
        testkai.testPokerHardNothing();
        testkai.testPokerHardPair();
        testkai.testPokerHardTwoPairs();
        testkai.testPokerHardThree();
        testkai.testPokerHardStrit();
        testkai.testPokerHardFull();
        testkai.testPokerHardFour();
        testkai.testPokerHardPoker();
    }

    @Test
    public void testNPlusEasy() throws Exception {

        int[] dice = new int[]{1, 1, 1, 1, 1};
        boolean[] results;
        botNPlusEasy.setScore(6);
        results = botNPlusEasy.makeMove(dice, otherDice);
        org.junit.Assert.assertTrue((results[0] && !results[1] && !results[2] && !results[3] && !results[4])
                || (!results[0] && results[1] && !results[2] && !results[3] && !results[4])
                || (!results[0] && !results[1] && results[2] && !results[3] && !results[4])
                || (!results[0] && !results[1] && !results[2] && results[3] && !results[4])
                || (!results[0] && !results[1] && !results[2] && !results[3] && results[4]));
    }

    @Test
    public void testNplusHard() throws Exception {

        int[] dice = new int[]{6, 6, 6, 6, 1};
        boolean[] results;
        botNPlusHard.setScore(30);
        results = botNPlusHard.makeMove(dice, otherDice);
        org.junit.Assert.assertTrue(!results[0] && !results[1] && !results[2] && !results[3] && results[4]);
    }

    @Test
    public void testNmulEasy() throws Exception {

        int[] dice = new int[]{1, 2, 1, 3, 1};
        boolean[] results;
        botNMulEasy.setScore(36);
        results = botNMulEasy.makeMove(dice, otherDice);
        org.junit.Assert.assertTrue((results[0] && !results[1] && !results[2] && !results[3] && !results[4])
                || (!results[0] && !results[1] && results[2] && !results[3] && !results[4])
                || (!results[0] && !results[1] && !results[2] && !results[3] && results[4]));
    }

    @Test
    public void testNmulHard() throws Exception {

        int[] dice = new int[]{6, 6, 2, 6, 1};
        boolean[] results;
        botNMulHard.setScore(6 ^ 5);
        results = botNPlusHard.makeMove(dice, otherDice);
        org.junit.Assert.assertTrue(!results[0] && !results[1] && results[2] && !results[3] && results[4]);
    }

    @Test
    public void testPokerEasyNothing() throws Exception {

        int[] dice = new int[]{3, 6, 2, 4, 1};
        boolean[] results = botPokerEasy.makeMove(dice, otherDice);
        org.junit.Assert.assertTrue(!results[0] && !results[1] && !results[2] && !results[3] && results[4]);
    }

    @Test
    public void testPokerEasyPair() throws Exception {

        int[] dice = new int[]{3, 6, 2, 6, 1};
        boolean[] results = botPokerEasy.makeMove(dice, otherDice);
        org.junit.Assert.assertTrue(results[0] && !results[1] && results[2] && !results[3] && results[4]);
    }

    @Test
    public void testPokerEasyTwoPairs() throws Exception {

        int[] dice = new int[]{3, 1, 2, 3, 1};
        boolean[] results = botPokerEasy.makeMove(dice, otherDice);
        org.junit.Assert.assertTrue(!results[0] && !results[1] && results[2] && !results[3] && !results[4]);
    }

    @Test
    public void testPokerEasyThree() throws Exception {

        int[] dice = new int[]{3, 6, 6, 6, 1};
        boolean[] results = botPokerEasy.makeMove(dice, otherDice);
        org.junit.Assert.assertTrue(results[0] && !results[1] && !results[2] && !results[3] && results[4]);
    }

    @Test
    public void testPokerEasyStrit() throws Exception {

        int[] dice = new int[]{1, 2, 4, 3, 5};
        boolean[] results = botPokerEasy.makeMove(dice, otherDice);
        org.junit.Assert.assertTrue(!results[0] && !results[1] && !results[2] && !results[3] && !results[4]);
    }

    @Test
    public void testPokerEasyFull() throws Exception {

        int[] dice = new int[]{3, 6, 3, 6, 3};
        boolean[] results = botPokerEasy.makeMove(dice, otherDice);
        org.junit.Assert.assertTrue(!results[0] && !results[1] && !results[2] && !results[3] && !results[4]);
    }

    @Test
    public void testPokerEasyFour() throws Exception {

        int[] dice = new int[]{3, 6, 6, 6, 6};
        boolean[] results = botPokerEasy.makeMove(dice, otherDice);
        org.junit.Assert.assertTrue(results[0] && !results[1] && !results[2] && !results[3] && !results[4]);
    }

    @Test
    public void testPokerEasyPoker() throws Exception {

        int[] dice = new int[]{5, 5, 5, 5, 5};
        boolean[] results = botPokerEasy.makeMove(dice, otherDice);
        org.junit.Assert.assertTrue(!results[0] && !results[1] && !results[2] && !results[3] && !results[4]);
    }

    @Test
    public void testPokerHardNothing() throws Exception {

        int[] dice = new int[]{5, 1, 3, 4, 6};
        otherDice.add(new int[]{1, 3, 2, 5, 4});
        boolean[] results = botPokerHard.makeMove(dice, otherDice);
        org.junit.Assert.assertTrue(!results[0] && results[1] && !results[2] && !results[3] && !results[4]);
        otherDice.removeAll(otherDice);
    }

    @Test
    public void testPokerHardPair() throws Exception {

        int[] dice = new int[]{5, 2, 3, 4, 2};
        otherDice.add(new int[]{6, 1, 6, 6, 6});
        boolean[] results = botPokerHard.makeMove(dice, otherDice);
        org.junit.Assert.assertTrue(results[0] && !results[1] && results[2] && results[3] && !results[4]);
        otherDice.removeAll(otherDice);
    }

    @Test
    public void testPokerHardTwoPairs() throws Exception {

        int[] dice = new int[]{5, 2, 3, 5, 2};
        otherDice.add(new int[]{3, 3, 4, 4, 3});
        boolean[] results = botPokerHard.makeMove(dice, otherDice);
        org.junit.Assert.assertTrue(!results[0] && !results[1] && results[2] && !results[3] && !results[4]);
        otherDice.removeAll(otherDice);
    }

    @Test
    public void testPokerHardThree() throws Exception {

        int[] dice = new int[]{5, 2, 2, 4, 2};
        otherDice.add(new int[]{6, 1, 6, 6, 6});
        boolean[] results = botPokerHard.makeMove(dice, otherDice);
        org.junit.Assert.assertTrue(results[0] && !results[1] && !results[2] && results[3] && !results[4]);
        otherDice.removeAll(otherDice);
    }

    @Test
    public void testPokerHardStrit() throws Exception {

        int[] dice = new int[]{1, 2, 3, 4, 5};
        otherDice.add(new int[]{2, 3, 4, 5, 6});
        boolean[] results = botPokerHard.makeMove(dice, otherDice);
        org.junit.Assert.assertTrue(results[0] && !results[1] && !results[2] && !results[3] && !results[4]);
        otherDice.removeAll(otherDice);
    }

    @Test
    public void testPokerHardFull() throws Exception {

        int[] dice = new int[]{3, 3, 3, 2, 2};
        otherDice.add(new int[]{3, 3, 3, 1, 1});
        otherDice.add(new int[]{2, 2, 3, 3, 3});
        boolean[] results = botPokerHard.makeMove(dice, otherDice);
        org.junit.Assert.assertTrue(!results[0] && !results[1] && !results[2] && !results[3] && !results[4]);
        otherDice.removeAll(otherDice);
    }

    @Test
    public void testPokerHardFour() throws Exception {

        int[] dice = new int[]{5, 5, 5, 5, 6};
        otherDice.add(new int[]{6, 6, 1, 6, 6});
        boolean[] results = botPokerHard.makeMove(dice, otherDice);
        org.junit.Assert.assertTrue(!results[0] && !results[1] && !results[2] && !results[3] && results[4]);
        otherDice.removeAll(otherDice);
    }

    @Test
    public void testPokerHardPoker() throws Exception {

        int[] dice = new int[]{5, 5, 5, 5, 5};
        otherDice.add(new int[]{6, 6, 6, 6, 6});
        boolean[] results = botPokerHard.makeMove(dice, otherDice);
        org.junit.Assert.assertTrue(results[0] && results[1] && results[2] && results[3] && results[4]);
        otherDice.removeAll(otherDice);
    }

}
