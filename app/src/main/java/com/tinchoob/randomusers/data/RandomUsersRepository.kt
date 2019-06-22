package com.tinchoob.randomusers.data

import com.tinchoob.randomusers.data.remote.RandomUsersApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

open class RandomUsersRepository : UsersDataSource{

    private val service = RandomUsersApiService.create()


    override fun getUsers(callback: UsersDataSource.GetUsersCallback) {
        GlobalScope.launch(Dispatchers.Main) {
            val request = service.getUsers(50)
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