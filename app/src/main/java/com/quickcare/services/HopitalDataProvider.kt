package com.quickcare.services

import com.quickcare.models.Hopital
import com.quickcare.models.TypeUrgence

object HopitalDataProvider {
    
    // Base de données locale des hôpitaux
    fun getHopitaux(): List<Hopital> {
        return listOf(
            Hopital(
                nom = "CHU de Kinshasa",
                adresse = "12 Avenue de l'Hôpital, Kinshasa",
                latitude = -4.3219,
                longitude = 15.3197,
                services = listOf(TypeUrgence.ACCIDENT, TypeUrgence.CRISE_CARDIAQUE, TypeUrgence.URGENCE_ACCOUCHEMENT)
            ),
            Hopital(
                nom = "Hôpital Ngaliema",
                adresse = "45 Boulevard du 30 Juin, Kinshasa",
                latitude = -4.3319,
                longitude = 15.3097,
                services = listOf(TypeUrgence.ACCIDENT, TypeUrgence.URGENCE_ACCOUCHEMENT)
            ),
            Hopital(
                nom = "Clinique Kinoise",
                adresse = "78 Avenue Kasa-Vubu, Kinshasa",
                latitude = -4.3419,
                longitude = 15.3297,
                services = listOf(TypeUrgence.CRISE_CARDIAQUE)
            ),
            Hopital(
                nom = "Hôpital Mère-Enfant",
                adresse = "23 Avenue de la Paix, Kinshasa",
                latitude = -4.3119,
                longitude = 15.3397,
                services = listOf(TypeUrgence.URGENCE_ACCOUCHEMENT)
            ),
            Hopital(
                nom = "Centre Médical de Kinshasa",
                adresse = "56 Avenue Lumumba, Kinshasa",
                latitude = -4.3519,
                longitude = 15.2897,
                services = listOf(TypeUrgence.ACCIDENT, TypeUrgence.CRISE_CARDIAQUE)
            )
        )
    }
}
