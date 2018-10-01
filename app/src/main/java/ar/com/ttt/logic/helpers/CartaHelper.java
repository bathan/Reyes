package ar.com.ttt.logic.helpers;

import ar.com.ttt.logic.Carta;
import ar.com.ttt.logic.enums.Palo;

public class CartaHelper {

    private Carta carta;

    public CartaHelper(Carta carta) {
        this.carta = carta;
    }

    public String getFancyName() {

        String fancyName;
        int numeroCarta = this.carta.numero;
        Palo paloCarta = this.carta.palo;

        PaloHelper paloHelper = new PaloHelper(paloCarta);


        if (numeroCarta == 1) {

            fancyName = "Ancho ";

            if (paloCarta == Palo.COPAS || paloCarta == Palo.OROS) {
                fancyName += " Falso";
            }

            return fancyName;

        } else if (numeroCarta == 10) {
            fancyName = "Sota";
        } else if (numeroCarta == 11) {
            fancyName = "Caballo";
        } else if (numeroCarta == 12) {
            fancyName = "Rey";
        } else {
            fancyName = Integer.toString(numeroCarta);
        }

        fancyName = fancyName + " de " + paloHelper.getPaloName();

        return fancyName;
    }
}