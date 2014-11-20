import java.util.List;



public class PokerEasy extends Poker{

	private boolean[] option = null;
	
	
	public PokerEasy(){
		option = new boolean[5];
	}
	
	
	@Override
	public boolean[] chooseOption(int[] dice, List<int[]> otherDice) {
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
