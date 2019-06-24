package com.tinchoob.randomusers.ui.Home


import com.tinchoob.randomusers.data.RandomUsersRepository
import com.tinchoob.randomusers.data.UsersDataSource
import com.tinchoob.randomusers.data.model.User


class UserListPresenter(val userListView: UserListContract.View, private val randomUsersRepository: RandomUsersRepository) :
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
             userListView.showError()
            }

        })
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}