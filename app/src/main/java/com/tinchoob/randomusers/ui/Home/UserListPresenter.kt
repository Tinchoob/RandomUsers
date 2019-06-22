package com.tinchoob.randomusers.ui.Home

import android.util.Log
import com.tinchoob.randomusers.data.RandomUsersRepository
import com.tinchoob.randomusers.data.UsersDataSource
import com.tinchoob.randomusers.data.model.User
import com.tinchoob.randomusers.data.remote.RandomUsersApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

class UserListPresenter(val userListView: UserListContract.View, val randomUsersRepository: RandomUsersRepository) :
    UserListContract.Presenter {

    init {
        userListView.presenter = this
    }

    override fun getUsers() {
        randomUsersRepository.getUsers(object : UsersDataSource.GetUsersCallback {
            override fun onUsersLoaded(user: User) {
                userListView.setUsers(user)
            }

            override fun onFailure() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


        })
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}