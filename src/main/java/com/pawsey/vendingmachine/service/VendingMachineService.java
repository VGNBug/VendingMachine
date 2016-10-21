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
            int updatedPence = coinUtils.updatePence(pence);
            output.add(coinUtils.convertPenceToCoin(pence - updatedPence));
            pence = updatedPence;
        }

        return output;
    }

    public Collection<Coin> getChangeFor(int pence) {
        return null;
    }

}
