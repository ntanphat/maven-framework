package com.demo;

import net.datafaker.Faker;
import org.testng.annotations.Test;

import java.util.Locale;

public class Topic_09_Faker_Net {
    @Test
    public void TC01(){
        System.out.println("HelloWorld");
        Faker faker = new Faker(new Locale("en-US"));
        System.out.println(faker.name().firstName());
        System.out.println(faker.name().lastName());
    }
}
