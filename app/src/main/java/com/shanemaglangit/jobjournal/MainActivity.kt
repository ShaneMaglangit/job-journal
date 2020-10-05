package com.shanemaglangit.jobjournal

import android.app.Activity
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateUtils
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.shanemaglangit.jobjournal.data.AppDatabase
import com.shanemaglangit.jobjournal.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout and create the view binding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Attach the navigation controller to the botton navigation view
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNav, navHost.navController)
    }

    override fun onStart() {
        super.onStart()
    }


    /**
     * Hides the soft keyboard whenever a touch event is detected
     * outside of edit text views.
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val view = currentFocus
            if (view != null && view is EditText) {
                val r = Rect()
                view.getGlobalVisibleRect(r)
                val rawX = ev.rawX.toInt()
                val rawY = ev.rawY.toInt()
                if (!r.contains(rawX, rawY)) {
                    val viewFocus = if (currentFocus == null) View(this) else currentFocus
                    val inputMethodManager =
                        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(viewFocus?.windowToken, 0)
                    view.clearFocus()
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}