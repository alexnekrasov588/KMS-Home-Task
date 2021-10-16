package com.kms.task;

import com.kms.task.service.GetCashService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GetCashTests {

    @Autowired
    GetCashService getCashService;

    @Test
    void getCurrency_Success() {
        String input = "- RUR 200";
        String res = getCashService.getCurrency(input);
        assertEquals(res, "RUR");
    }

    @Test
    void getAmount_Success() {
        String input = "- RUR 200";
        int res = getCashService.getAmount(input);
        assertEquals(res, 200);
    }
}