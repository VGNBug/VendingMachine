package com.pawsey.vendingmachine.service;

import com.pawsey.vendingmachine.component.CoinUtils;
import com.pawsey.vendingmachine.model.Coin;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class VendingMachineService {

    private CoinUtils coinUtils;

    public VendingMachineService() {
        coinUtils = new CoinUtils();
    }

    public Collection<Coin> getOptimalChangeFor(int pence) {

        Collection<Coin> output = new ArrayList<>();

        while (pence > 0) {
            int updatedPence = updatePence(pence, output);
            output.add(coinUtils.convertPenceToCoin(pence - updatedPence));
            pence = updatedPence;
        }

        return output;
    }

    public Collection<Coin> getChangeFor(int pence) {
        return null;
    }

    private int updatePence(int pence, Collection<Coin> output) {
        if (pence >= 100) {
            pence = pence - 100;
        } else if (pence >= 50) {
            pence = pence - 50;
        } else if (pence >= 20) {
            pence = pence - 20;
        } else if (pence >= 10) {
            pence = pence - 10;
        } else if (pence >= 5) {
            pence = pence - 5;
        } else if (pence >= 2) {
            pence = pence - 2;
        } else if (pence >= 1) {
            pence = pence - 1;
        }
        return pence;
    }

}
