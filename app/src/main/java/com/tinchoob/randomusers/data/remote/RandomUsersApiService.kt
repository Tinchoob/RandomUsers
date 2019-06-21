package com.tinchoob.randomusers.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tinchoob.randomusers.BuildConfig
import com.tinchoob.randomusers.data.model.User
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUsersApiService {

    @GET(".")
    fun getUsers(@Query("results") usersCount: Int ) : Deferred<User>


    companion object {
        fun create(): RandomUsersApiService {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl(BuildConfig.BASE_URL)
                .build()

            return retrofit.create(RandomUsersApiService::class.java)
        }
    }
}

