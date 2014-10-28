import java.util.ArrayList;



public class Nplus extends DiceGame{

	private static int matches = 0; //ilosc trafien w danym wyborze
	private int score;			//oczekiwany wynik
	private ArrayList<boolean[]> options = null;
	
	
	public Nplus(int score, byte[] dice){
		this.score = score;
		this.dice = dice;
		options = initList();
	}
	

	@Override
	public boolean[] play() {
		
		int sumLeft;			//suma oczek zostawionych
		double bestProb = 0;	//najlepsze prawdobodobienstwo
		boolean[] bestOption = new boolean[5];	//najlepsza opcja
		double prob;
		boolean[] option;
		int target;				//jaka sume trzeba trafic pozostalymi koscmi
		int dicesToThrow = 0;
		int allThrows;
		int currTraf;
		
		
		for(int i = 0; i < options.size(); i++){  //petla po wszystkich opcjach
			
			//wybranie opcji, wyliczenie sumy pozostalych i ile trzeba wyrzucic
			option = options.get(i);
			sumLeft = sumLeft(dice, option);
			target = score - sumLeft; 
	
			//ile kostek do przerzucenia przy danej opcji
			dicesToThrow = 0;
			for(int j = 0; j < option.length; j++){
				if(option[j])
					dicesToThrow++;
			}
			
			//zapisanie do zmiennej traf ilosc trafien wyniku
			countMatches(dicesToThrow, 0, target);
			currTraf = Nplus.matches;
			Nplus.matches = 0;
			
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
		
		System.out.println("Najlepsze prawdopodobienstwo: " +  Double.toString(bestProb));
		System.out.println("Przy opcji: ");
		optionToString(bestOption);
		
		return bestOption;
	}

	
	
	//suma pozostalych kosci
	public int sumLeft(byte[] dice, boolean[] option){
		int sumLeft = 0;
		for(int i = 0; i < option.length; i++){
			if(!option[i]){
				sumLeft += dice[i];
			}
		}
		return sumLeft;
	}
	
	//funkcja, ktora rekurencyjne zlicza wszystkie mozliwe rzuty, w zaleznosci od ilosc przerzucanych kosci,
	//i inkrementuje zmienna matches, jezeli dana opcja pasuje
	public static void countMatches(int _throws, int sum, int target){
		if(_throws == 0){
			if(sum == target)
				Nplus.matches++;
		}else{
			for (int j = 1; j < 7; j++){
				countMatches(_throws - 1, sum + j, target);
			}
		}
	}
	
}
