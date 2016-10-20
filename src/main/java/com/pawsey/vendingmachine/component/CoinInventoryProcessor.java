package com.pawsey.vendingmachine.component;

import com.pawsey.vendingmachine.model.Coin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CoinInventoryProcessor {

    private CoinUtils coinUtils;

    public CoinInventoryProcessor() {
        coinUtils = new CoinUtils();
    }

    public List<Coin> getInventory() throws IOException {

        List<Coin> initialCoins = new ArrayList<>();

        int i = 0;
        for (String line : Files.readAllLines(Paths.get("/resources/coin-inventory.properties"))) {
            boolean addingName = true;
            String coinPenceValueName = "";
            String coinAmountName = "";

            if (Character.isDigit(line.charAt(i)) || "".equals(line.charAt(i))) {
                if (addingName) {
                    if (Character.isDigit(line.charAt(i))) {
                        coinPenceValueName = coinPenceValueName + line.charAt(i);
                    } else if ("=".equals(line.charAt(i))) {
                        addingName = false;
                    }
                } else {
                    if (!System.getProperty("line.separator").equals(line.charAt(i))) {
                        coinAmountName = coinAmountName + line.charAt(i);
                    } else {
                        initialCoins.add(CoinUtils.convertPenceToCoin(Integer.parseInt(coinAmountName)));
                    }
                }
            } else
                throw new IOException("coin-inventory line read exception: lines MUST follow the format of [coinPenceValue]=[numberOfCoins], e.g. 100=11");
            i++;
        }
        return initialCoins;
    }

}
