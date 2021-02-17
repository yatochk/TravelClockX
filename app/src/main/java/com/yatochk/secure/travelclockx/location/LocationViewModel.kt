package com.yatochk.secure.travelclockx.location

import androidx.lifecycle.ViewModel
import com.snakydesign.livedataextensions.first
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    locationLiveData: LocationLiveData
) : ViewModel() {

    val location = locationLiveData.first()

}