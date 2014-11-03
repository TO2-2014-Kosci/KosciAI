import java.util.ArrayList;


public abstract class DiceBot {
	
	//lista wszystkich opcji
	protected ArrayList<boolean[]> options = null;
	
	//kosci bota
	protected int[] dice = null;
	
	//wynik, potrzebny do odmian NPlus, NMul
	protected int score;
	
	//wykonanie ruchu, w argumencie dostaje aktualne kosci
	public abstract boolean[] makeMove(int[] dice);
	
	public void setScore(int score){
		this.score = score;
	}
	
	//inicjacja listy wszystkich opcji
	public ArrayList<boolean[]> initList(){
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
	
	//opcja do stringa
	public void optionToString(boolean[] option){
		for(int i = 0; i < option.length; i++){
			if(option[i])
				System.out.print(1);
			else
				System.out.print(0);
		}
		System.out.println();
		return;
	}
}
