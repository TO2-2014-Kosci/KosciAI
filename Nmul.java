import java.util.Comparator;
import java.util.Map;


public abstract class NMul extends DiceBot{
	
	//wynik, potrzebny do odmian NPlus, NMul
	protected int score;
	
	
	public void setScore(int score){
		this.score = score;
	}
	
	//suma pozostalych kosci
	public int mulLeft(int[] dice, boolean[] option){
		int mulLeft = 1;
		for(int i = 0; i < option.length; i++){
			if(!option[i]){
				mulLeft *= dice[i];
			}
		}
		return mulLeft;
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
