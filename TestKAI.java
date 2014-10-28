
public class TestKAI {
	public static void main(String[] args){
		TestKAI testkai = new TestKAI();
		testkai.test();
	}
	
	public boolean testNplus(){
	
		boolean[] expectedOption = new boolean[]{false, false, false, true, true};
		boolean[] option;
		int plusScore = 30;
		byte[] plusDice = new byte[]{6, 6, 6, 1, 1};
		Nplus nplus = new Nplus(plusScore, plusDice);
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
	
	public void test(){
		
		/*
		
		int plusScore = 10;
		byte[] plusDice = new byte[]{1, 2, 3, 4, 5};
		Nplus nplus = new Nplus(plusScore, plusDice);
		nplus.play();
		
		
		int mulScore = 30;
		byte[] mulDice = new byte[]{3, 5, 6, 4, 4};
		Nmul nmul = new Nmul(mulScore, mulDice);
		nmul.play();
		
		
		byte[] pokerDice = new byte[]{4, 5, 6, 2, 1};
		Poker poker = new Poker(pokerDice);
		poker.play();
		*/
		
		System.out.println(testNplus());
		System.out.println(testNmul());
		System.out.println(testPokerPoker());
		System.out.println(testPokerFull());
		System.out.println(testPokerStrit());
		System.out.println(testPokerFour());
		System.out.println(testPokerThree());
		System.out.println(testPokerTwoPairs());
		System.out.println(testPokerPair());
		System.out.println(testPokerNothing());
	}
}
