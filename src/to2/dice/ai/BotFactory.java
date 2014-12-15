package to2.dice.ai;

import to2.dice.ai.ngames.nmul.NMulHard;
import to2.dice.ai.ngames.nplus.NPlusEasy;
import to2.dice.ai.ngames.nplus.NPlusHard;
import to2.dice.ai.poker.PokerEasy;
import to2.dice.ai.poker.PokerHard;
import to2.dice.game.BotLevel;
import to2.dice.game.GameType;
import to2.dice.ai.ngames.nmul.NMulEasy;

public class BotFactory {

    /**
     * Creates a bot
     *
     * @param type type of game - N+, N* or Poker
     * @param level level - easy or hard
     * @param time determines how much time in seconds the bot has got to make
     * its move
     * @return to2.dice.ai.Bot
     */
    public static Bot createBot(GameType type, BotLevel level, int time) {
        Bot bot = null;

        if (type == GameType.NPLUS && level == BotLevel.EASY) {
            bot = new NPlusEasy();
        } else if (type == GameType.NPLUS && level == BotLevel.HARD) {
            bot = new NPlusHard();
        } else if (type == GameType.NMUL && level == BotLevel.EASY) {
            bot = new NMulEasy();
        } else if (type == GameType.NMUL && level == BotLevel.HARD) {
            bot = new NMulHard();
        } else if (type == GameType.POKER && level == BotLevel.EASY) {
            bot = new PokerEasy();
        } else if (type == GameType.POKER && level == BotLevel.HARD) {
            bot = new PokerHard();
        }
        bot.setTime(time);

        return bot;
    }
}
