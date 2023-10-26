package test;


import by.clevertec.Main;
import by.clevertec.util.Util;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamTest {
    public static JsonNode testData;


    @BeforeAll
    public static void initData() {
    testData = Util.getTestData();
    }

    @Test
    public void testFourthTask() {
        assertEquals(Long.parseLong(testData.get("task_four_count").toString()), Main.task4());
    }
}
