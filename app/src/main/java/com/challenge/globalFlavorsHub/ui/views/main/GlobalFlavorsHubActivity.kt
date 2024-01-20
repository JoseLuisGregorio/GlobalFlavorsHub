package com.challenge.globalFlavorsHub.ui.views.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.FloatingWindow
import androidx.navigation.fragment.findNavController
import com.challenge.globalFlavorsHub.R
import com.challenge.globalFlavorsHub.databinding.ActivityGlobalFlavorsHubBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GlobalFlavorsHubActivity : AppCompatActivity() {
    private val navController by lazy {
        supportFragmentManager
            .findFragmentById(R.id.turboUserFragmentContainerView)
            ?.findNavController()
    }

    private val globalFlavorsHubViewModel: GlobalFlavorsHubViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global_flavors_hub)

        with(ActivityGlobalFlavorsHubBinding.inflate(layoutInflater)) {
            setContentView(root)
            swipeToRefreshLayout.setOnChildScrollUpCallback { _, _ ->
                globalFlavorsHubViewModel.currentScroll != 0
            }

            swipeToRefreshLayout.setOnRefreshListener {
                globalFlavorsHubViewModel.refreshRequest?.invoke()
            }

            globalFlavorsHubViewModel.isRefreshing.observe(this@GlobalFlavorsHubActivity) {
                swipeToRefreshLayout.isRefreshing = it
            }
            navController?.addOnDestinationChangedListener { _, destination, _ ->
                globalFlavorsHubViewModel.currentScroll = 0
                if (destination !is FloatingWindow) {
                    swipeToRefreshLayout.isEnabled = destination.id == R.id.recipesFragment
                }
            }
        }
    }
}
