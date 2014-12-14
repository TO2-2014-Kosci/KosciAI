import to2.dice.game.GameType;

public class BotFactory {

    /**
     * Creates a bot
     *
     * @param type type of game - N+, N* or Poker
     * @param level level - easy or hard
     * @param time determines how much time in seconds the bot has got to make
     * its move
     * @return Bot
     */
    public static Bot createBot(GameType type, Level level, int time) {
        Bot bot = null;

        if (type == GameType.NPLUS && level == Level.EASY) {
            bot = new NPlusEasy();
        } else if (type == GameType.NPLUS && level == Level.HARD) {
            bot = new NPlusHard();
        } else if (type == GameType.NMUL && level == Level.EASY) {
            bot = new NMulEasy();
        } else if (type == GameType.NMUL && level == Level.HARD) {
            bot = new NMulHard();
        } else if (type == GameType.POKER && level == Level.EASY) {
            bot = new PokerEasy();
        } else if (type == GameType.POKER && level == Level.HARD) {
            bot = new PokerHard();
        }
        bot.setTime(time);

        return bot;
    }
}
