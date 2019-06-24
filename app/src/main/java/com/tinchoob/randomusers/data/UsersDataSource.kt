package com.tinchoob.randomusers.data

import com.tinchoob.randomusers.data.model.User

interface UsersDataSource {

    interface GetUsersCallback {

        fun onUsersLoaded(user: User)

        fun onFailure()
    }


    fun getUsers(callback: GetUsersCallback)

    fun getMoreUsers(callback: GetUsersCallback)


}