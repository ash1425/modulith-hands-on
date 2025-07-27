package com.ashay.learnings.shop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ShopApplicationTests {

    @Test
    void contextLoads() {
        //checks if application context is happy
    }

    @Test
    void verifyModulith() {
        ApplicationModules applicationModules = ApplicationModules.of(ShopApplication.class);
        applicationModules.verify();
    }

    @Test
    void architecture() {
        ApplicationModules applicationModules = ApplicationModules.of(ShopApplication.class);
        new Documenter(applicationModules).writeDocumentation();
    }

}
