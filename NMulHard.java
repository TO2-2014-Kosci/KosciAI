import java.util.List;

class NMulHard extends NMul{


	public NMulHard(){
		allOptions = initList();
	}

	@Override
	public void chooseOption(int dice[], List<int[]> otherDice) throws Exception{
		
		this.dice = dice;
		
		int mulLeft;			//iloczyn oczek zostawionych
		double bestProb = 0;	//najlepsze prawdobodobienstwo
		boolean[] bestOption = new boolean[5];	//najlepsza opcja
		double prob;
		boolean[] option;
		int target;				//jaka sume trzeba trafic pozostalymi koscmi
		int dicesToThrow = 0;
		int allThrows;
		int currMatches;

		for(int i = 0; i < allOptions.size(); i++){  //petla po wszystkich opcjach
			
			//wybranie opcji, wyliczenie iloczynu pozostalych i ile trzeba wyrzucic
			option = allOptions.get(i);
			mulLeft = leftToScore(dice, option);
			
			if(score % mulLeft == 0){ //sprawdzam, czy przy takiej opcji jest w ogole mozliwe uzyskanie wyniku
				target = score / mulLeft; 
		
				//ile kostek do przerzucenia przy danej opcji
				dicesToThrow = 0;
				for(int j = 0; j < option.length; j++){
					if(option[j])
						dicesToThrow++;
				}
				
				//zapisanie do zmiennej traf ilosc trafien wyniku
				countMatches(dicesToThrow, 1, target);
				currMatches = matches;
				matches = 0;
				
				//wyliczenia ilosci wszystkich rzutow dla danej opcji
				allThrows = 1;
				for(int j = 0; j < dicesToThrow; j++){
					allThrows *= 6;
				}
				
				//obliczenie prawdopodobienstwa, i zapisanie go jesli jest najlepsze z opcja
				prob = (1.0 * currMatches) /allThrows;
				
				if(prob > bestProb){
					bestProb = prob;
					bestOption = option;
					setOption(bestOption);
				}
			}
		}
		
		//System.out.println("NMulHard: praw. = " +  Double.toString(bestProb));
		//optionToString(bestOption);
		
	}

	
	

	
	
	
}
