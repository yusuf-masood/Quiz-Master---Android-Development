package com.example.yusuffinalproject.apiquestions

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface TriviaApiService {
    @GET("questions")
    suspend fun getQuestions(): List<Question>
}


