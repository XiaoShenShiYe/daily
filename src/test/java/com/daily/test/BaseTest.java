package com.daily.test;

import com.example.MyProjectApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MyProjectApplication.class)
public class BaseTest {

    private Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @Test
    public void test() {
        logger.info("hello world");
    }

}
