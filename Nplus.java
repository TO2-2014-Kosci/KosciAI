import java.util.Comparator;
import java.util.Map;


public abstract class NPlus extends DiceBot{
	

	//wynik, potrzebny do odmian NPlus, NMul
	protected int score;
	
	
	public void setScore(int score){
		this.score = score;
	}
	
	//suma pozostalych kosci
	public int sumLeft(int[] dice, boolean[] option){
		int sumLeft = 0;
		for(int i = 0; i < option.length; i++){
			if(!option[i]){
				sumLeft += dice[i];
			}
		}
		return sumLeft;
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
