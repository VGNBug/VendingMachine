package com.pawsey.vendingmachine.component;

import com.pawsey.vendingmachine.model.Coin;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CoinInventoryProcessorTest {

    private CoinInventoryProcessor coinInventoryProcessor;

    @Before
    public void setup() {
        coinInventoryProcessor = new CoinInventoryProcessor("src/test/resources/coin-inventory.properties");
    }

    @Test
    public void testGetInventory() throws IOException {
        List<Coin> actual = coinInventoryProcessor.getInventory();
        List<Coin> expected = setupExpectedCoinList();

        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());

        for (int i = 0; i <= expected.size() - 1; i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    /**
     * This corresponds to the test version of coin-inventory.properties, at
     * <a href="./src/test/resources/coin-inventory.properties">src/test/resources/coin-inventory.properties</a>.
     *
     * @return A list of coins corresponding to src/test/resources/coin-inventory.properties
     */
    private List<Coin> setupExpectedCoinList() {
        List<Coin> output = new ArrayList<>();

        for (int i = 1; i <= 11; i++) {
            output.add(Coin.ONE_POUND);
        }
        for (int j = 1; j <= 24; j++) {
            output.add(Coin.FIFTY_PENCE);
        }
        for (int l = 1; l <= 0; l++) {
            output.add(Coin.TWENTY_PENCE);
        }
        for (int m = 1; m <= 99; m++) {
            output.add(Coin.TEN_PENCE);
        }
        for (int n = 1; n <= 200; n++) {
            output.add(Coin.FIVE_PENCE);
        }
        for (int o = 1; o <= 11; o++) {
            output.add(Coin.TWO_PENCE);
        }
        for (int p = 1; p <= 23; p++) {
            output.add(Coin.ONE_PENNY);
        }

        return output;
    }
}
