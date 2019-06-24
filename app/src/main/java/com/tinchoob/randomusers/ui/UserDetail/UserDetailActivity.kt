package com.tinchoob.randomusers.ui.UserDetail

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.tinchoob.randomusers.R
import kotlinx.android.synthetic.main.activity_item_detail.*
import com.bumptech.glide.request.RequestOptions
import com.tinchoob.randomusers.utils.Constants.Companion.USER_EMAIL
import com.tinchoob.randomusers.utils.Constants.Companion.USER_FULL_NAME
import com.tinchoob.randomusers.utils.Constants.Companion.USER_IMAGE
import com.tinchoob.randomusers.utils.Constants.Companion.USER_USERNAME


class UserDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(detail_toolbar)

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {

            val fragment = UserDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(
                        USER_FULL_NAME,
                        intent.getStringExtra(USER_FULL_NAME)
                    )
                    putString(USER_EMAIL,intent.getStringExtra(USER_EMAIL))
                    putString(USER_USERNAME,intent.getStringExtra(USER_USERNAME))
                }
            }


            supportActionBar?.setDisplayShowTitleEnabled(false)

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }


    }

    override fun onResume() {
        super.onResume()

        val options = RequestOptions()
        options.fitCenter()


        Glide.with(this)
            .load(intent.getStringExtra(USER_IMAGE))
            .apply(options)
            .into(user_image)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
               finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
