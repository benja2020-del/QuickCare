package com.quickcare.services

import com.quickcare.models.Hopital
import com.quickcare.models.Position
import com.quickcare.models.Urgence

class RechercheHopital(private val hopitaux: List<Hopital> = HopitalDataProvider.getHopitaux()) {
    
    fun trouverHopitauxDisponibles(urgence: Urgence): List<Hopital> {
        return hopitaux.filter { it.peutSoigner(urgence) }
    }

    fun trouverLePlusProche(
        urgence: Urgence,
        userPosition: Position
    ): Hopital? {
        val hopitauxDisponibles = trouverHopitauxDisponibles(urgence)
        
        if (hopitauxDisponibles.isEmpty()) {
            return null
        }

        return hopitauxDisponibles.minByOrNull { 
            it.distanceVers(userPosition.latitude, userPosition.longitude)
        }
    }

    fun trouverAvecDistances(
        urgence: Urgence,
        userPosition: Position
    ): List<Pair<Hopital, Double>> {
        return trouverHopitauxDisponibles(urgence)
            .map { it to it.distanceVers(userPosition.latitude, userPosition.longitude) }
            .sortedBy { it.second }
    }
}
