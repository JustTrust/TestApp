package com.test.testapp.network

import com.test.testapp.network.models.Response
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by a.belichenko on 20.05.2019.
 * mail: a.belichenko@gmail.com
 */
interface Api {

    @GET("api.json?rss_url=https://mars.nasa.gov/rss/blogs.cfm")
    fun getSitesList(): Single<Response>
}