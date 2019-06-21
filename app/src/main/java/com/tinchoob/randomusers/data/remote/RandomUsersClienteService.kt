package com.tinchoob.randomusers.data.remote

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

open class RandomUsersClienteService{

    var service = RandomUsersApiService.create()

    fun getUsers (){
        GlobalScope.launch(Dispatchers.Main) {
            val request = service.getUsers(50)
            try {
                val response = request.await()
                Log.d("lala",response.toString())
            } catch (e: HttpException) {
                e.stackTrace
            } catch (e: Throwable) {
                e.stackTrace
            }
        }

    }
}