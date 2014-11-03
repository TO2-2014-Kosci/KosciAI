import java.util.ArrayList;



public class NMulEasy extends NMul{


	private static int matches = 0; //ilosc trafien w danym wyborze


	public NMulEasy(){
		options = initList();
	}

	@Override
	public boolean[] makeMove(int dice[]) {
		this.dice = dice;
		
		int mulLeft;			//iloczyn oczek zostawionych
		double bestProb = 0;	//najlepsze prawdobodobienstwo
		boolean[] bestOption = new boolean[5];	//najlepsza opcja
		double prob;
		boolean[] option;
		int target;				//jaka sume trzeba trafic pozostalymi koscmi
		int dicesToThrow = 0;
		int allThrows;
		int currTraf;
		
	
		for(int i = 0; i < options.size(); i++){  //petla po wszystkich opcjach
			
			//wybranie opcji, wyliczenie iloczynu pozostalych i ile trzeba wyrzucic
			option = options.get(i);
			mulLeft = mulLeft(dice, option);
			
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
				currTraf = NMulEasy.matches;
				NMulEasy.matches = 0;
				
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
				}
			}
		}
		
		System.out.println("Najlepsze prawdopodobienstwo: " +  Double.toString(bestProb));
		System.out.println("Przy opcji: ");
		optionToString(bestOption);
		
		return bestOption;
	}

	
	

	
	
	//funkcja, ktora rekurencyjne zlicza wszystkie mozliwe rzuty, w zaleznosci od ilosc przerzucanych kosci,
	//i inkrementuje zmienna matches, jezeli dana opcja pasuje
	public static void countMatches(int _throws, int iloczyn, int target){
		if(_throws == 0){
			if(iloczyn == target)
				NMulEasy.matches++;
		}else{
			for (int j = 1; j < 7; j++){
				countMatches(_throws - 1, iloczyn * j, target);
			}
		}
	}
	
}
