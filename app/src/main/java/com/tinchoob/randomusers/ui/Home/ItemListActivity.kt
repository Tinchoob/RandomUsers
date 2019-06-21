package com.tinchoob.randomusers.ui.Home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.tinchoob.randomusers.R
import com.tinchoob.randomusers.data.model.User

import com.tinchoob.randomusers.dummy.DummyContent
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
class ItemListActivity : AppCompatActivity(), UserListContract.View {


    override lateinit var presenter: UserListContract.Presenter

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        presenter = UserListPresenter(this)

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

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
            twoPane
        )
    }


}
