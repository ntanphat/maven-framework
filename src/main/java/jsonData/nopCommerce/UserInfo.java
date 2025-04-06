package jsonData.nopCommerce;


import commons.GlobalConstants;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import io.qameta.allure.internal.shadowed.jackson.databind.DeserializationFeature;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;

import java.io.File;

public class UserInfo {
    public static UserInfo getUserInfo(String filename){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            return mapper.readValue(new File(GlobalConstants.RESOURCE_PATH + filename), UserInfo .class);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("visa")
    private Visa visa;

    static class Visa{
        @JsonProperty("number")
        String number;
    }

    public String getNumber(){
        return visa.number;
    }
}
