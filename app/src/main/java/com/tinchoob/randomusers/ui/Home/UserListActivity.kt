package com.tinchoob.randomusers.ui.Home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tinchoob.randomusers.R
import com.tinchoob.randomusers.data.RandomUsersRepository
import com.tinchoob.randomusers.data.model.Result
import com.tinchoob.randomusers.data.model.User
import com.tinchoob.randomusers.ui.UserDetail.UserDetailActivity
import com.tinchoob.randomusers.ui.UserDetail.UserDetailFragment
import com.tinchoob.randomusers.utils.Constants.Companion.USER_EMAIL
import com.tinchoob.randomusers.utils.Constants.Companion.USER_FULL_NAME
import com.tinchoob.randomusers.utils.Constants.Companion.USER_IMAGE
import com.tinchoob.randomusers.utils.Constants.Companion.USER_USERNAME

import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View


class UserListActivity : AppCompatActivity(), UserListContract.View, OnUserSelectedListener {

    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: UsersAdapter
    override lateinit var presenter: UserListContract.Presenter
    private var twoPane: Boolean = false
    private var loading: Boolean = false

    override fun onUserSelected(item: Result) {
        if (twoPane) {
            val fragment = UserDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(USER_FULL_NAME, item.name?.first)
                }
            }
            this.supportFragmentManager
                .beginTransaction()
                .replace(R.id.item_detail_container, fragment)
                .commit()
        } else {
            val intent = Intent(this, UserDetailActivity::class.java).apply {
                putExtra(USER_FULL_NAME, String.format("%s %s", item.name?.last, item.name?.first))
                putExtra(USER_IMAGE, item.picture?.large)
                putExtra(USER_EMAIL, item.email)
                putExtra(USER_USERNAME, item.login?.username)
            }
            startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        presenter = UserListPresenter(this, RandomUsersRepository())
        mLayoutManager = LinearLayoutManager(this)

        setSupportActionBar(toolbar)
        toolbar.title = title

        if (item_detail_container != null) {
            twoPane = true
        }

        presenter.getUsers()

    }


    override fun setUsers(user: User) {

        setupRecyclerView(item_list, user)
    }

    override fun setupRecyclerView(recyclerView: RecyclerView, user: User) {
        mAdapter = UsersAdapter(
            this,
            user.results,
            twoPane,
            this
        )
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = mLayoutManager

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0)
                //check for scroll down
                {
                    val shouldLoadMoreUsers =
                        mLayoutManager.itemCount <= mLayoutManager.findLastVisibleItemPosition() + 2

                    if (!loading && shouldLoadMoreUsers) {
                        showProgressView()
                        loading = true
                        presenter.fetchNewUsers()
                    }
                }
            }
        })
    }


    override fun showError() {
        Toast.makeText(this, R.string.general_error_message, Toast.LENGTH_SHORT).show()
    }

    override fun showErrorFetchingUsers() {
        loading = false
        hideProgressView()
        Toast.makeText(this, R.string.general_error_message, Toast.LENGTH_SHORT).show()
    }

    override fun newUsersReceived(user: User) {
        loading = false
        hideProgressView()
        mAdapter.addMoreUsers(user.results!!)
    }

    override fun showProgressView() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressView() {
        progressBar.visibility = View.INVISIBLE
    }

}
