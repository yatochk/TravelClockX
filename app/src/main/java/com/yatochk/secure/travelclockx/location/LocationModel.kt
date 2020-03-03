package com.yatochk.secure.travelclockx.location

sealed class LocationModel

class LocationInfo(
    val latitude: Double,
    val longitude: Double
) : LocationModel()

object NeedLocationPermission : LocationModel()
