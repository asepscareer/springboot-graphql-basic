package com.graphqljava.tutorial;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

public class SimpleTest {

    @Test
    public void testSame() {
        String data1 = "0122";
        String data2 = "0123";

        Assertions.assertEquals(data1, data2);
    }

}