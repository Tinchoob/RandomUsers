package com.tinchoob.randomusers.ui.Home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tinchoob.randomusers.R
import com.tinchoob.randomusers.data.RandomUsersRepository
import com.tinchoob.randomusers.data.model.Result
import com.tinchoob.randomusers.data.model.User
import com.tinchoob.randomusers.ui.UserDetail.ItemDetailActivity
import com.tinchoob.randomusers.ui.UserDetail.ItemDetailFragment
import com.tinchoob.randomusers.utils.Constants.Companion.USER_EMAIL
import com.tinchoob.randomusers.utils.Constants.Companion.USER_FULL_NAME
import com.tinchoob.randomusers.utils.Constants.Companion.USER_IMAGE
import com.tinchoob.randomusers.utils.Constants.Companion.USER_USERNAME

import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity(), UserListContract.View,OnUserSelectedListener {

    override fun onUserSelected(item: Result){
        if (twoPane) {
            val fragment = ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(USER_FULL_NAME, item.name?.first)
                }
            }
            this.supportFragmentManager
                .beginTransaction()
                .replace(R.id.item_detail_container, fragment)
                .commit()
        } else {
            val intent = Intent(this, ItemDetailActivity::class.java).apply {
                putExtra(USER_FULL_NAME, String.format("%s %s",item.name?.last,item.name?.first))
                putExtra(USER_IMAGE,item.picture?.large)
                putExtra(USER_EMAIL,item.email)
                putExtra(USER_USERNAME,item.login?.username)
            }
            startActivity(intent)
        }
    }


    override lateinit var presenter: UserListContract.Presenter

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        presenter = UserListPresenter(this, RandomUsersRepository())

        setSupportActionBar(toolbar)
        toolbar.title = title

        if (item_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        presenter.getUsers()

    }


    override fun setUsers(user: User) {

        setupRecyclerView(item_list,user)
    }

    private fun setupRecyclerView(recyclerView: androidx.recyclerview.widget.RecyclerView,user : User) {
        recyclerView.adapter = UsersAdapter(
            this,
            user.results,
            twoPane,
            this
        )
    }

    override fun showError() {
        Toast.makeText(this,R.string.general_error_message,Toast.LENGTH_SHORT).show()
    }


}
