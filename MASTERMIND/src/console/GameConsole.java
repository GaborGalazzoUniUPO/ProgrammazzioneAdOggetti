package console;

import jbook.util.Input;
import palyer.Computer;
import palyer.Human;
import util.Judge;

public class GameConsole {

    public static Human human;
    public static Computer computer;
    public static int numBU, numBC, numMU, numMC;
    public static int guessMax;
    public static String targetU, targetC, guessU, guessC;

    /**
     * Il main fa giocare human vs computer;
     */
    public static void main(String[] args) {
        while (true) {
            int numGuess = 0;
            inizializzaGioco();
            while (numGuess < guessMax) {
                System.out.println("Siamo al guess n:" + (numGuess+1));
                guessU = human.genGuess();
                guessC = computer.genGuess();
                System.out.println("Guess del computer:" + guessC);
                aggiornaListaTentativi();
                stampaSituazioneTentativi();
                if ((numBU == 4) || (numBC == 4))
                    break;
                numGuess++;
            }
            stampaSituazioneFinale();
        }
    }

    /**
     * Il metodo inizializzaGioco chiede all'utente se vuole giocare,  il
     * numero di guess massimo, e inizializza gli oggetti computer e utente.
     */
    public static void inizializzaGioco() {
        String risposta;
        do
            risposta = Input.readString("Vuoi inziare una partita? (y/n)");
        while (!(risposta.equals("y")) && !(risposta.equals("n")));
        if (risposta.equals("n"))
            System.exit(1);
        guessMax = Input.readInt("Quale deve essere il numero massimo di guess:");
        if(human == null)
            human = new Human();
        else
            human.init();
        if(computer == null)
            computer = new Computer();
        else
            computer.init();
        targetC = computer.getTarget();
        targetU = human.getTarget();

    }

    /**
     * Il metodo aggiornaListaTentativi aggiunge il risultato del guess corrente
     * (dell'utente e del computer) nelle loro rispettive liste di tentativi.
     */
    public static void aggiornaListaTentativi() {
        numBU = Judge.numBulls(guessU, targetC);
        numMU = Judge.numMaggots(guessU, targetC);
        numBC = Judge.numBulls(guessC, targetU);
        numMC = Judge.numMaggots(guessC, targetU);
        computer.addAttempt(guessC, numBC, numMC);
        human.addAttempt(guessU, numBU, numMU);
    }

    /**
     * Il metodo stampaSituazioneTentativi stampa le liste di tentativi
     * dell'utente e del computer.
     */
    public static void stampaSituazioneTentativi() {
        System.out.println("Situazione tentativi utente");
        System.out.println("****************************");
        System.out.println(human.attemptToString());
        System.out.println("****************************");
        System.out.println("Situazione tentativi computer");
        System.out.println("****************************");
        System.out.println(computer.attemptToString());
        System.out.println("****************************");
    }

    /**
     * Il metodo stampaSituazioneFinale stampa il risultato del gioco
     */
    public static void stampaSituazioneFinale() {
        if (numBU != 4 && numBC != 4) {
            System.out.println("Raggiunto il limite massimo dei guess e nessuno ha vinto!");
            System.out.println("La combinazione del computer era: " + computer.getTarget());
        }
        if (numBU == 4 && numBC != 4)
            System.out.println("Bravo hai vinto!");
        if (numBU != 4 && numBC == 4)
            System.out.println("Il computer e' stato piu' bravo di te!");
        if (numBU == 4 && numBC == 4)
            System.out.println("Avete vinto entrambi!");
    }

}
