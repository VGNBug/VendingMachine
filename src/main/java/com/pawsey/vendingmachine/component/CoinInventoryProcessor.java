package com.pawsey.vendingmachine.component;

import com.pawsey.vendingmachine.model.Coin;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CoinInventoryProcessor {

    private final Log LOG = LogFactory.getLog(this.getClass());
    private CoinUtils coinUtils;
    private String coinInventoryPath = "src/main/resources/coin-inventory.properties";

    public CoinInventoryProcessor() {
        coinUtils = new CoinUtils();
    }

    /**
     * @param coinInventoryPath over-writes {@link com.pawsey.vendingmachine.component.CoinInventoryProcessor#coinInventoryPath}
     *                          for testing purposes.
     */
    public CoinInventoryProcessor(String coinInventoryPath) {
        coinUtils = new CoinUtils();
        this.coinInventoryPath = coinInventoryPath;
    }

    public List<Coin> getInventory() throws IOException {

        List<Coin> initialCoins = new ArrayList<>();

        readFile(initialCoins);
        return initialCoins;
    }

    private void readFile(List<Coin> initialCoins) throws IOException {
        for (String line : Files.readAllLines(Paths.get(coinInventoryPath))) {
            boolean writingName = true;

            LineReader lineReader = new LineReader(line, writingName, new String(), new String()).invoke();
            String valueOfCoin = lineReader.getValueOfCoin();
            String numberOfCoins = lineReader.getNumberOfCoins();
            addCoinsToList(initialCoins, Integer.parseInt(valueOfCoin), Integer.parseInt(numberOfCoins));
        }
    }

    private void addCoinsToList(List<Coin> coins, int valueOfCoin, int numberOfCoins) {
        LOG.info("Adding " + numberOfCoins + " of " + valueOfCoin + " to collection");
        for (int j = 0; j <= numberOfCoins - 1; j++) {
            coins.add(coinUtils.convertPenceToCoin(valueOfCoin));
        }
    }

    private class LineReader {
        private String line;
        private boolean writingName;
        private String valueOfCoin;
        private String numberOfCoins;

        public LineReader(String line, boolean writingName, String valueOfCoin, String numberOfCoins) {
            this.line = line;
            this.writingName = writingName;
            this.valueOfCoin = valueOfCoin;
            this.numberOfCoins = numberOfCoins;
        }

        public String getValueOfCoin() {
            return valueOfCoin;
        }

        public String getNumberOfCoins() {
            return numberOfCoins;
        }

        public LineReader invoke() throws IOException {
            for (char character : line.toCharArray()) {
                IOException ioe = new IOException("coin-inventory line read exception: lines MUST follow the format of [coinPenceValue]=[numberOfCoins], e.g. 100=11\nFailing line was " + line);
                if (writingName) {
                    if (Character.isDigit(character)) {
                        valueOfCoin += character;
                    } else if ('=' == character) {
                        writingName = false;
                    } else throw ioe;
                } else {
                    if (Character.isDigit(character)) {
                        numberOfCoins += character;
                    } else
                        throw ioe;
                }
            }
            return this;
        }
    }
}
