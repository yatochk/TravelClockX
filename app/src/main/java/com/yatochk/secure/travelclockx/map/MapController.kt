package com.yatochk.secure.travelclockx.map

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.yatochk.secure.travelclockx.location.InfoLocationState
import com.yatochk.secure.travelclockx.utils.latLng

class MapController(private val googleMap: GoogleMap) {

    companion object {
        private const val STREET_ZOOM = 15f
    }

    fun moveTo(location: InfoLocationState) {
        googleMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                location.latitude latLng location.longitude,
                STREET_ZOOM
            )
        )
    }

}