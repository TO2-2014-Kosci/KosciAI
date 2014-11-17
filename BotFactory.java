
//Klasa od tworzenia botow
public class BotFactory {
	//zwraca bota do danej gry, w konstruktorze: typ, poziom
	public DiceBot createBot(GameType type, int level, int moveTime){
		DiceBot bot = null;
		
		if(type == GameType.NPLUS && level == 0){
			bot = new NPlusEasy(moveTime);
		}else if(type == GameType.NPLUS && level == 1){
			bot = new NPlusHard(moveTime);
		}else if(type == GameType.NMUL && level == 0){
			bot = new NMulEasy(moveTime);
		}else if(type == GameType.NMUL && level == 1){
			bot = new NMulHard(moveTime);
		}else if(type == GameType.POKER && level == 0){
			bot = new PokerEasy(moveTime);
		}else if(type == GameType.POKER && level == 1){
			bot = new PokerHard(moveTime);
		}
			
		return bot;
	}
}
