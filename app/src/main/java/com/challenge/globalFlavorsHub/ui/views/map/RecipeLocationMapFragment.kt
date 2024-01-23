package com.challenge.globalFlavorsHub.ui.views.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.challenge.globalFlavorsHub.R
import com.challenge.globalFlavorsHub.databinding.FragmentRecipeLocationMapBinding
import com.challenge.globalFlavorsHub.ui.views.main.GlobalFlavorsHubViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeLocationMapFragment : Fragment(R.layout.fragment_recipe_location_map) {

    private val globalFlavorsHubViewModel: GlobalFlavorsHubViewModel by activityViewModels()
    private val navArgs: RecipeLocationMapFragmentArgs by navArgs()
    private lateinit var binding: FragmentRecipeLocationMapBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentRecipeLocationMapBinding.bind(view)
        loadMap()
    }

    private fun loadMap() {
        binding.mapView.onCreate(null)
        binding.mapView.onResume()
        binding.mapView.getMapAsync { googleMap ->
            globalFlavorsHubViewModel.isLoading(false)
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(navArgs.latitude.toDouble(), navArgs.longitude.toDouble())))
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(12f))
            googleMap.addMarker(
                MarkerOptions().position(LatLng(navArgs.latitude.toDouble(), navArgs.longitude.toDouble())).title(navArgs.country.orEmpty()),
            )
        }
    }
}
