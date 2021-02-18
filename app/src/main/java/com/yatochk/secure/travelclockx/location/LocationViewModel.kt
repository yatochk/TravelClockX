package com.yatochk.secure.travelclockx.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.snakydesign.livedataextensions.filter
import com.snakydesign.livedataextensions.map
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationLiveData: LocationLiveData
) : ViewModel() {

    private var enableAutoLocation = true

    val location: LiveData<LocationState> = locationLiveData.filter { enableAutoLocation }.map {
        enableAutoLocation = false
        it
    }

    fun onPermissionLocationGranted() {
        locationLiveData.startLocationUpdates()
        enableAutoLocation = true
    }

}