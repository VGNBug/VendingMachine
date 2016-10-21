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

}