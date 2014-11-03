import java.util.HashMap;
import java.util.Map;



public class PokerEasy extends Poker{

	private boolean[] option = null;
	
	
	public PokerEasy(){
		
		option = new boolean[5];
		countDice = new HashMap<Integer, Integer>(){
		{
	        put(1,0);put(2,0);put(3,0);put(4,0);put(5,0);put(6,0);
	    }};
	}
	
	
	@Override
	public boolean[] makeMove(int[] dice) {
		this.dice = dice;
		
	    
		figure = checkFigure(dice);
		
		for(int i = 0; i < option.length; i++)
			option[i] = false;
		
		System.out.println(figure);
		switch(figure){
			//przy pokerze, streecie, fullu nic nie przerzucaj
			case POKER:
			case FULL:
			case STRIT:
				break;
			//przy czworce, trojce, 2x2, parze, rzuc tymi, ktore pojawiaja sie raz
			case FOUR:
			case THREE:
			case TWO_PAIRS:
			case PAIR:
				for(int i = 0; i < dice.length; i++){
					if(countDice.get(dice[i]) == 1){
						option[i] = true;
					}
				}
				break;
			//jak nic nie ma, przezuc 1 w nadziei na duzego streeta
			case NOTHING:
				for(int i = 0; i < dice.length; i++){
					if(dice[i] == 1){
						option[i] = true;
					}
				}
				break;
			default:
				break;
		}
		
				
		optionToString(option);
		return option;
	}

	
	

}
