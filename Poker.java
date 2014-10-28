import java.util.HashMap;
import java.util.Map;



public class Poker extends DiceGame{

	
	private Map<Byte, Integer> countDice;
	private boolean[] option = null;
	public EnumFigure.Figure figure; //aktualna figura
	
	public Poker(byte[] dice){
		this.dice = dice;
		option = new boolean[5];
		countDice = new HashMap<Byte, Integer>(){
			private static final long serialVersionUID = 1L;
		{
	        put((byte)1,0);put((byte)2,0);put((byte)3,0);put((byte)4,0);put((byte)5,0);put((byte)6,0);
	    }};
	}
	
	
	public EnumFigure.Figure checkFigure(byte[] dice){
		
		
		for(int i = 0; i < dice.length; i++){
			countDice.put(dice[i], countDice.get(dice[i]) + 1);
		}
		
		System.out.println(countDice);
		
		
		if(countDice.containsValue(5))
			figure = EnumFigure.Figure.POKER;
		else if(countDice.containsValue(4))
			figure = EnumFigure.Figure.FOUR;
		else if(countDice.containsValue(3)){
			if(countDice.containsValue(2))
				figure = EnumFigure.Figure.FULL;
			else 
				figure = EnumFigure.Figure.THREE;
		}
		else if((countDice.get((byte)2) == 1) && (countDice.get((byte)3) == 1) && (countDice.get((byte)4) == 1) && (countDice.get((byte)5) == 1)){
			figure = EnumFigure.Figure.STRIT;
		}else{
			int repeat = 0; 
			for(byte i = 1; i < 7; i++){
				if (countDice.get(i) == 2)
					repeat++;
			}
			if(repeat == 2){
				figure = EnumFigure.Figure.TWO_PAIRS;
			}else if(repeat == 1){
				figure = EnumFigure.Figure.PAIR;
			}else
				figure = EnumFigure.Figure.NOTHING;
		}
		
		return figure;
	}
	

	@Override
	public boolean[] play() {
		
		
	    
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

	
	
	public static class EnumFigure{
		public enum Figure{
			NOTHING, PAIR, TWO_PAIRS, THREE, STRIT, FULL, FOUR, POKER
		}
	}

}
