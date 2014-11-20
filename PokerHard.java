import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






public class PokerHard extends Poker{

	private boolean[] option = null;
	
	
	public PokerHard(){
		option = new boolean[5];
	}
	
	private int[] choseBestDice(List<int[]> otherDice){
		int[] bestDice = otherDice.get(0);
		int[] curDice = null;
		Figure bestFigure = checkFigure(bestDice);
		Figure curFigure = null;
		Map<Integer, Integer> curMap = new HashMap<Integer, Integer>();  //mapy oczko:powtorzenie, 
		Map<Integer, Integer> bestMap = new HashMap<Integer, Integer>();  //potrzebne przy tych samych figurach
		
		
		int curToCompare = 0;   //oczko na kosci do porownania
		int bestToCompare = 0;
		
				
		for(int i = 1; i < otherDice.size(); i++){
			curDice = otherDice.get(i);
			curFigure = checkFigure(curDice);
			if(curFigure.ordinal() > bestFigure.ordinal()){
				bestFigure = curFigure;
				bestDice = curDice;
			}else if(curFigure.ordinal() == bestFigure.ordinal()){
				
				//uzupelnianie map
				curMap = fillMap(curDice);
				bestMap = fillMap(bestDice);
				
				//sprawdzam, ktora figura jest wyzsza, schodzac w dol mapy oczek
				boolean curCheck = false;
				boolean bestCheck = false;
				
				switch(curFigure){
					
					case POKER:
						for(int j = 6; j >=1; j--){
							if(curMap.get(j) == 5)
								curCheck = true;
							if(bestMap.get(j) == 5)
								bestCheck = true;
							if(curCheck && !bestCheck){
								bestDice = curDice;
								break;
							}
						}
						break;
						
					case FOUR:	
						for(int j = 1; j <= 6; j++){
							if(curMap.get(j) == 4)
								curToCompare = j;
							if(bestMap.get(j) == 4)
								bestToCompare = j;
						}
						if(curToCompare > bestToCompare)
							bestDice = curDice;
						
						//jezeli 4 jest z tych samych, sprawdzam ostatnia
						else if(curToCompare == bestToCompare){
							for(int j = 6; j >= 1; j--){
								if(curMap.get(j) == 1)
									curCheck = true;
								if(bestMap.get(j) == 1)
									bestCheck = true;
								if(curCheck && !bestCheck){
									bestDice = curDice;
									break;
								}
							}			
						}
						break;
						
					case FULL:
						for(int j = 1; j <= 6; j++){
							if(curMap.get(j) == 3)
								curToCompare = j;
							if(bestMap.get(j) == 3)
								bestToCompare = j;
						}
						if(curToCompare > bestToCompare)
							bestDice = curDice;
						//jezeli 3 takie same, sprawdzam 2
						else if(curToCompare == bestToCompare){
							for(int j = 6; j >= 1; j--){
								if(curMap.get(j) == 2)
									curCheck = true;
								if(bestMap.get(j) == 2)
									bestCheck = true;
								if(curCheck && !bestCheck){
									bestDice = curDice;
									break;
								}
							}			
						}
						break;
						
					case STRIT:
						if(curMap.get(6) == 1 && bestMap.get(1) == 1)
							bestDice = curDice;
						break;
						
					case THREE:
						for(int j = 1; j <= 6; j++){
							if(curMap.get(j) == 3)
								curToCompare = j;
							if(bestMap.get(j) == 3)
								bestToCompare = j;
						}
						if(curToCompare > bestToCompare)
							bestDice = curDice;
						//jezeli 3 jest z tych samych, sprawdzam od konca mape
						else if(curToCompare == bestToCompare){
							for(int j = 6; j >= 1; j--){
								if(curMap.get(j) == 1)
									curCheck = true;
								if(bestMap.get(j) == 1)
									bestCheck = true;
								//to samo, schodze nizej
								if(curCheck && bestCheck){									
									curCheck = false;
									bestCheck = false;
								}else if(curCheck && !bestCheck){
									bestDice = curDice;
									break;
								}else if(!curCheck && bestCheck){
									break;
								}
							}			
						}
						break;
			
					
					//dla dwoch par, schodze w dol, porownujac pary
					case TWO_PAIRS:
						int samePairs = 0;//sprawdzam, ile razy powtorzyly sie pary 
						for(int j = 6; j >= 1; j--){
							if(curMap.get(j) == 2){
								curCheck = true;
							}
							if(bestMap.get(j) == 2)
								bestCheck = true;
							if(curCheck && bestCheck){									
								curCheck = false;
								bestCheck = false;
								samePairs++;
							}else if(curCheck && !bestCheck){
								bestDice = curDice;
								break;
							}else if(!curCheck && bestCheck){
								break;
							}
							
						}

						if(samePairs == 2){
							for(int j = 6; j >= 1; j--){
								if(curMap.get(j) == 1)
									curCheck = true;
								if(bestMap.get(j) == 1)
									bestCheck = true;
								if(curCheck && !bestCheck){
									bestDice = curDice;
									break;
								}
							}
						}
						break;
					case PAIR:
						boolean samePair = false;
						for(int j = 6; j >= 1; j--){
							if(curMap.get(j) == 2)
								curToCompare = j;
							if(bestMap.get(j) == 2)
								bestToCompare = j;					
						}
						if(curToCompare > bestToCompare){
							bestDice = curDice;
						}else if(curToCompare == bestToCompare){
							for(int j = 6; j >= 1; j--){
								if(curMap.get(j) == 1)
									curCheck = true;
								if(bestMap.get(j) == 1)
									bestCheck = true;
								if(curCheck && bestCheck){									
									curCheck = false;
									bestCheck = false;
								}else if(curCheck && !bestCheck){
									bestDice = curDice;
									break;
								}else if(!curCheck && bestCheck){
									break;
								}
							}
						}
						break;
					case NOTHING:
						for(int j = 6; j >= 1; j--){
							if(curMap.get(j) == 1)
								curCheck = true;
							if(bestMap.get(j) == 1)
								bestCheck = true;
							if(curCheck && bestCheck){									
								curCheck = false;
								bestCheck = false;
							}else if(curCheck && !bestCheck){
								bestDice = curDice;
								break;
							}else if(!curCheck && bestCheck){
								break;
							}
						}
					break;
				}
			}
			
		}
		
		
		return bestDice;
	}
	
	@Override
	public boolean[] chooseOption(int[] dice, List<int[]> otherDice) {
		int[] bestDice;
		
		this.dice = dice;
		this.otherDice = otherDice;
		
		bestDice = choseBestDice(otherDice);
		
		
		
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
				
			/*Metoda zwraca tablicê, w której wartoœci odpowiadaj¹ce koœciom z pary 
			  maj¹ wartoœæ FALSE, a pozosta³ym TRUE, chyba ¿e któryœ z przeciwników 
			  ma pokera z cyfr wy¿szych ni¿ nasza para, wtedy wszystkie wartoœci s¹ TRUE*/
			case PAIR:
				if(checkFigure(bestDice) == Figure.POKER){
					
				}
				
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
