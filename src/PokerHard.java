import java.util.List;
import java.util.Map;

class PokerHard extends Poker {

    public PokerHard() {
    }

    @Override
    public void chooseResult(int[] dice, List<int[]> otherDice) {
        int[] bestDice;
        Figure bestFigure;
        Figure curFigure;

        this.dice = dice;
        this.otherDice = otherDice;

        Map<Integer, Integer> curMap;  //mapy oczko:powtorzenie, 
        Map<Integer, Integer> bestMap;  //potrzebne przy tych samych figurach

        int curToCompare = 0;   //oczko na kosci do porownania
        int bestToCompare = 0;

        bestDice = choseBestDice(otherDice);

        curMap = fillMap(dice);
        bestMap = fillMap(bestDice);

        curFigure = checkFigure(dice);
        //System.out.println(curFigure);

        bestFigure = checkFigure(bestDice);

        for (int i = 0; i < result.length; i++) {
            result[i] = false;
        }

        setResult(result);

        switch (curFigure) {

            /*Metoda zwraca tablic�, w kt�rej wszystkie elementy s� FALSE, chyba �e kt�ry� przeciwnik 
             * ma wy�szego pokera, wtedy wszystkie s� TRUE*/
            case POKER:

                if (bestFigure == Figure.POKER) {
                    for (int i = 1; i <= 6; i++) {
                        if (curMap.get(i) == 5) {
                            curToCompare = i;
                        }
                        if (bestMap.get(i) == 5) {
                            bestToCompare = i;
                        }
                    }
                    if (bestToCompare > curToCompare) {
                        for (int i = 0; i < dice.length; i++) {
                            result[i] = true;
                        }
                    }
                }
                break;

            /*Metoda zwraca tablic�, w kt�rej warto�� odpowiadaj�ca ko�ci r�nej od 
             * pozosta�ych jest TRUE, reszta FALSE, chyba �e kt�ry� z przeciwnik�w ma 
             * pokera z cyfr wy�szych ni� nasza kareta, wtedy wszystkie warto�ci s� TRUE*/
            case FOUR:
                if (bestFigure == Figure.POKER) {
                    for (int i = 1; i <= 6; i++) {
                        if (curMap.get(i) == 4) {
                            curToCompare = i;
                        }
                        if (bestMap.get(i) == 5) {
                            bestToCompare = i;
                        }
                    }
                }
                if (bestToCompare > curToCompare) {
                    for (int i = 0; i < dice.length; i++) {
                        result[i] = true;
                    }
                } else {
                    for (int i = 0; i < dice.length; i++) {
                        if (curMap.get(dice[i]) != 4) {
                            result[i] = true;
                        }
                    }
                }
                break;

            /*Metoda zwraca tablic�, w kt�rej wszystkie elementy s� FALSE je�li przeciwnicy nie maj� 
             * wy�szych figur. Je�li maj�, przerzuca par�, chyba �e kto� ma pokera z wy�szych ko�ci 
             * ni� nasza tr�jka, wtedy przerzuca wszystkie.*/
            case FULL:
                if (bestFigure != Figure.POKER && bestFigure != Figure.FOUR && bestFigure != Figure.FULL) {
                    break;
                } else if (bestFigure == Figure.FULL) {
                    boolean theBest = true;
                    for (int i = 1; i <= 6; i++) {
                        if (curMap.get(i) !=  bestMap.get(i)) {
                            theBest = false;
                        }
                    }
                    //jezeli mam gorszego fulla, przerzucam pare
                    if (!theBest) {
                        for (int i = 0; i < dice.length; i++) {
                            if (curMap.get(dice[i]) == 2) {
                                result[i] = true;
                            }
                        }
                    } else {
                        break;
                    }
                } else if (bestFigure == Figure.FOUR) {
                    for (int i = 0; i < dice.length; i++) {
                        if (curMap.get(dice[i]) == 2) {
                            result[i] = true;
                        }
                    }
                } else if (bestFigure == Figure.POKER) {
                    for (int i = 1; i <= 6; i++) {
                        if (bestMap.get(i) == 5) {
                            bestToCompare = i;
                        }
                        if (curMap.get(i) == 3) {
                            curToCompare = i;
                        }
                    }
                    //patrze, czy trojka w fullu jest wieksza niz poker, jesli tak, przerzucam pare
                    if (curToCompare >= bestToCompare) {
                        for (int i = 0; i < dice.length; i++) {
                            if (curMap.get(dice[i]) == 2) {
                                result[i] = true;
                            }
                        }
                    } else {
                        for (int i = 1; i <= 6; i++) {
                            if (bestMap.get(i) == 5) {
                                bestToCompare = i;
                            }
                            if (curMap.get(i) == 2) {
                                curToCompare = i;
                            }
                        }
                        //patrze, czy dwoja w fullu jest wieksza, jesli tak, przerzucam trojke
                        if (curToCompare >= bestToCompare) {
                            for (int i = 0; i < dice.length; i++) {
                                if (curMap.get(dice[i]) == 3) {
                                    result[i] = true;
                                }
                            }
                        } else {
                            //jezeli ani trojka, ani dwojka nie jest wieksza niz poker, przerzucam wszystkie
                            for (int i = 0; i < result.length; i++) {
                                result[i] = true;
                            }
                        }

                    }
                }
                break;

            /*Je�li nikt nie ma wy�szej figury, metoda zwraca tablic�, w kt�rej wszystkie 
             * elementy s� FALSE. W przeciwnym wypadku zostawia najwy�sz� ko��, reszt� przerzucaj�c.*/
            case STRIT:

                if (bestFigure == Figure.STRIT) {

                    if (curMap.get(1) == 1 && bestMap.get(6) == 1) {
                        for (int i = 0; i < dice.length; i++) {
                            if (dice[i] == 1) {
                                result[i] = true;
                            }
                        }
                    }
                } else if (bestFigure == Figure.POKER && bestFigure == Figure.FULL) {
                    if (curMap.get(6) == 1) {
                        for (int i = 0; i < dice.length; i++) {
                            if (dice[i] != 6) {
                                result[i] = true;
                            }
                        }
                    } else {
                        if (bestFigure == Figure.POKER && bestDice[0] == 6) {
                            for (int i = 0; i < dice.length; i++) {
                                result[i] = true;
                            }
                        } else {
                            for (int i = 0; i < dice.length; i++) {
                                if (dice[i] != 5) {
                                    result[i] = true;
                                }
                            }
                        }
                    }
                }

                break;

            /*Metoda zwraca tablic�, w kt�rej warto�ci odpowiadaj�ce ko�ciom z tr�jki 
             * maj� warto�� FALSE, a pozosta�ym TRUE, chyba �e kt�ry� z przeciwnik�w ma 
             * pokera z cyfr wy�szych ni� nasza tr�jka, wtedy wszystkie warto�ci s� TRUE*/
            case THREE:
                if (bestFigure == Figure.POKER) {
                    for (int i = 1; i <= 6; i++) {
                        if (curMap.get(i) == 3) {
                            curToCompare = i;
                        }
                        if (bestMap.get(i) == 5) {
                            bestToCompare = i;
                        }
                    }
                }
                if (bestToCompare > curToCompare) {
                    for (int i = 0; i < dice.length; i++) {
                        result[i] = true;
                    }
                } else {
                    for (int i = 0; i < dice.length; i++) {
                        if (curMap.get(dice[i]) != 3) {
                            result[i] = true;
                        }
                    }
                }
                break;

            /*Metoda zwraca tablic�, w kt�rej warto�� odpowiadaj�ca ko�ci bez pary jest TRUE, 
             * pozosta�e FALSE je�li �aden z przeciwnik�w nie ma pokera, czworki lub fulla 
             * W przeciwnym wypadku, je�li wy�ej wspomniany poker lub czworka sk�ada 
             * si� z cyfr wy�szych ni� wyzsza z par, przerzu� wszystkie. Jesli tylko wyzsze od
             * jednej pary, przezuc wszystkie oprocz tej pary. 
             * Je�li najwyzsza jest full, ale ani trojka, ani dwojka nie jest wyzsza od wyzszej pary
             * w fullu, przezuc wszystkie oprocz tej pary. Inaczej przezuc wszystkie*/
            case TWO_PAIRS:
                if (bestFigure == Figure.POKER || bestFigure == Figure.FOUR) {
                    for (int i = 1; i <= 6; i++) {
                        if (curMap.get(i) == 2 && i > curToCompare) {
                            curToCompare = i;
                        }
                        if (bestMap.get(i) == 4 || bestMap.get(i) == 5) {
                            bestToCompare = i;
                        }
                    }
                    //jezeli najwyzsza para i tak jest mniejsza od pokera i czworki, przezuc wszystkie
                    if (bestToCompare > curToCompare) {
                        for (int i = 0; i < result.length; i++) {
                            result[i] = true;
                        }
                        //inaczej, zostaw te pare
                    } else {
                        for (int i = 0; i < dice.length; i++) {
                            if (dice[i] != curToCompare) {
                                result[i] = true;
                            }
                        }
                    }
                    //dla fulla, jezeli mamy szanse na lepszego po przezuceniu koscia, to przerzucamy, inaczej rzucamy tymi poza lepsza para
                } else if (bestFigure == Figure.FULL) {
                    for (int i = 1; i <= 6; i++) {
                        if (curMap.get(i) == 2 && i > curToCompare) {
                            curToCompare = i;
                        }
                        if ((bestMap.get(i) == 3 || bestMap.get(i) == 2) && i > bestToCompare) {
                            bestToCompare = i;
                        }
                    }
                    //System.out.println(curToCompare);
                    //System.out.println(bestToCompare);
                    if (curToCompare > bestToCompare) {
                        for (int i = 0; i < dice.length; i++) {
                            if (curMap.get(dice[i]) == 1) {
                                result[i] = true;
                            }
                        }
                    } else {
                        for (int i = 0; i < dice.length; i++) {
                            if (dice[i] != curToCompare) {
                                result[i] = true;
                            }
                        }
                    }
                    //w innym przypadku, przerzuc ta spoza par
                } else {
                    for (int i = 0; i < dice.length; i++) {
                        if (curMap.get(dice[i]) == 1) {
                            result[i] = true;
                        }
                    }
                }
                break;

            /*Metoda zwraca tablic�, w kt�rej warto�ci odpowiadaj�ce ko�ciom z pary 
             * maj� warto�� FALSE, a pozosta�ym TRUE, chyba �e kt�ry� z przeciwnik�w 
             * ma pokera z cyfr wy�szych ni� nasza para, wtedy wszystkie warto�ci s� TRUE*/
            case PAIR:
                if (bestFigure == Figure.POKER) {
                    for (int i = 1; i <= 6; i++) {
                        if (curMap.get(i) == 2) {
                            curToCompare = i;
                        }
                        if (bestMap.get(i) == 5) {
                            bestToCompare = i;
                        }
                    }
                }
                if (bestToCompare > curToCompare) {
                    for (int i = 0; i < dice.length; i++) {
                        result[i] = true;
                    }
                } else {

                    for (int i = 0; i < dice.length; i++) {

                        if (curMap.get(dice[i]) == 1) {
                            result[i] = true;
                        }
                    }
                }
                break;

            /*Je�li nikt nie ma uk�adu wy�szego ni� strit, przerzuca jedynk�.*/
            case NOTHING:
                if (bestFigure != Figure.FULL && bestFigure != Figure.FOUR && bestFigure != Figure.POKER) {
                    for (int i = 0; i < dice.length; i++) {
                        if (dice[i] == 1) {
                            result[i] = true;
                        }
                    }
                } else {
                    for (int i = 0; i < dice.length; i++) {
                        if (dice[i] != 6) {
                            result[i] = true;
                        }
                    }
                }

                break;
            default:
                break;
        }

        setResult(result);
        //resultToString(result);

    }

    private int[] choseBestDice(List<int[]> otherDice) {
        int[] bestDice = otherDice.get(0);
        int[] curDice;
        Figure bestFigure = checkFigure(bestDice);
        Figure curFigure;
        Map<Integer, Integer> curMap;  //mapy oczko:powtorzenie, 
        Map<Integer, Integer> bestMap;  //potrzebne przy tych samych figurach

        int curToCompare = 0;   //oczko na kosci do porownania
        int bestToCompare = 0;

        for (int i = 1; i < otherDice.size(); i++) {
            curDice = otherDice.get(i);
            curFigure = checkFigure(curDice);
            if (curFigure.ordinal() > bestFigure.ordinal()) {
                bestFigure = curFigure;
                bestDice = curDice;
            } else if (curFigure.ordinal() == bestFigure.ordinal()) {

                //uzupelnianie map
                curMap = fillMap(curDice);
                bestMap = fillMap(bestDice);

                //sprawdzam, ktora figura jest wyzsza, schodzac w dol mapy oczek
                boolean curCheck = false;
                boolean bestCheck = false;

                switch (curFigure) {

                    case POKER:
                        for (int j = 6; j >= 1; j--) {
                            if (curMap.get(j) == 5) {
                                curCheck = true;
                            }
                            if (bestMap.get(j) == 5) {
                                bestCheck = true;
                            }
                            if (curCheck && !bestCheck) {
                                bestDice = curDice;
                                break;
                            }
                        }
                        break;

                    case FOUR:
                        for (int j = 1; j <= 6; j++) {
                            if (curMap.get(j) == 4) {
                                curToCompare = j;
                            }
                            if (bestMap.get(j) == 4) {
                                bestToCompare = j;
                            }
                        }
                        if (curToCompare > bestToCompare) {
                            bestDice = curDice;
                        } //jezeli 4 jest z tych samych, sprawdzam ostatnia
                        else if (curToCompare == bestToCompare) {
                            for (int j = 6; j >= 1; j--) {
                                if (curMap.get(j) == 1) {
                                    curCheck = true;
                                }
                                if (bestMap.get(j) == 1) {
                                    bestCheck = true;
                                }
                                if (curCheck && !bestCheck) {
                                    bestDice = curDice;
                                    break;
                                }
                            }
                        }
                        break;

                    case FULL:
                        for (int j = 1; j <= 6; j++) {
                            if (curMap.get(j) == 3) {
                                curToCompare = j;
                            }
                            if (bestMap.get(j) == 3) {
                                bestToCompare = j;
                            }
                        }
                        if (curToCompare > bestToCompare) {
                            bestDice = curDice;
                        } //jezeli 3 takie same, sprawdzam 2
                        else if (curToCompare == bestToCompare) {
                            for (int j = 6; j >= 1; j--) {
                                if (curMap.get(j) == 2) {
                                    curCheck = true;
                                }
                                if (bestMap.get(j) == 2) {
                                    bestCheck = true;
                                }
                                if (curCheck && !bestCheck) {
                                    bestDice = curDice;
                                    break;
                                }
                            }
                        }
                        break;

                    case STRIT:
                        if (curMap.get(6) == 1 && bestMap.get(1) == 1) {
                            bestDice = curDice;
                        }
                        break;

                    case THREE:
                        for (int j = 1; j <= 6; j++) {
                            if (curMap.get(j) == 3) {
                                curToCompare = j;
                            }
                            if (bestMap.get(j) == 3) {
                                bestToCompare = j;
                            }
                        }
                        if (curToCompare > bestToCompare) {
                            bestDice = curDice;
                        } //jezeli 3 jest z tych samych, sprawdzam od konca mape
                        else if (curToCompare == bestToCompare) {
                            for (int j = 6; j >= 1; j--) {
                                if (curMap.get(j) == 1) {
                                    curCheck = true;
                                }
                                if (bestMap.get(j) == 1) {
                                    bestCheck = true;
                                }
                                //to samo, schodze nizej
                                if (curCheck && bestCheck) {
                                    curCheck = false;
                                    bestCheck = false;
                                } else if (curCheck && !bestCheck) {
                                    bestDice = curDice;
                                    break;
                                } else if (!curCheck && bestCheck) {
                                    break;
                                }
                            }
                        }
                        break;

                    //dla dwoch par, schodze w dol, porownujac pary
                    case TWO_PAIRS:
                        int samePairs = 0;//sprawdzam, ile razy powtorzyly sie pary 
                        for (int j = 6; j >= 1; j--) {
                            if (curMap.get(j) == 2) {
                                curCheck = true;
                            }
                            if (bestMap.get(j) == 2) {
                                bestCheck = true;
                            }
                            if (curCheck && bestCheck) {
                                curCheck = false;
                                bestCheck = false;
                                samePairs++;
                            } else if (curCheck && !bestCheck) {
                                bestDice = curDice;
                                break;
                            } else if (!curCheck && bestCheck) {
                                break;
                            }

                        }

                        if (samePairs == 2) {
                            for (int j = 6; j >= 1; j--) {
                                if (curMap.get(j) == 1) {
                                    curCheck = true;
                                }
                                if (bestMap.get(j) == 1) {
                                    bestCheck = true;
                                }
                                if (curCheck && !bestCheck) {
                                    bestDice = curDice;
                                    break;
                                }
                            }
                        }
                        break;
                    case PAIR:

                        for (int j = 6; j >= 1; j--) {
                            if (curMap.get(j) == 2) {
                                curToCompare = j;
                            }
                            if (bestMap.get(j) == 2) {
                                bestToCompare = j;
                            }
                        }
                        if (curToCompare > bestToCompare) {
                            bestDice = curDice;
                        } else if (curToCompare == bestToCompare) {
                            for (int j = 6; j >= 1; j--) {
                                if (curMap.get(j) == 1) {
                                    curCheck = true;
                                }
                                if (bestMap.get(j) == 1) {
                                    bestCheck = true;
                                }
                                if (curCheck && bestCheck) {
                                    curCheck = false;
                                    bestCheck = false;
                                } else if (curCheck && !bestCheck) {
                                    bestDice = curDice;
                                    break;
                                } else if (!curCheck && bestCheck) {
                                    break;
                                }
                            }
                        }
                        break;
                    case NOTHING:
                        for (int j = 6; j >= 1; j--) {
                            if (curMap.get(j) == 1) {
                                curCheck = true;
                            }
                            if (bestMap.get(j) == 1) {
                                bestCheck = true;
                            }
                            if (curCheck && bestCheck) {
                                curCheck = false;
                                bestCheck = false;
                            } else if (curCheck && !bestCheck) {
                                bestDice = curDice;
                                break;
                            } else if (!curCheck && bestCheck) {
                                break;
                            }
                        }
                        break;
                }
            }

        }

        return bestDice;
    }

}
