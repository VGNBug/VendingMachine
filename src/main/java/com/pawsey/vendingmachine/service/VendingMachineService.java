package com.pawsey.vendingmachine.service;

import com.pawsey.vendingmachine.component.CoinInventoryProcessor;
import com.pawsey.vendingmachine.component.CoinUtils;
import com.pawsey.vendingmachine.model.Coin;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.zip.DataFormatException;

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
        Collection<Coin> output = new ArrayList<>();

        try {
            output = getChangeFromLimitedCoinPool(pence);
        } catch (IOException e) {
            LOG.error("There was a problem reading a data file required for this function.");
        } catch (DataFormatException e) {
            LOG.error("A file required for this function is incorrectly formatted.");
        }
        return output;
    }

    private Collection<Coin> getChangeFromLimitedCoinPool(int pence) throws IOException, DataFormatException {
        Collection<Coin> output = new ArrayList<>();

        for (Coin coin : getOptimalCoins(pence)) {
            output = addOptimalCoinIfAvailableOrRecalculate(output, coin);
        }
        return output;
    }

    private Collection<Coin> addOptimalCoinIfAvailableOrRecalculate(Collection<Coin> output, Coin coin) throws IOException, DataFormatException {
        final Map<String, Integer> coinInventoryMap = coinUtils.getCoinInventoryMap(coinInventoryProcessor.getInventory());

        if (coinInventoryMap.get(coin.name()) > 0) {
            output.add(coin);
        } else {
            Iterator iterator = coinInventoryMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry pair = (Map.Entry) iterator.next();
                final Object key = pair.getKey();

                if (!key.equals(coin.name())) {
                    addOptimalCoinIfAvailableOrRecalculate(output, Coin.valueOf(key.toString()));
                } else
                    throw new DataFormatException("CoinUtils.getInventoryMap() has returned a duplicate coin type. Please check data file.");

                iterator.remove();
            }

        }
        return output;
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
