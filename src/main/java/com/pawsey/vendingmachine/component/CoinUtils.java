package com.pawsey.vendingmachine.component;

import com.pawsey.vendingmachine.model.Coin;

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

}