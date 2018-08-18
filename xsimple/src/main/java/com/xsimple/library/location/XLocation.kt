@file:JvmName("XLocationKt")

package com.xsimple.library.location

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.support.v4.content.ContextCompat

/**
 * Created by dvirdaniel on 25/03/2018.
 */

fun Context.hasLocationPermission(): Boolean {
    return ContextCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
}

fun Context.isGpsEnabled(): Boolean {
    val service = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return service.isProviderEnabled(LocationManager.GPS_PROVIDER) || service.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
}

