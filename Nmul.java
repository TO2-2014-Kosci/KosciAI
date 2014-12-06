public abstract class NMul extends NBot{

	@Override
	public int leftToScore(int[] dice, boolean[] option) {
		
		int left = 1;
		
		for(int i = 0; i < option.length; i++){
			if(!option[i]){
				left *= dice[i];
			}
		}
		return left;
	}


	//funkcja, ktora rekurencyjne zlicza wszystkie mozliwe rzuty, w zaleznosci od ilosc przerzucanych kosci,
	//i inkrementuje zmienna matches, jezeli dana opcja pasuje
	public void countMatches(int toThrow, int current, int target){
		if(toThrow == 0){
			if(current == target)
				matches++;
		}else{
			for (int j = 1; j < 7; j++){
				countMatches(toThrow - 1, current * j, target);
			}
		}
	}

}
