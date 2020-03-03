package com.yatochk.secure.travelclockx.location

import android.content.Context
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LocationViewModel @Inject constructor(
    context: Context
) : ViewModel() {

    val locationLiveData = LocationLiveData(context)

}