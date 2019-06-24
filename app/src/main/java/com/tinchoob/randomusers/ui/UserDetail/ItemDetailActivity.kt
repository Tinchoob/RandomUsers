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

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(
                        ItemDetailFragment.USER_FULL_NAME,
                        intent.getStringExtra(ItemDetailFragment.USER_FULL_NAME)
                    )
                    putString(ItemDetailFragment.USER_EMAIL,intent.getStringExtra(ItemDetailFragment.USER_EMAIL))
                    putString(ItemDetailFragment.USER_USERNAME,intent.getStringExtra(ItemDetailFragment.USER_USERNAME))
                }
            }

            val options = RequestOptions()
            options.fitCenter()

            Glide.with(this)
                .load(intent.getStringExtra(ItemDetailFragment.USER_IMAGE))
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
