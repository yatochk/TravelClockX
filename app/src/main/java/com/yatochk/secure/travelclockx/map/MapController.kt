package com.yatochk.secure.travelclockx.map

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.yatochk.secure.travelclockx.location.InfoLocationState

class MapController(private val googleMap: GoogleMap) {

    fun moveTo(infoLocationState: InfoLocationState) {
        googleMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    infoLocationState.latitude,
                    infoLocationState.longitude
                ),
                15.0f
            )
        )
    }

}