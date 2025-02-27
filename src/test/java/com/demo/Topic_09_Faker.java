package com.demo;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import testdata.PaymentObject;

import java.util.Locale;

public class Topic_09_Faker {
    @Test
    public void TC01(){
        System.out.println("HelloWorld");
        Faker faker = new Faker(new Locale("en-US"));
        System.out.println(faker.address().city());
        System.out.println(faker.address().zipCode());
        System.out.println(faker.address().firstName());
        System.out.println(faker.address().lastName());
        System.out.println(faker.address().streetAddress());
        System.out.println(faker.internet().emailAddress());
        System.out.println(faker.phoneNumber().cellPhone());

        System.out.println(PaymentObject.BillingAddress.COUNTRY);
    }
}
