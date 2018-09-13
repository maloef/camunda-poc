/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2018.
 */
package ch.sbb.dks.camundapoc.delegate.einheitenberechnung;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author ue85191 (Markus Loeffler)
 */
public class WagenfaktorDelegateTest {

    private WagenfaktorDelegate testee = new WagenfaktorDelegate();

    @Test
    public void testFormat() {
        String from = "2018-08-22T22:30:17";
        String to = "2018-08-22T23:31:18";

        assertEquals(61, testee.minutesBetween(from, to));
    }
}
