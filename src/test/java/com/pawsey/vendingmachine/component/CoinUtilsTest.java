package com.pawsey.vendingmachine.component;

import com.pawsey.vendingmachine.model.Coin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoinUtilsTest {

    private CoinUtils coinUtils;

    @Before
    public void setup() {
        coinUtils = new CoinUtils();
    }

    @Test
    public void testGetCoinFor100PenceShouldReturnCoin() {
        testShouldReturnCoin(100, Coin.ONE_POUND);
    }

    @Test
    public void testGetCoinFor50PenceShouldReturnCoin() {
        testShouldReturnCoin(50, Coin.FIFTY_PENCE);
    }

    @Test
    public void testGetCoinFor20PenceShouldReturnCoin() {
        testShouldReturnCoin(20, Coin.TWENTY_PENCE);
    }

    @Test
    public void testGetCoinFor10PenceShouldReturnCoin() {
        testShouldReturnCoin(10, Coin.TEN_PENCE);
    }

    @Test
    public void testGetCoinFor5PenceShouldReturnCoin() {
        testShouldReturnCoin(5, Coin.FIVE_PENCE);
    }

    @Test
    public void testGetCoinFor2PenceShouldReturnCoin() {
        testShouldReturnCoin(2, Coin.TWO_PENCE);
    }

    @Test
    public void testGetCoinFor1PennyShouldReturnCoin() {
        testShouldReturnCoin(1, Coin.ONE_PENNY);
    }

    @Test
    public void testGetCoinFor0PenceShouldFail() {
        assertNull(coinUtils.convertPenceToCoin(0));
    }

    @Test
    public void testGetCoinFor99PenceShouldNoCoin() {
        assertNull(coinUtils.convertPenceToCoin(99));
    }

    @Test
    public void testGetCoinFor45PenceShouldReturnNoCoin() {
        assertNull(coinUtils.convertPenceToCoin(45));
    }

    @Test
    public void testUpdatePence100PenceShouldLeave0Pence() {
        assertEquals(0, coinUtils.updatePence(100));
    }

    @Test
    public void testUpdatePenceSubtract99ShouldLeave49Pence() {
        assertEquals(49, coinUtils.updatePence(99));
    }

    @Test
    public void testUpdatePenceSubtract50ShouldLeave0Pence() {
        assertEquals(0, coinUtils.updatePence(50));
    }

    @Test
    public void testUpdatePenceSubtract49ShouldLeave29Pence() {
        assertEquals(29, coinUtils.updatePence(49));
    }

    @Test
    public void testUpdatePenceSubtract20ShouldLeave0Pence() {
        assertEquals(0, coinUtils.updatePence(20));
    }

    @Test
    public void testUpdatePenceSubtract19ShouldLeave9Pence() {
        assertEquals(9, coinUtils.updatePence(19));
    }

    @Test
    public void testUpdatePenceSubtract10ShouldLeave0Pence() {
        assertEquals(0, coinUtils.updatePence(10));
    }

    @Test
    public void testUpdatePenceSubtract9ShouldLeave4Pence() {
        assertEquals(4, coinUtils.updatePence(9));
    }

    @Test
    public void testUpdatePenceSubtract5ShouldLeave0Pence() {
        assertEquals(0, coinUtils.updatePence(5));
    }

    @Test
    public void testUpdatePenceSubtract4ShouldLeave2Pence() {
        assertEquals(2, coinUtils.updatePence(4));
    }

    @Test
    public void testUpdatePenceSubtract2ShouldLeave0Pence() {
        assertEquals(0, coinUtils.updatePence(2));
    }

    @Test
    public void testUpdatePenceSubtract1ShouldLeave0Pence() {
        assertEquals(0, coinUtils.updatePence(1));
    }

    private void testShouldReturnCoin(int pence, Coin fiftyPence) {
        Coin actual = coinUtils.convertPenceToCoin(pence);

        assertNotNull(actual);
        assertEquals(fiftyPence, actual);
    }

}
