package com.pawsey.vendingmachine.component;

import com.pawsey.vendingmachine.model.Coin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CoinUtils {

    public static Coin convertPenceToCoin(int pence) {

        Coin coin;
        switch (pence) {
            case 100:
                return Coin.ONE_POUND;
            case 50:
                return Coin.FIFTY_PENCE;
            case 20:
                return Coin.TWENTY_PENCE;
            case 10:
                return Coin.TEN_PENCE;
            case 5:
                return Coin.FIVE_PENCE;
            case 2:
                return Coin.TWO_PENCE;
            case 1:
                return Coin.ONE_PENNY;
            default:
                return null;
        }
    }

    public int updatePence(int pence) {
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

    public int updatePenceWithCoin(Coin coin, int pence) {
        return pence - coin.getValue();
    }

    public Map<String, Integer> getCoinInventoryMap(List<Coin> coinList) {
        Map<String, Integer> coinMap = new HashMap<>();

        int coinCount = 1;
        for (Coin coin : coinList) {
            if (coinMap.get(coin.name()) != null) {
                coinCount = coinMap.get(coin.name()) + 1;
                coinMap.remove(coin.name());
            }
            coinMap.put(coin.name(), coinCount);
            coinCount = 1;
        }
        return sortCoinInventoryMap(coinMap);
    }

    private Map<String, Integer> sortCoinInventoryMap(Map<String, Integer> unsortedCoinMap) {
        Map<String, Integer> sortedCoinMap = new HashMap<>();
        Iterator iterator = unsortedCoinMap.entrySet().iterator();
        int maxValue = 0;

        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            if(Coin.valueOf(pair.getKey().toString()).getValue() > maxValue) {
                maxValue = Coin.valueOf(pair.getKey().toString()).getValue();
                sortedCoinMap.put(pair.getKey().toString(), (Integer) pair.getValue());
            }
        }

        return sortedCoinMap;
    }

}