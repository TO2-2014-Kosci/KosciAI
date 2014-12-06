import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public abstract class Bot {
	
	//opcja przezutu koscmi
	protected boolean[] option = new boolean[5];
	//kosci bota
	protected int[] dice = null;
	//kosci innych graczy
	protected List<int[]> otherDice = null;
	//czas na wykonanie ruchu, dla klasy wewnetrznej Time
	private int time;
	//cel, do ktorego dazymy
	protected int score;
	
    protected boolean[] getOption(){
    	return option;
    }

    
	
    protected abstract void chooseOption(int dice[], List<int[]> otherDice) throws Exception;

	
	protected void setOption(boolean[] option){
		this.option = option;
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	protected void setTime(int time){
		this.time = time;
	}

	
	//opcja do stringa
	protected void optionToString(boolean[] option){
		for(int i = 0; i < option.length; i++){
			if(option[i])
				System.out.print(1);
			else
				System.out.print(0);
		}
		System.out.println();
		System.out.println();
	}
	
	/** 
     * 
     * @param dice array of 5 integers representing set of dice
     * @param otherDice list of int[5] arrays representing dice sets of other players
     * @return array of 5 booleans, true if dice should be rethrown, false otherwise
     */
	public boolean[] makeMove() throws Exception{
		ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<boolean[]> future = executor.submit(new Move(dice, otherDice));

        try {
            System.out.println("Started..");
            future.get(time, TimeUnit.SECONDS);
            System.out.println("Finished!");
        } catch (TimeoutException e) {
            System.out.println("Terminated!");
        }

        executor.shutdownNow();
        return option;
    }
	
	//KLASY WEWNETRZNE
	
    
    class Move implements Callable<boolean[]>{

        int[] dice;
        List<int[]> otherDice;
        
        public Move(int[] dice, List<int[]> otherDice){
            this.dice = dice;
            this.otherDice = otherDice;
        }
        
        @Override
        public boolean[] call() throws Exception {
            chooseOption(dice, otherDice);
            return getOption();
        }
      
    }
}
