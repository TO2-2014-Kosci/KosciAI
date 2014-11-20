import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;



public class NMulEasy extends NMul{


	private static int matches = 0; //ilosc trafien w danym wyborze


	public NMulEasy(){
		options = initList();
	}

	@Override
	public boolean[] chooseOption(int dice[], List<int[]> otherDice) {
		this.dice = dice;
		
		HashMap<boolean[],Double> map = new HashMap<boolean[],Double>();
        ValueComparator bvc =  new ValueComparator(map);
        TreeMap<boolean[],Double> sorted_map = new TreeMap<boolean[],Double>(bvc);
		
		int mulLeft;			//iloczyn oczek zostawionych
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
			
			map.put(options.get(i), prob);
		
		}
		
		//posortowanie mapy boolean[]:int
		sorted_map.putAll(map);
		
		Random generator = new Random();
		int index = generator.nextInt(1);
		Iterator<boolean[]> iterator = sorted_map.keySet().iterator();
		
		for(int j = 0; j < index && iterator.hasNext(); j++){
			iterator.next();
		}
		option = (boolean[])iterator.next();
		
		System.out.println("NMulEasy: praw. = " + map.get(option));
		optionToString(option);
		
		return option;
		
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
