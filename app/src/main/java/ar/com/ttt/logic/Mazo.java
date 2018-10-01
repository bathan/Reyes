package ar.com.ttt.logic;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;

import ar.com.ttt.logic.enums.Palo;

public class Mazo {

    private ArrayList<Carta> cartas;

    public Mazo(@NonNull boolean remove8and9) {

        this.cartas = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {

            // If we dont need 8's and 9's we skip adding them to the deck
            if(remove8and9 && (i == 8 || i == 9)) {
                continue;
            }

            cartas.add(new Carta(Palo.BASTOS,i));
            cartas.add(new Carta(Palo.OROS,i));
            cartas.add(new Carta(Palo.ESPADAS,i));
            cartas.add(new Carta(Palo.COPAS,i));
        }

        // Shuffle the Deck
        Collections.shuffle(cartas);
    }

    public ArrayList<Carta> getCartas() {
        return this.cartas;
    }
}
