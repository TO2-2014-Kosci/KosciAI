
//Klasa od tworzenia botow
public class BotFactory {
	//zwraca bota do danej gry, w konstruktorze: typ, poziom
	public DiceBot createBot(GameType type, int level, int time){
		DiceBot bot = null;
		
		if(type == GameType.NPLUS && level == 0){
			bot = new NPlusEasy();
		}else if(type == GameType.NPLUS && level == 1){
			bot = new NPlusHard();
		}else if(type == GameType.NMUL && level == 0){
			bot = new NMulEasy();
		}else if(type == GameType.NMUL && level == 1){
			bot = new NMulHard();
		}else if(type == GameType.POKER && level == 0){
			bot = new PokerEasy();
		}else if(type == GameType.POKER && level == 1){
			bot = new PokerHard();
		}
		bot.setTime(time);
		
		return bot;
	}
}
