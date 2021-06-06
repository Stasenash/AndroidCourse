package com.example.animeapi.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.animeapi.R
import com.example.animeapi.model.Anime
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    lateinit var animeSelected : Anime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)
        val navController = findNavController(R.id.fragment)

        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setSelectedItemId(R.id.loginFragment);
        bottomNavigationView.setItemIconTintList(null);

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.loginFragment) {
                bottomNavigationView.visibility = View.GONE
            } else {
                bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun switchContent(id: Int, fragment: Fragment) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        var rootView = this.findViewById<View>(android.R.id.content)
        ft.replace(rootView.id, fragment, fragment.toString())
        ft.addToBackStack(fragment.toString())
        ft.commit()

        (fragment as AnimeDetailFragment).anime = animeSelected
    }
}