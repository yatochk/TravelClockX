package com.yatochk.secure.travelclockx.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

class LocationLiveData(private val context: Context) : LiveData<LocationState>() {

    companion object {
        private val locationRequest: LocationRequest = LocationRequest.create().apply {
            interval = 1000
            fastestInterval = 100
            priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        }
    }

    private var fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return
            for (location in locationResult.locations) {
                setLocationData(location)
            }
        }
    }

    override fun onActive() {
        super.onActive()
        startLocationUpdates()
    }

    override fun onInactive() {
        super.onInactive()
        cancelObserveLocation()
    }

    private fun cancelObserveLocation() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private fun setLocationData(location: Location) {
        value = InfoLocationState(
            location.latitude,
            location.longitude
        )
    }

    private fun setNeedPermission() {
        value = NeedPermissionLocationState
    }

    fun startLocationUpdates() {
        checkPermission()
        checkLastLocation()
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        )
    }

    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            setNeedPermission()
            return
        }
    }

    @RequiresPermission(
        anyOf = [
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.ACCESS_FINE_LOCATION"
        ]
    )
    private fun checkLastLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.also {
                    setLocationData(it)
                }
            }
            .addOnFailureListener {
                Log.e(LocationLiveData::class.simpleName, it.localizedMessage ?: "", it)
            }
    }

}