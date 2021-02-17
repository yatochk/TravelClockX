package com.yatochk.secure.travelclockx.location

sealed class LocationState

class InfoLocationState(
    val latitude: Double,
    val longitude: Double
) : LocationState()

object NeedPermissionLocationState : LocationState()
