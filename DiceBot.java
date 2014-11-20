import java.util.ArrayList;
import java.util.List;


public abstract class DiceBot {
	
	//opcja przezutu koscmi
	protected boolean[] option = new boolean[5];
	//lista wszystkich opcji, dla NPlus i NMul
	protected ArrayList<boolean[]> options = null;
	//kosci bota
	protected int[] dice = null;
	//kosci innych graczy
	protected List<int[]> otherDice = null;
	//czas na wykonanie ruchu, dla klasy wewnetrznej Time
	private int time;
	
	
    //wykonanie ruchu, w argumencie dostaje aktualne kosci
	public boolean[] makeMove(int[] dice, List<int[]> otherDice){
		//tworze dwie klasy, jedna jest odpowiedzialna za wybranie opcji, a druga pilnuje czasu
        Time tm = new Time();
        Move mv = new Move(dice, otherDice);
        tm.start();
        mv.start();
        //jezeli zostala wybrana opcja, przerywamy odliczanie czasu
        //jezeli czas dobiegl konca, konczymy wybieranie opcji
        while (mv.isAlive() && tm.isAlive()){}
        if(mv.isAlive()) mv.interrupt();
        if(tm.isAlive()) tm.interrupt();
        return option;
    }
	
    protected abstract boolean[] chooseOption(int dice[], List<int[]> otherDice);

	
	protected void setOption(boolean[] option){
		this.option = option;
	}
	
	public void setScore(int score){}
	
	public void setTime(int time){
		this.time = time;
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
		System.out.println();
		return;
	}
	
	//KLASY WEWNETRZNE
	class Time extends Thread{
		
        @Override
        public synchronized void run(){
    
            long start = System.currentTimeMillis();
            long timediff = System.currentTimeMillis() - start;
            while(timediff < time * 1000 - 200 && !Thread.currentThread().isInterrupted()){
                timediff = System.currentTimeMillis() - start;
            }                
        }
    }
    
    class Move extends Thread{

        int[] dice;
        List<int[]> otherDice;
        public Move(int[] dice, List<int[]> otherDice){
            this.dice = dice;
            this.otherDice = otherDice;
        }
        @Override
        public synchronized void run (){
         
            setOption(chooseOption(dice, otherDice)); 
        }
    }
}
