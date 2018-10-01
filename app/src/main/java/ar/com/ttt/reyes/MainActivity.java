package ar.com.ttt.reyes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import ar.com.ttt.logic.Carta;
import ar.com.ttt.logic.Mazo;
import ar.com.ttt.logic.enums.Palo;
import ar.com.ttt.logic.helpers.CartaHelper;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    // Lets Create a Mazo
    Mazo mazo = new Mazo(true);

    // Setup del Truco
    int numberOfPlayers = 6;
    int currentPlayer = 1;

    // Jugadores que ya les tocó un rey
    boolean[] isPlayerServed = new boolean[numberOfPlayers];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timber.plant(new Timber.DebugTree());


        Timber.d("### onCreate:  ARRANCAMOS A TIRAR REYES !");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Carta> cartas = mazo.getCartas();

        int deckSize = cartas.size();

        Map<Integer, ArrayList<Carta>> cartasJugadores = new HashMap<>();


        for (int i = 0; i < deckSize; i++) {
            //-- Pick a Card from the deck
            Carta pickedCard = cartas.get(0);

            //-- Remove it from the Deck
            cartas.remove(0);

            //-- Give it to some player, unless he has already been served, in witch case we need to increment the player id
            currentPlayer = getNextPlayerToServeCard(currentPlayer);

            //-- TODO: Double Check this
            ArrayList<Carta> cartasJugador = cartasJugadores.get(currentPlayer);
            if (cartasJugador == null) {
                cartasJugador = new ArrayList<>();
            }
            cartasJugador.add(pickedCard);
            cartasJugadores.put(currentPlayer, cartasJugador);
            Timber.d("### Jugador %s Recibe Carta %s :", currentPlayer, (new CartaHelper(pickedCard)).getFancyName());

            //-- Check if card is King
            if (pickedCard.numero == 12) {
                isPlayerServed[currentPlayer - 1] = true;
                Timber.d("### Jugador %s SACO REY! :", currentPlayer);
            }

            if (weAreDone(isPlayerServed)) {
                //-- Ya tenemos a los jugadores
                Timber.d("### onCreate:  YA ESTAN TODOS!");
                break;
            }

            if (currentPlayer == numberOfPlayers) {
                currentPlayer = 1;
            } else {
                currentPlayer++;
            }

        }

        String equipos = getTeams(isPlayerServed);

        Timber.d("### %s",equipos);

    }


    private int getNextPlayerToServeCard(int player) {
        //-- Give it to some player, unless he has already been served, in witch case we need to increment the player id
        int playerIndex = player - 1;

        if (isPlayerServed[playerIndex]) {
            // Need to skip this player since he is already picked
            if (player == numberOfPlayers) {
                player = 1;
            } else {
                player++;
            }
            return getNextPlayerToServeCard(player);
        } else {
            return player;
        }
    }

    public static boolean weAreDone(boolean[] array) {
        int totalCount = array.length / 2;
        int count = 0;

        for (boolean b : array) {
            if (b) count++;
        }

        return count == totalCount;
    }

    public static String getTeams(boolean[] array) {

        Map<String, ArrayList<String>> teams = new HashMap<>();

        for (int i = 0; i < array.length; i++) {

            boolean val = array[i];

            String teamKey = "Team 1";
            if (!val) {
                teamKey = "Team 2";
            }

            ArrayList<String> t = teams.get(teamKey);
            if (t == null) {
                t = new ArrayList<>();
            }
            t.add(Integer.toString(i+1));
            teams.put(teamKey, t);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(" EQUIPO 1: ");
        sb.append( String.join(",", teams.get("Team 1")));

        sb.append(" EQUIPO 2: ");
        sb.append( String.join(",", teams.get("Team 2")));

        return sb.toString();

    }
}
