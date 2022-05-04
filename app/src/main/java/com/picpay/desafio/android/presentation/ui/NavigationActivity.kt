package com.picpay.desafio.android.presentation.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.picpay.desafio.android.R

class NavigationActivity : AppCompatActivity() {

    private val navController by lazy {
        Navigation.findNavController(this, R.id.frame_navigation)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.frame_navigation)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        navController.addOnDestinationChangedListener { _,
                                                        destination,
                                                        _ ->
            title = destination.label
        }
    }

}