package com.quickcare.models

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

data class Position(val latitude: Double, val longitude: Double)

class LocalisationUtilisateur(private val context: Context) {
    
    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    fun demanderPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun obtenirPosition(callback: (Position?) -> Unit) {
        if (!demanderPermission()) {
            callback(null)
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                callback(Position(it.latitude, it.longitude))
            } ?: callback(null)
        }.addOnFailureListener {
            callback(null)
        }
    }
}
