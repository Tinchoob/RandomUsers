package com.tinchoob.randomusers.ui.UserDetail

class UserDetailPresenter(val userDetailView: UserDetailContract.View) : UserDetailContract.Presenter {

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    init {
        userDetailView.presenter = this
    }

}