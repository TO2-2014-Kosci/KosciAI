import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

class NMulEasy extends NMul {

    

    public NMulEasy() {
        allOptions = initList();
    }

    @Override
    public void chooseOption(int dice[], List<int[]> otherDice) throws Exception{
    	
   
        this.dice = dice;

        HashMap<boolean[], Double> map = new HashMap<boolean[], Double>();
        ValueComparator bvc = new ValueComparator(map);
        TreeMap<boolean[], Double> sorted_map = new TreeMap<boolean[], Double>(bvc);

        int mulLeft;			//iloczyn oczek zostawionych
        double prob;
        boolean[] option;
        double bestProb = 0;	//najlepsze prawdobodobienstwo
        int target;				//jaka sume trzeba trafic pozostalymi koscmi
        int dicesToThrow = 0;
        int allThrows;
        int currMatches;

        for (int i = 0; i < allOptions.size(); i++) {  //petla po wszystkich opcjach

            //wybranie opcji, wyliczenie iloczynu pozostalych i ile trzeba wyrzucic
            option = allOptions.get(i);
            mulLeft = leftToScore(dice, option);

            target = score / mulLeft;

            //ile kostek do przerzucenia przy danej opcji
            dicesToThrow = 0;
            for (int j = 0; j < option.length; j++) {
                if (option[j]) {
                    dicesToThrow++;
                }
            }

            //zapisanie do zmiennej traf ilosc trafien wyniku
            countMatches(dicesToThrow, 1, target);
            currMatches = matches;
            matches = 0;

            //wyliczenia ilosci wszystkich rzutow dla danej opcji
            allThrows = 1;
            for (int j = 0; j < dicesToThrow; j++) {
                allThrows *= 6;
            }

            //obliczenie prawdopodobienstwa, i zapisanie go jesli jest najlepsze z opcja
            prob = (1.0 * currMatches) / allThrows;
            if(prob > bestProb){
            	bestProb = prob;
            	setOption(option);
            }

            map.put(allOptions.get(i), prob);

        }

        //posortowanie mapy boolean[]:int
        sorted_map.putAll(map);

        Random generator = new Random();
        int index = generator.nextInt(3);
        Iterator<boolean[]> iterator = sorted_map.keySet().iterator();

        for (int j = 0; j < index && iterator.hasNext(); j++) {
            iterator.next();
        }
        option = (boolean[]) iterator.next();

        setOption(option);
		//System.out.println("NMulEasy: praw. = " + map.get(option));
        //optionToString(option);
      
    }

}
