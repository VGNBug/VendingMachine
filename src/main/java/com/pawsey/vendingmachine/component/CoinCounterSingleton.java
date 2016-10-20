package com.pawsey.vendingmachine.component;

import com.pawsey.vendingmachine.model.Coin;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CoinCounterSingleton {

    private CoinCounterSingleton instance;
    private CoinInventoryProcessor coinInventoryProcessor;
    private List<Coin> coins;

    private CoinCounterSingleton() throws IOException {
        coinInventoryProcessor = new CoinInventoryProcessor();
        instance = new CoinCounterSingleton();

        // First access setup
        coins = coinInventoryProcessor.getInventory();
    }

    public CoinCounterSingleton getInstance() {
        return instance;
    }

    public List<Coin> getCoinInventory() {
        return coins;
    }

    public void setCoinInventory(Collection<Coin> coinInventory) {

    }
}
