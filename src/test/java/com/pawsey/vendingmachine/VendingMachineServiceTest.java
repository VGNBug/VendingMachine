package com.pawsey.vendingmachine;

import com.pawsey.vendingmachine.service.VendingMachineService;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Fail.fail;

public class VendingMachineServiceTest {

    private VendingMachineService vendingMachineService;

    @Before
    public void setup() {
        vendingMachineService = new VendingMachineService();
    }

    @Test
    public void testGetOptimalChangeFor() {
        fail("Not yet implemented");
    }
}
