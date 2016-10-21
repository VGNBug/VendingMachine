package com.pawsey.vendingmachine.service;

import com.pawsey.vendingmachine.component.CoinInventoryProcessor;
import com.pawsey.vendingmachine.component.CoinUtils;
import com.pawsey.vendingmachine.model.Coin;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class VendingMachineService extends BaseService {

    private CoinUtils coinUtils;
    private CoinInventoryProcessor coinInventoryProcessor;
    private Collection<Coin> coins;

    public VendingMachineService() {
        coinUtils = new CoinUtils();
        coinInventoryProcessor = new CoinInventoryProcessor();
        try {
            coins = coinInventoryProcessor.getInventory();
        } catch (IOException e) {
            LOG.error("Failed to get inventory due to a file-read exception");
        }
    }

    public Collection<Coin> getOptimalChangeFor(int pence) {

        return getOptimalCoins(pence);
    }

    public Collection<Coin> getChangeFor(int pence) {
        //TODO get optimal change
        Collection<Coin> optimalCoins = getOptimalCoins(pence);
        //TODO check if optimal change is available for each coin type
        for(Coin coin : optimalCoins) {
//            if(coin)
        }
            // TODO if not, calculate how to make the denomination with a smaller coin-type
            // TODO check if these coin types are available
        return null;
    }

    private List<Coin> getOptimalCoins(int pence) {
        List<Coin> output = new ArrayList<>();

        while (pence > 0) {
            int updatedPence = coinUtils.updatePence(pence);
            output.add(coinUtils.convertPenceToCoin(pence - updatedPence));
            pence = updatedPence;
        }

        return output;
    }

}
