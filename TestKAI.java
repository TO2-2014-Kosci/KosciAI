
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestKAI {
    
	DiceBot botNPlusEasy;
    DiceBot botNPlusHard; 
    DiceBot botNMulEasy;
    DiceBot botNMulHard;
    DiceBot botPokerEasy;
    DiceBot botPokerHard;
    public List<int[]> otherDice = new ArrayList<int[]>();
    
    public TestKAI(){
    	BotFactory fabrykaBotow = new BotFactory();
        botNPlusEasy = fabrykaBotow.createBot(GameType.NPLUS, 0, 1);
        botNPlusHard = fabrykaBotow.createBot(GameType.NPLUS, 1, 5);
    	botNMulEasy = fabrykaBotow.createBot(GameType.NMUL, 0, 4);
    	botNMulHard = fabrykaBotow.createBot(GameType.NMUL, 1, 4);
    	botPokerEasy = fabrykaBotow.createBot(GameType.POKER, 0, 4);
    	botPokerHard = fabrykaBotow.createBot(GameType.POKER, 1, 4);   
    }
        
    
	public static void main(String[] args){
		
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
	public void testNPlusEasy(){
				
		int[] dice = new int[]{1, 1, 1, 1, 1};
        boolean[] results = new boolean[5];
        botNPlusEasy.setScore(6);
		results = botNPlusEasy.makeMove(dice, otherDice);
		org.junit.Assert.assertTrue((results[0] && !results[1] && !results[2] && !results[3] && !results[4])||
									(!results[0] && results[1] && !results[2] && !results[3] && !results[4])||
									(!results[0] && !results[1] && results[2] && !results[3] && !results[4])||
									(!results[0] && !results[1] && !results[2] && results[3] && !results[4])||
									(!results[0] && !results[1] && !results[2] && !results[3] && results[4]));
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
	public void testNmulEasy(){
				
		int[] dice = new int[]{1, 2, 1, 3, 1};
        boolean[] results = new boolean[5];
        botNMulEasy.setScore(36);
		results = botNMulEasy.makeMove(dice, otherDice);
		org.junit.Assert.assertTrue((results[0] && !results[1] && !results[2] && !results[3] && !results[4])||
								   (!results[0] && !results[1] && results[2] && !results[3] && !results[4])||
								   (!results[0] && !results[1] && !results[2] && !results[3] && results[4]));
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
    public void testPokerEasyNothing(){
				
		int[] dice = new int[]{3, 6, 2, 4, 1};
        boolean[] results = botPokerEasy.makeMove(dice, otherDice);
		org.junit.Assert.assertTrue(!results[0] && !results[1] && !results[2] && !results[3] && results[4]);
	} 
        @Test
    public void testPokerEasyPair(){
		
		int[] dice = new int[]{3, 6, 2, 6, 1};
        boolean[] results = botPokerEasy.makeMove(dice, otherDice);
		org.junit.Assert.assertTrue(results[0] && !results[1] && results[2] && !results[3] && results[4]);
	} 
        @Test
    public void testPokerEasyTwoPairs(){
		
		int[] dice = new int[]{3, 1, 2, 3, 1};
        boolean[] results = botPokerEasy.makeMove(dice, otherDice);
		org.junit.Assert.assertTrue(!results[0] && !results[1] && results[2] && !results[3] && !results[4]);
	} 
        @Test
    public void testPokerEasyThree(){
				
		int[] dice = new int[]{3, 6, 6, 6, 1};
        boolean[] results = botPokerEasy.makeMove(dice, otherDice);
		org.junit.Assert.assertTrue(results[0] && !results[1] && !results[2] && !results[3] && results[4]);
	}
        
        @Test
    public void testPokerEasyStrit(){
		
		int[] dice = new int[]{1, 2, 4, 3, 5};
        boolean[] results = botPokerEasy.makeMove(dice, otherDice);
		org.junit.Assert.assertTrue(!results[0] && !results[1] && !results[2] && !results[3] && !results[4]);
	} 
        @Test
    public void testPokerEasyFull(){
		
		int[] dice = new int[]{3, 6, 3, 6, 3};
        boolean[] results = botPokerEasy.makeMove(dice, otherDice);
		org.junit.Assert.assertTrue(!results[0] && !results[1] && !results[2] && !results[3] && !results[4]);
	}
        @Test
    public void testPokerEasyFour(){
		
		int[] dice = new int[]{3, 6, 6, 6, 6};
        boolean[] results = botPokerEasy.makeMove(dice, otherDice);
		org.junit.Assert.assertTrue(results[0] && !results[1] && !results[2] && !results[3] && !results[4]);
	}
        @Test
    public void testPokerEasyPoker(){
				
		int[] dice = new int[]{5, 5, 5, 5, 5};
        boolean[] results = botPokerEasy.makeMove(dice, otherDice);
		org.junit.Assert.assertTrue(!results[0] && !results[1] && !results[2] && !results[3] && !results[4]);
	}
    
    @Test
    public void testPokerHardNothing(){
		
		int[] dice = new int[]{5, 1, 3, 4, 6};
		otherDice.add(new int[]{1, 3, 2, 5, 4});
        boolean[] results = botPokerHard.makeMove(dice, otherDice);
		org.junit.Assert.assertTrue(!results[0] && results[1] && !results[2] && !results[3] && !results[4]);
		otherDice.removeAll(otherDice);
	}
    
    @Test
    public void testPokerHardPair(){
		
		int[] dice = new int[]{5, 2, 3, 4, 2};
		otherDice.add(new int[]{6, 1, 6, 6, 6});
        boolean[] results = botPokerHard.makeMove(dice, otherDice);
		org.junit.Assert.assertTrue(results[0] && !results[1] && results[2] && results[3] && !results[4]);
		otherDice.removeAll(otherDice);
	}
    
    @Test
    public void testPokerHardTwoPairs(){
		
		int[] dice = new int[]{5, 2, 3, 5, 2};
		otherDice.add(new int[]{3, 3, 4, 4, 3});
        boolean[] results = botPokerHard.makeMove(dice, otherDice);
		org.junit.Assert.assertTrue(!results[0] && !results[1] && results[2] && !results[3] && !results[4]);
		otherDice.removeAll(otherDice);
	}
    
    @Test
    public void testPokerHardThree(){
		
		int[] dice = new int[]{5, 2, 2, 4, 2};
		otherDice.add(new int[]{6, 1, 6, 6, 6});
        boolean[] results = botPokerHard.makeMove(dice, otherDice);
		org.junit.Assert.assertTrue(results[0] && !results[1] && !results[2] && results[3] && !results[4]);
		otherDice.removeAll(otherDice);
	}
    
    @Test
    public void testPokerHardStrit(){
				
		int[] dice = new int[]{1, 2, 3, 4, 5};
		otherDice.add(new int[]{2, 3, 4, 5, 6});
        boolean[] results = botPokerHard.makeMove(dice, otherDice);
		org.junit.Assert.assertTrue(results[0] && !results[1] && !results[2] && !results[3] && !results[4]);
		otherDice.removeAll(otherDice);
	}
    
    @Test
    public void testPokerHardFull(){
				
		int[] dice = new int[]{3, 3, 3, 2, 2};
		otherDice.add(new int[]{3, 3, 3, 1, 1});
		otherDice.add(new int[]{2, 2, 3, 3, 3});
        boolean[] results = botPokerHard.makeMove(dice, otherDice);
		org.junit.Assert.assertTrue(!results[0] && !results[1] && !results[2] && !results[3] && !results[4]);
		otherDice.removeAll(otherDice);
	}
    
    @Test
    public void testPokerHardFour(){
				
		int[] dice = new int[]{5, 5, 5, 5, 6};
		otherDice.add(new int[]{6, 6, 1, 6, 6});
        boolean[] results = botPokerHard.makeMove(dice, otherDice);
		org.junit.Assert.assertTrue(!results[0] && !results[1] && !results[2] && !results[3] && results[4]);
		otherDice.removeAll(otherDice);
	}
    
    @Test
    public void testPokerHardPoker(){
				
		int[] dice = new int[]{5, 5, 5, 5, 5};
		otherDice.add(new int[]{6, 6, 6, 6, 6});
        boolean[] results = botPokerHard.makeMove(dice, otherDice);
		org.junit.Assert.assertTrue(results[0] && results[1] && results[2] && results[3] && results[4]);
		otherDice.removeAll(otherDice);
	}
   
}