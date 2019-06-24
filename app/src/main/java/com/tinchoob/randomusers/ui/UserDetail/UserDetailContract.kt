package com.tinchoob.randomusers.ui.UserDetail

import com.tinchoob.randomusers.ui.BasePresenter
import com.tinchoob.randomusers.ui.BaseView

interface UserDetailContract {

    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter

}