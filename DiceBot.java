import java.util.ArrayList;


public abstract class DiceBot {
	
	public void setScore(int score){}
	
	//lista wszystkich opcji
	protected ArrayList<boolean[]> options = null;
	
	//kosci bota
	protected int[] dice = null;
	
	//kosci innych graczy
	protected ArrayList<int[]> otherDice = null;
	
	//czas na wykonanie ruchu
	protected int moveTime;
	
	//wykonanie ruchu, w argumencie dostaje aktualne kosci
	public abstract boolean[] makeMove(int[] dice, ArrayList<int[]> otherDice);
	
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
