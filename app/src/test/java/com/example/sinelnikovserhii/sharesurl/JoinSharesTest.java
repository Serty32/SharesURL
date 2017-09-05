package com.example.sinelnikovserhii.sharesurl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JoinSharesTest {

    ShareService service;

    @Before
    public void setup() {
        service = new ShareService();
    }

    @Test
    public void joinIsCorrect() {
        assertEquals(
                "select * from yahoo.finance.quotes where symbol in (\"YHOO\",\"AAPL\",\"GOOG\",\"GPRO\")",
                service.formQuery(new String[] {"YHOO", "AAPL", "GOOG", "GPRO"})
        );
    }
}