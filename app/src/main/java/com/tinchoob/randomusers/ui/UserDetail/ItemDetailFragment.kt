package com.tinchoob.randomusers.ui.UserDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tinchoob.randomusers.R
import com.tinchoob.randomusers.utils.Constants.Companion.USER_EMAIL
import com.tinchoob.randomusers.utils.Constants.Companion.USER_FULL_NAME
import com.tinchoob.randomusers.utils.Constants.Companion.USER_USERNAME
import kotlinx.android.synthetic.main.item_detail.view.*


class ItemDetailFragment : Fragment(),UserDetailContract.View {

    override lateinit var presenter: UserDetailContract.Presenter
    private lateinit var userName : String
    private lateinit var fullName : String
    private lateinit var userEmail : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = UserDetailPresenter(this)

        arguments?.let {
            if (it.containsKey(USER_FULL_NAME)) {
                fullName = it.getString(USER_FULL_NAME)
                userEmail = it.getString(USER_EMAIL)
                userName = it.getString(USER_USERNAME)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        rootView.user_name.text = fullName
        rootView.user_email.text = userEmail
        rootView.user_username.text = userName

        return rootView
    }

}
