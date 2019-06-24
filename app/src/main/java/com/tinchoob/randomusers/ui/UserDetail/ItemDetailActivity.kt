package com.tinchoob.randomusers.ui.UserDetail

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.tinchoob.randomusers.R
import com.tinchoob.randomusers.ui.Home.ItemListActivity
import kotlinx.android.synthetic.main.activity_item_detail.*
import com.bumptech.glide.request.RequestOptions
import com.tinchoob.randomusers.utils.Constants.Companion.USER_EMAIL
import com.tinchoob.randomusers.utils.Constants.Companion.USER_FULL_NAME
import com.tinchoob.randomusers.utils.Constants.Companion.USER_IMAGE
import com.tinchoob.randomusers.utils.Constants.Companion.USER_USERNAME


/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [ItemListActivity].
 */
class ItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(detail_toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        if (savedInstanceState == null) {

            val fragment = ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(
                        USER_FULL_NAME,
                        intent.getStringExtra(USER_FULL_NAME)
                    )
                    putString(USER_EMAIL,intent.getStringExtra(USER_EMAIL))
                    putString(USER_USERNAME,intent.getStringExtra(USER_USERNAME))
                }
            }

            val options = RequestOptions()
            options.fitCenter()

            Glide.with(this)
                .load(intent.getStringExtra(USER_IMAGE))
                .apply(options)
                .into(user_image)


            supportActionBar?.setDisplayShowTitleEnabled(false)

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }


    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back

               finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
