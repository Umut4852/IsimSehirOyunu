package com.example.isimsehiroyunu;

import retrofit2.Call;
import retrofit2.http.GET;

public interface timeAPI {
    @GET("Europe/Istanbul")
    Call<timeTurkey> getTime();
}

