
public class TestKAI {
	public static void main(String[] args){
		TestKAI testkai = new TestKAI();
		testkai.test();
	}
	
	public void test(){
		
		
		
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
	}
}
