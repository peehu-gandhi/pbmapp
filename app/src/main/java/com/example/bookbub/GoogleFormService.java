package com.example.bookbub;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

// Create a Retrofit service interface with a POST request method to the Google Apps Script URL.
interface GoogleFormService {
    @POST("/forms/d/e/1FAIpQLScbBO_8aEe6FlBqH36uETmDzjkbuV7dm5VPBj3bEZNqvBY8DA")
    Call<Void> sendFormData(
            @Field("entry.1239163370") String name,
            @Field("entry.200051395") String email
            // if you have more questions you can add more here
    );}

// Create a FormData class to represent the data you want to submit.
class FormData {
    String parameter1;
    String parameter2;

    public FormData(String parameter1, String parameter2) {
        this.parameter1 = parameter1;
        this.parameter2 = parameter2;
    }
};
