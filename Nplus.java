
public abstract class NPlus extends DiceBot{
	
	
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
		
}
