
public abstract class NMul extends DiceBot{
	
	
	
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
	
	
}
