import java.util.ArrayList;


public abstract class DiceGame {
	protected byte[] dice = null;
	
	public abstract boolean[] play();
	
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
