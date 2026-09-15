package com.example.mobileappclient.DI

import com.example.mobileappclient.Data.RoomBD.Remote.HeroApi
import com.example.mobileappclient.utils.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


//Module tell hilt how to provide retrofit instance
@Module
@InstallIn(Singleton::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS) //This is the maximum time the app will wait to establish a connection to the server.
            .connectTimeout(15, TimeUnit.SECONDS) //This is the maximum time the app will wait for a response after the connection has been established.
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient) : Retrofit {

        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    //function to provide retrofit instance
    @Provides
    @Singleton
    fun provideHeroApi(retrofit: Retrofit) : HeroApi {
        return retrofit.create(HeroApi::class.java)
    }

}