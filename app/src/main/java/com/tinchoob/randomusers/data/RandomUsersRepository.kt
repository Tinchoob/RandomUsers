package com.tinchoob.randomusers.data

import com.tinchoob.randomusers.data.remote.RandomUsersApiService
import com.tinchoob.randomusers.utils.Constants.Companion.DEFAULT_USERS_NUMBER_PER_PAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

open class RandomUsersRepository : UsersDataSource{

    private val service = RandomUsersApiService.create()
    private var seed : String? = ""
    private var page : Int = 1

    override fun getUsers(callback: UsersDataSource.GetUsersCallback) {
        GlobalScope.launch(Dispatchers.Main) {
            val request = service.getUsers(DEFAULT_USERS_NUMBER_PER_PAGE)
            try {
                val response = request.await()
                seed = response.info?.seed
                callback.onUsersLoaded(response)
            } catch (e: HttpException) {
               callback.onFailure()
            } catch (e: Throwable) {
               callback.onFailure()
            }
        }
    }


    override fun getMoreUsers(callback: UsersDataSource.GetUsersCallback) {
        page+=1
        GlobalScope.launch(Dispatchers.Main) {
            val request = service.getNewUsers(page,DEFAULT_USERS_NUMBER_PER_PAGE,seed)
            try {
                val response = request.await()
                callback.onUsersLoaded(response)
            } catch (e: HttpException) {
                callback.onFailure()
            } catch (e: Throwable) {
                callback.onFailure()
            }
        }
    }

}