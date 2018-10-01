package ar.com.ttt.logic.helpers;

import ar.com.ttt.logic.enums.Palo;

public class PaloHelper {

    private Palo palo;

    public PaloHelper(Palo palo) {
        this.palo = palo;
    }

    public String getPaloName() {
        String paloName = "";

        switch(palo) {
            case OROS: {
                paloName = "Oros";
                break;
            }
            case BASTOS: {
                paloName = "Bastos";
                break;
            }
            case COPAS: {
                paloName = "Copas";
                break;
            }
            case ESPADAS: {
                paloName = "Espadas";
                break;
            }
        }

        return paloName;
    }
}
