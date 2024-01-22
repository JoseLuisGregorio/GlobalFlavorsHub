package com.challenge.globalFlavorsHub.ui.views.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.challenge.globalFlavorsHub.R
import com.challenge.globalFlavorsHub.databinding.FragmentRecipeLocationMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Marker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeLocationMapFragment : Fragment(R.layout.fragment_recipe_location_map), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(FragmentRecipeLocationMapBinding.bind(view)) {}
    }

    override fun onMapReady(googleMap: GoogleMap) {
        /*
        mMap = map
        mMap?.setOnMarkerClickListener(this)
        map?.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(19.432848, -99.133329),
                14f,
            )
        )

         */
    }

    /*
    private fun createMarkers() {
        val markerOptions = MarkerOptions()
        markerOptions.position(LatLng(latitud(), longitud()))
        markerOptions.title(country)
        mMap?.addMarker(markerOptions)
    }
     */
    override fun onMarkerClick(marker: Marker): Boolean {
        TODO("Not yet implemented")
    }
}
