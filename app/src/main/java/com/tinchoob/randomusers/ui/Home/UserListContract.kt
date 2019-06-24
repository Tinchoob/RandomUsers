package com.tinchoob.randomusers.ui.Home

import com.tinchoob.randomusers.data.model.User
import com.tinchoob.randomusers.ui.BasePresenter
import com.tinchoob.randomusers.ui.BaseView

interface UserListContract {

    interface View : BaseView<Presenter> {
        fun setUsers(user : User)
        fun showError()
    }

    interface Presenter : BasePresenter {
        fun getUsers()
    }

}