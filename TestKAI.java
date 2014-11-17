
import java.util.ArrayList;

import javax.print.attribute.standard.MediaSize.Other;

import org.junit.Test;

public class TestKAI {
    
	DiceBot botNPlusEasy;
    DiceBot botNPlusHard; 
    DiceBot botNMulEasy;
    DiceBot botNMulHard;
    DiceBot botPokerEasy;
    public DiceBot botPokerHard;
    public ArrayList<int[]> otherDice = new ArrayList<int[]>();
    
    public TestKAI(){
    	BotFactory fabrykaBotow = new BotFactory();
        botNPlusEasy = fabrykaBotow.createBot(GameType.NPLUS, 0, 0);
        botNPlusHard = fabrykaBotow.createBot(GameType.NPLUS, 1, 0);
    	botNMulEasy = fabrykaBotow.createBot(GameType.NMUL, 0, 0);
    	botNMulHard = fabrykaBotow.createBot(GameType.NMUL, 1, 0);
    	botPokerEasy = fabrykaBotow.createBot(GameType.POKER, 0, 0);
    	botPokerHard = fabrykaBotow.createBot(GameType.POKER, 1, 0);   
    }
        
	public static void main(String[] args){
		
		
		TestKAI testkai = new TestKAI();
		testkai.otherDice.add(new int[]{1, 2, 4, 5, 6});
		testkai.otherDice.add(new int[]{1, 3, 4, 5, 6});
		
		
		int[] dice = new int[]{6, 6, 6, 6, 1};
		testkai.botPokerHard.makeMove(dice, testkai.otherDice);
		/*
		testkai.testNplusHard();
        testkai.testNmulHard();
        
        testkai.testPokerEasyPair();
        testkai.testPokerEasyThree();
        */
	}
	
        @Test
	public void testNplusHard(){
				
		int[] dice = new int[]{6, 6, 6, 6, 1};
        boolean[] results = new boolean[5];
        botNPlusHard.setScore(30);
		results = botNPlusHard.makeMove(dice, otherDice);
		org.junit.Assert.assertTrue(!results[0] && !results[1] && !results[2] && !results[3] && results[4]);
	}
        
        @Test
	public void testNmulHard(){
				
		int[] dice = new int[]{6, 6, 2, 6, 1};
        boolean[] results = new boolean[5];
        botNMulHard.setScore(6^5);
		results = botNPlusHard.makeMove(dice, otherDice);
		org.junit.Assert.assertTrue(!results[0] && !results[1] && results[2] && !results[3] && results[4]);
	}
        @Test
    public void testPokerEasyPair(){
				
		int[] dice = new int[]{3, 6, 2, 6, 1};
        boolean[] results = botPokerEasy.makeMove(dice, otherDice);
		org.junit.Assert.assertTrue(results[0] && !results[1] && results[2] && !results[3] && results[4]);
	}
        @Test
    public void testPokerEasyThree(){
				
		int[] dice = new int[]{3, 6, 6, 6, 1};
        boolean[] results = botPokerEasy.makeMove(dice, otherDice);
		org.junit.Assert.assertTrue(results[0] && !results[1] && !results[2] && !results[3] && results[4]);
	}
	
}