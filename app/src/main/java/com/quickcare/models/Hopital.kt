package com.quickcare.models

import kotlin.math.*

data class Hopital(
    val nom: String,
    val adresse: String,
    val latitude: Double,
    val longitude: Double,
    val services: List<TypeUrgence>
) {
    fun peutSoigner(urgence: Urgence): Boolean {
        return services.contains(urgence.type) ||
               (urgence.type == TypeUrgence.AUTRE && services.contains(TypeUrgence.AUTRE))
    }

    fun distanceVers(userLat: Double, userLon: Double): Double {
        val R = 6371.0 // Rayon de la Terre en km
        val latDistance = Math.toRadians(userLat - latitude)
        val lonDistance = Math.toRadians(userLon - longitude)
        val a = sin(latDistance / 2) * sin(latDistance / 2) +
                cos(Math.toRadians(latitude)) * cos(Math.toRadians(userLat)) *
                sin(lonDistance / 2) * sin(lonDistance / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return R * c
    }

    fun getDistanceFormatted(userLat: Double, userLon: Double): String {
        val distance = distanceVers(userLat, userLon)
        return if (distance < 1) {
            "${(distance * 1000).toInt()} m"
        } else {
            String.format("%.1f km", distance)
        }
    }
}
