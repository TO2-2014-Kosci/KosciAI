import java.util.List;



class NPlusHard extends NPlus{

	public NPlusHard(){
		allOptions = initList();
	}
	

	@Override
	public void chooseOption(int dice[], List<int[]> otherDice) throws Exception{
		
		
		Thread.sleep(100);
		
		this.dice = dice;
		int sumLeft;			//suma oczek zostawionych
		double bestProb = 0;	//najlepsze prawdobodobienstwo
		boolean[] bestOption = new boolean[5];	//najlepsza opcja
		double prob;
		boolean[] option;
		int target;				//jaka sume trzeba trafic pozostalymi koscmi
		int dicesToThrow = 0;
		int allThrows;
		int currTraf;
		
	
		for(int i = 0; i < allOptions.size(); i++){  //petla po wszystkich opcjach
			
			//wybranie opcji, wyliczenie sumy pozostalych i ile trzeba wyrzucic
			option = allOptions.get(i);
			sumLeft = leftToScore(dice, option);
			target = score - sumLeft; 
	
			//ile kostek do przerzucenia przy danej opcji
			dicesToThrow = 0;
			for(int j = 0; j < option.length; j++){
				if(option[j])
					dicesToThrow++;
			}
			
			//zapisanie do zmiennej traf ilosc trafien wyniku
			countMatches(dicesToThrow, 0, target);
			currTraf = matches;
			matches = 0;
			
			//wyliczenia ilosci wszystkich rzutow dla danej opcji
			allThrows = 1;
			for(int j = 0; j < dicesToThrow; j++){
				allThrows *= 6;
			}
			
			//obliczenie prawdopodobienstwa, i zapisanie go jesli jest najlepsze z opcja
			prob = (1.0 * currTraf) /allThrows;
			
			if(prob > bestProb){
				bestProb = prob;
				bestOption = option;
				setOption(bestOption);
			}
		}
		
		//System.out.println("NPlusHard: praw. = " +  Double.toString(bestProb));
		//optionToString(bestOption);
		
	}

	
	
	
	
}
