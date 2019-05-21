package com.test.testapp.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.test.testapp.BuildConfig
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by a.belichenko on 20.05.2019.
 * mail: a.belichenko@gmail.com
 */
class NetworkProvider {

    fun getApi(): Api = provideBaseRetrofit().create(Api::class.java)

    private fun provideBaseRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(getHttpUrl())
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(getGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .build()
    }

    private fun getHttpUrl() = HttpUrl.parse(BuildConfig.API_BASE_URL)!!

    private fun getOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    private fun getGson(): Gson = GsonBuilder()
        .serializeNulls()
        .create()

    companion object {
        val instance: NetworkProvider = NetworkProvider()
    }
}