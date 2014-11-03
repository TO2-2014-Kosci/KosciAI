
public class TestKAI {
	public static void main(String[] args){
		TestKAI testkai = new TestKAI();
		testkai.test();
	}
	

	public void test(){
		BotFactory fabrykaBotow = new BotFactory();
		
		DiceBot botNPlusEasy = fabrykaBotow.createBot(0, 0);
		DiceBot botNPlusHard = fabrykaBotow.createBot(0, 1);
		DiceBot botNMulEasy = fabrykaBotow.createBot(1, 0);
		DiceBot botNMulHard = fabrykaBotow.createBot(1, 1);
		DiceBot botPokerEasy = fabrykaBotow.createBot(2, 1);
		
		int[] dice = new int[]{6, 6, 6, 6, 1};
		
		botNPlusHard.setScore(30);
		botNPlusHard.makeMove(dice);
		
		botNPlusEasy.setScore(30);
		botNPlusEasy.makeMove(dice);
	}
	/*
	public boolean testNplus(){
	
		boolean[] expectedOption = new boolean[]{false, false, false, true, true};
		boolean[] option;
		int plusScore = 30;
		byte[] plusDice = new byte[]{6, 6, 6, 1, 1};
		NPlus nplus = new NPLus(plusScore, plusDice);
		option = nplus.play();
		
		nplus.optionToString(expectedOption);
		nplus.optionToString(option);
		
	
		for(int i=0; i<option.length; i++){
			if(option[i] != expectedOption[i]) return false;
		}
		
		return true;
		
	}
	
	public boolean testNmul(){
		
		boolean[] expectedOption = new boolean[]{false, false, false, true, true};
		boolean[] option;
		int mulScore = 6*6*6*6*6;
		byte[] mulDice = new byte[]{6, 6, 6, 1, 1};
		Nmul nmul = new Nmul(mulScore, mulDice);
		option = nmul.play();
		
	
		for(int i=0; i<option.length; i++){
			if(option[i] != expectedOption[i]) return false;
		}
		
		return true;
		
	}
	
	public boolean testPokerPoker(){
		boolean[] expectedOption = new boolean[]{false, false, false, false, false};
		boolean[] option;
		byte[] pokerDice = new byte[]{6, 6, 6, 6, 6};
		Poker poker = new Poker(pokerDice);
		option = poker.play();
		
	
		for(int i=0; i<option.length; i++){
			if(option[i] != expectedOption[i]) return false;
		}
		
		return true;
		
	}
	
	public boolean testPokerFull(){
		boolean[] expectedOption = new boolean[]{false, false, false, false, false};
		boolean[] option;
		byte[] pokerDice = new byte[]{6, 6, 6, 5, 5};
		Poker poker = new Poker(pokerDice);
		option = poker.play();
		
	
		for(int i=0; i<option.length; i++){
			if(option[i] != expectedOption[i]) return false;
		}
		
		return true;
		
	}
	
	public boolean testPokerStrit(){
		boolean[] expectedOption = new boolean[]{false, false, false, false, false};
		boolean[] option;
		byte[] pokerDice = new byte[]{2, 3, 4, 5, 6};
		Poker poker = new Poker(pokerDice);
		option = poker.play();
		
	
		for(int i=0; i<option.length; i++){
			if(option[i] != expectedOption[i]) return false;
		}
		
		return true;
		
	}
	
	public boolean testPokerFour(){
		boolean[] expectedOption = new boolean[]{false, false, true, false, false};
		boolean[] option;
		byte[] pokerDice = new byte[]{2, 2, 4, 2, 2};
		Poker poker = new Poker(pokerDice);
		option = poker.play();
		
	
		for(int i=0; i<option.length; i++){
			if(option[i] != expectedOption[i]) return false;
		}
		
		return true;
		
	}
	
	public boolean testPokerTwoPairs(){
		boolean[] expectedOption = new boolean[]{false, false, true, false, false};
		boolean[] option;
		byte[] pokerDice = new byte[]{2, 3, 4, 2, 3};
		Poker poker = new Poker(pokerDice);
		option = poker.play();
		
	
		for(int i=0; i<option.length; i++){
			if(option[i] != expectedOption[i]) return false;
		}
		
		return true;
		
	}
	
	public boolean testPokerThree(){
		boolean[] expectedOption = new boolean[]{true, false, false, false, true};
		boolean[] option;
		byte[] pokerDice = new byte[]{2, 3, 3, 3, 6};
		Poker poker = new Poker(pokerDice);
		option = poker.play();
		
	
		for(int i=0; i<option.length; i++){
			if(option[i] != expectedOption[i]) return false;
		}
		
		return true;
		
	}
	
	public boolean testPokerPair(){
		boolean[] expectedOption = new boolean[]{false, true, false, true, true};
		boolean[] option;
		byte[] pokerDice = new byte[]{6, 3, 6, 5, 1};
		Poker poker = new Poker(pokerDice);
		option = poker.play();
		
	
		for(int i=0; i<option.length; i++){
			if(option[i] != expectedOption[i]) return false;
		}
		
		return true;
		
	}
	
	public boolean testPokerNothing(){
		boolean[] expectedOption = new boolean[]{false, true, false, false, false};
		boolean[] option;
		byte[] pokerDice = new byte[]{2, 1, 3, 5, 6};
		Poker poker = new Poker(pokerDice);
		option = poker.play();
		
	
		for(int i=0; i<option.length; i++){
			if(option[i] != expectedOption[i]) return false;
		}
		
		return true;
		
	}
	*/
	
}
