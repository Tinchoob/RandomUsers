package com.tinchoob.randomusers.ui.Home

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.tinchoob.randomusers.R
import com.tinchoob.randomusers.data.RandomUsersRepository
import com.tinchoob.randomusers.data.model.User

import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import androidx.appcompat.app.ActionBarDrawerToggle
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.tinchoob.randomusers.ui.Favourites


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
    private lateinit var mDrawerLayout: DrawerLayout

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        presenter = UserListPresenter(this, RandomUsersRepository())
        mDrawerLayout = drawer_nav
        setSupportActionBar(toolbar)
        toolbar.title = title

        val action = ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close)
        mDrawerLayout.addDrawerListener(action)
        action.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        nv.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            mDrawerLayout.closeDrawers()

            // Handle navigation view item clicks here.
            when (menuItem.itemId) {

                R.id.navigation_home -> {
                    Toast.makeText(this, "Home", Toast.LENGTH_LONG).show()
                }
                R.id.navigation_favourites -> {
                    val intent = Intent(this, Favourites::class.java).apply {

                    }
                  startActivity(intent)
                }
            }
            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here

            true
        }

//        if (item_detail_container != null) {
//            // The detail container view will be present only in the
//            // large-screen layouts (res/values-w900dp).
//            // If this view is present, then the
//            // activity should be in two-pane mode.
//            twoPane = true
//        }

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


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                mDrawerLayout.openDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}
