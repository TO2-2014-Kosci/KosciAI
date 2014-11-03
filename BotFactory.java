
//Klasa od tworzenia botow
public class BotFactory {
	//zwraca bota do danej gry, w konstruktorze: typ, poziom
	public DiceBot createBot(int type, int level){
		DiceBot bot = null;
		
		if(type == 0 && level == 0){
			bot = new NPlusEasy();
		}else if(type == 0 && level == 1){
			bot = new NPlusHard();
		}else if(type == 1 && level == 0){
			bot = new NMulEasy();
		}else if(type == 1 && level == 1){
			bot = new NMulHard();
		}else if(type == 2 && level == 0){
			bot = new PokerEasy();
		}else if(type == 2 && level == 1){
			bot = new PokerHard();
		}
			
		return bot;
	}
}
