package com.mttn.android

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val homeFragment = HomeFragment.newInstance()
                openFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                val socialFragment = SocialFragment.newInstance()
                openFragment(socialFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                val alertFragment = AlertFragment.newInstance()
                openFragment(alertFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_directory -> {
                val directoryFragment = DirectoryFragment.newInstance()
                openFragment(directoryFragment)
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    private fun openFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val homeFragment = HomeFragment.newInstance()
        openFragment(homeFragment)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
