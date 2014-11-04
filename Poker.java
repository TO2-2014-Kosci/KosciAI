import java.util.Map;

public abstract class Poker extends DiceBot{

	protected Map<Integer, Integer> countDice;
	public EnumFigure.Figure figure; //aktualna figura

	public EnumFigure.Figure checkFigure(int[] dice){
	
		//zerowanie mapy oczko: powtorzenie
		for(int i = 1; i < 7; i++){
			countDice.put(i, 0);
		}
		
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
		else if((countDice.get(2) == 1) && (countDice.get(3) == 1) && (countDice.get(4) == 1) && (countDice.get(5) == 1)){
			figure = EnumFigure.Figure.STRIT;
		}else{
			int repeat = 0; 
			for(int i = 1; i < 7; i++){
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
	

	public static class EnumFigure{
		public enum Figure{
			NOTHING, PAIR, TWO_PAIRS, THREE, STRIT, FULL, FOUR, POKER
		}
	}
}
