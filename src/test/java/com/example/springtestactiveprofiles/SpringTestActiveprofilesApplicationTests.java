package com.example.springtestactiveprofiles;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@ActiveProfiles("test")
//@TestPropertySource("/application-test.properties")
//@TestPropertySource("/test.properties")  // IDEA-272397 - the props set here are not navigable
class SpringTestActiveprofilesApplicationTests {
    @Autowired
    ApplicationContext ctx;

    // https://youtrack.jetbrains.com/issue/IDEA-308562
    // the folded value should display the values from application-test.properties
    // since @ActiveProfiles is present
    
    @Value("${foo.bar}")
    String bar;
    @Value("${foo.buzz}")
    String buzz;

    @Test
    void checkThatProfileSpecificComponentIsHere() {

        assertTrue(Arrays.asList(ctx.getBeanDefinitionNames()).contains("testComponent"));
    }

    @Test
    void checkCorrectBarProperty() {
        assertEquals(bar,"bar from test/application-test.properties");
    }
    @Test
    void checkCorrectBuzzProperty() {
        assertEquals(buzz,"buzz from test/application-test.properties");
    }
}
