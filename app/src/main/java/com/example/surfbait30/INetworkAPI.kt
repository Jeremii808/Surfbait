package com.example.surfbait30

import com.example.surfbait30.data.Post
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  API key provided by Magic Seaweed surf forecast website
 *  For information visit https://magicseaweed.com/developer/api
 */
const val API_KEY: String = BuildConfig.MSW_API_KEY

interface INetworkAPI {
    @GET("api/$API_KEY/forecast/")
    fun getAllPosts(
        @Query("spot_id") location: String,
        @Query("units") country: String = "us",
        @Query("fields") data: String = "timestamp," +
                "swell.minBreakingHeight," +
                "swell.maxBreakingHeight," +
                "wind.speed," +
                "wind.compassDirection," +
                "condition.temperature"
    ): Observable<List<Post>>

    // A companion object with a function that will initiate Retrofit
    companion object {
        fun create(): INetworkAPI {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://magicseaweed.com/").build()

            return retrofit.create(INetworkAPI::class.java)
        }
    }
}