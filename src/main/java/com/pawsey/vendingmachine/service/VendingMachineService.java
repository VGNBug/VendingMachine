package com.pawsey.vendingmachine.service;

import com.pawsey.vendingmachine.model.Coin;

import java.util.ArrayList;
import java.util.Collection;

public class VendingMachineService {

    public Collection<Coin> getOptimalChangeFor(int pence) {

        Collection<Coin> output = new ArrayList<>();

        // TODO make this occur in a loop, and extract it
        if(pence >= 100) {
            output.add(Coin.ONE_POUND);
            pence = pence - 100;
        }
        else if(pence >= 50) {
            output.add(Coin.FIFTY_PENCE);
            pence = pence - 50;
        }
        else if(pence >= 20) {
            output.add(Coin.TWENTY_PENCE);
            pence = pence - 20;
        }
        else if (pence >= 10) {
            output.add(Coin.TEN_PENCE);
            pence = pence - 10;
        }
        else if(pence >= 5) {
            output.add(Coin.FIVE_PENCE);
        }
        else if(pence >= 2) {
            output.add(Coin.TWO_PENCE);
            pence = pence - 2;
        }
        else if(pence >= 1) {
            output.add(Coin.ONE_PENNY);
            pence = pence - 1;
        }

        return output;
    }
}
