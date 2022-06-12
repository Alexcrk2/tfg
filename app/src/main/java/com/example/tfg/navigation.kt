package com.example.tfg

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tfg.databinding.ActivityNavigationBinding
import com.example.tfg.ui.home.HomeFragment

class navigation : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //supportActionBar?.hide()

        val bundle = intent.extras
        val texto: String? = bundle?.getString("id")
       //j val datos = intent.extras!!
        // descomentar ??    val datosobtenidos = datos.getString("id")
        //val email =datosobtenidos.toString()
        val args = Bundle()
       // args.putString("id", texto.toString());
        val mFragment = HomeFragment()
        mFragment.setArguments(args)

        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()

        //mFragmentTransaction.replace(R.id.navigation_home, mFragment); //donde fragmentContainer_id es el ID del FrameLayout donde tu Fragment está contenido.

        mFragmentTransaction.addToBackStack(null)

        mFragmentTransaction.commit();


        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_navigation)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}