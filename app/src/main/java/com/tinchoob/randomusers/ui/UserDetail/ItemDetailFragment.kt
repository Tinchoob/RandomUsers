package com.tinchoob.randomusers.ui.UserDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tinchoob.randomusers.R
import kotlinx.android.synthetic.main.item_detail.view.*

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class ItemDetailFragment : Fragment(),UserDetailContract.View {

    override lateinit var presenter: UserDetailContract.Presenter
    lateinit var userName : String
    lateinit var fullName : String
    lateinit var userEmail : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = UserDetailPresenter(this)

        arguments?.let {
            if (it.containsKey(USER_FULL_NAME)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
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

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val USER_EMAIL = "user_email"
        const val USER_FULL_NAME = "user_full_name"
        const val USER_USERNAME = "user_username"
        const val USER_IMAGE = "image_path"
    }
}
