package com.pawsey.vendingmachine.component;

import com.pawsey.vendingmachine.model.Coin;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CoinInventoryProcessorTest extends BaseComponentTest {

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

}
