package com.tinchoob.randomusers.ui.Home

import android.util.Log
import com.tinchoob.randomusers.data.remote.RandomUsersApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

class UserListPresenter(val userListView: UserListContract.View) : UserListContract.Presenter {

    init {
        userListView.presenter = this
    }

    var service = RandomUsersApiService.create()

    override fun getUsers() {
        GlobalScope.launch(Dispatchers.Main) {
            val request = service.getUsers(50)
            try {
                val response = request.await()
                userListView.setUsers(response)
            } catch (e: HttpException) {
                e.stackTrace
            } catch (e: Throwable) {
                e.stackTrace
            }
        }
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}