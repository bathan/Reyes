package ar.com.ttt.logic;

import ar.com.ttt.logic.enums.Palo;

public class Carta {

    public Palo palo;
    public int numero;

    public Carta(Palo palo, int numero) {
        this.palo = palo;
        this.numero = numero;
    }
}
