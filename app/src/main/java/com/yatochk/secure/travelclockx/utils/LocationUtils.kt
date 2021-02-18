package com.yatochk.secure.travelclockx.utils

import com.google.android.gms.maps.model.LatLng

infix fun Double.latLng(lng: Double) = LatLng(this, lng)
