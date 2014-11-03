import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;



public class NPlusEasy extends NPlus{

	private static int matches = 0;
	
	public NPlusEasy(){
		options = initList();
	}
	

	@Override
	public boolean[] makeMove(int dice[]) {
		
		HashMap<boolean[],Double> map = new HashMap<boolean[],Double>();
        ValueComparator bvc =  new ValueComparator(map);
        TreeMap<boolean[],Double> sorted_map = new TreeMap<boolean[],Double>(bvc);
		
		this.dice = dice;
		int sumLeft;			//suma oczek zostawionych
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
			currTraf = NPlusEasy.matches;
			NPlusEasy.matches = 0;
			
			//wyliczenia ilosci wszystkich rzutow dla danej opcji
			allThrows = 1;
			for(int j = 0; j < dicesToThrow; j++){
				allThrows *= 6;
			}
			
			//obliczenie prawdopodobienstwa, i dodanie go do mapy
			prob = (1.0 * currTraf) /allThrows;
			
			map.put(options.get(i), prob);
		}
		
		//posortowanie mapy boolean[]:int
		sorted_map.putAll(map);
		
		Random generator = new Random();
		int chose = generator.nextInt(5);
		Iterator iterator = sorted_map.keySet().iterator();
		
		for(int j = 0; j < chose; j++){
			iterator.next();
		}
		option = (boolean[])iterator.next();
		
		System.out.println("Wylosowane prawdopodobienstwo" + map.get(option));
		System.out.println("Przy opcji: ");
		optionToString(option);
		
		return option;
	}

	//funkcja, ktora rekurencyjne zlicza wszystkie mozliwe rzuty, w zaleznosci od ilosc przerzucanych kosci,
		//i inkrementuje zmienna matches, jezeli dana opcja pasuje
		public static void countMatches(int _throws, int sum, int target){
			if(_throws == 0){
				if(sum == target)
					NPlusEasy.matches++;
			}else{
				for (int j = 1; j < 7; j++){
					countMatches(_throws - 1, sum + j, target);
				}
			}
		}
		
		class ValueComparator implements Comparator<boolean[]> {

		    Map<boolean[], Double> base;
		    public ValueComparator(Map<boolean[], Double> base) {
		        this.base = base;
		    }

		    // Note: this comparator imposes orderings that are inconsistent with equals.    
		    public int compare(boolean[] a, boolean[] b) {
		        if (base.get(a) >= base.get(b)) {
		            return -1;
		        } else {
		            return 1;
		        } // returning 0 would merge keys
		    }
		}
	
	
	
}
