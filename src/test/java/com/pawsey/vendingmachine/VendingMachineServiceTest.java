package com.pawsey.vendingmachine;

import com.pawsey.vendingmachine.model.Coin;
import com.pawsey.vendingmachine.service.VendingMachineService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.*;

public class VendingMachineServiceTest {

    private VendingMachineService vendingMachineService;

    @Before
    public void setup() {
        vendingMachineService = new VendingMachineService();
    }

    @Test
    public void onePoundShouldReturnSingleOnePoundCoin() {
        ArrayList<Coin> expected = new ArrayList<>();
        expected.add(Coin.ONE_POUND);

        Collection<Coin> actual = vendingMachineService.getOptimalChangeFor(100);

        coinCollectionAssertions(expected, actual, expected.size());
    }

    @Test
    public void seventyPenceShouldReturnOneFiftyAndOneTwenty() {
        ArrayList<Coin> expected = new ArrayList<>();
        expected.add(Coin.FIFTY_PENCE);
        expected.add(Coin.TWENTY_PENCE);

        Collection<Coin> actual = vendingMachineService.getOptimalChangeFor(70);

        coinCollectionAssertions(expected, actual, expected.size());
    }

    private void coinCollectionAssertions(ArrayList<Coin> expected, Collection<Coin> actual, int collectionSize) {
        assertNotNull(actual);
        assertTrue(actual.size() == collectionSize);

        int i = 0;
        for (Iterator actualIterator = actual.iterator(); actualIterator.hasNext();) {
            Coin actualCoin = (Coin) actualIterator.next();
            assertEquals(expected.get(i), actualCoin);
            i++;
        }
    }

}
