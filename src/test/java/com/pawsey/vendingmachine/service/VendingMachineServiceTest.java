package com.pawsey.vendingmachine.service;

import com.pawsey.vendingmachine.model.Coin;
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

        coinCollectionSuccessAssertions(expected, actual, expected.size());
    }

    @Test
    public void seventyPenceShouldReturnOneFiftyAndOneTwenty() {
        ArrayList<Coin> expected = new ArrayList<>();
        expected.add(Coin.FIFTY_PENCE);
        expected.add(Coin.TWENTY_PENCE);

        Collection<Coin> actual = vendingMachineService.getOptimalChangeFor(70);

        coinCollectionSuccessAssertions(expected, actual, expected.size());
    }

    @Test
    public void ninetyNicePenceShouldReturnOneFiftyTwoTwentiesOneFiveAndTwoTwos() {
        ArrayList<Coin> expected = new ArrayList<>();
        expected.add(Coin.FIFTY_PENCE);
        expected.add(Coin.TWENTY_PENCE);
        expected.add(Coin.TWENTY_PENCE);
        expected.add(Coin.FIVE_PENCE);
        expected.add(Coin.TWO_PENCE);
        expected.add(Coin.TWO_PENCE);

        Collection<Coin> actual = vendingMachineService.getOptimalChangeFor(99);

        coinCollectionSuccessAssertions(expected, actual, expected.size());
    }

    @Test
    public void testGetChangeFor100ShouldReturnOneOnePound() {
        ArrayList<Coin> expected = new ArrayList<>();
        expected.add(Coin.ONE_POUND);

        coinCollectionSuccessAssertions(expected, vendingMachineService.getChangeFor(100), expected.size());
    }

    @Test
    public void testGetChangeFor100ShouldNotReturnTwoFiftyPennies() {
        ArrayList<Coin> expected = new ArrayList<>();
        expected.add(Coin.FIFTY_PENCE);
        expected.add(Coin.FIFTY_PENCE);

        coinCollectionFailureAssertions(expected, vendingMachineService.getChangeFor(100), expected.size());
    }

    @Test
    public void testGetChangeFor20ShouldNotReturnOneTwentyPenny() {
        ArrayList<Coin> expected = new ArrayList<>();
        expected.add(Coin.TWENTY_PENCE);

        coinCollectionFailureAssertions(expected, vendingMachineService.getChangeFor(20), expected.size());
    }

    @Test
    public void testGetChangeFor20ShouldReturnTwoTenPennies() {
        ArrayList<Coin> expected = new ArrayList<>();
        expected.add(Coin.TEN_PENCE);
        expected.add(Coin.TEN_PENCE);

        coinCollectionSuccessAssertions(expected, vendingMachineService.getChangeFor(20), expected.size());

    }

    private void coinCollectionSuccessAssertions(ArrayList<Coin> expected, Collection<Coin> actual, int collectionSize) {
        assertNotNull(actual);
        assertTrue(actual.size() == collectionSize);

        int i = 0;
        for (Iterator actualIterator = actual.iterator(); actualIterator.hasNext(); ) {
            Coin actualCoin = (Coin) actualIterator.next();
            assertEquals(expected.get(i), actualCoin);
            i++;
        }
    }

    private void coinCollectionFailureAssertions(ArrayList<Coin> expected, Collection<Coin> actual, int collectionSize) {
        assertNotNull(actual);
        assertFalse(actual.size() == collectionSize);
        assertNotEquals(expected, actual);
    }

}
