import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

abstract class NBot extends Bot{
	
	
	/**suma pozostalych kosci
         * 
         * @param dice
         * @param option
         * @return 
         */
	
	protected int matches = 0; //ilosc trafien w danym wyborze
	//lista wszystkich opcji, dla NPlus i NMul
	protected List<boolean[]> allOptions = null;
		
	abstract public int leftToScore(int[] dice, boolean[] option);
	abstract public void countMatches(int toThrow, int current, int target);
	
	//inicjacja listy wszystkich opcji
	protected ArrayList<boolean[]> initList(){
		ArrayList<boolean[]> tab = new ArrayList<boolean[]>();
		for(int liczba = 0; liczba < 32; liczba++){
			boolean[] option = new boolean[5];
			//zapisanie jednej z mozliwosci
			for(int i = 0; i < option.length; i++){
				option[4 - i] = (liczba >> i & 1) != 0;
			}
			tab.add(option);
		}
		return tab;
	}
		
	//klasa potrzebna do sortowania opcji wyboru po prawdopodobienstwie
	class ValueComparator implements Comparator<boolean[]> {

	    Map<boolean[], Double> base;
	    public ValueComparator(Map<boolean[], Double> base) {
	        this.base = base;
	    }
	    public int compare(boolean[] a, boolean[] b) {
	        if (base.get(a) >= base.get(b)) {
	            return -1;
	        } else {
	            return 1;
	        }
	    }
	}
}
