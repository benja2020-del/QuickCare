package com.quickcare.models

data class Urgence(
    val id: String = java.util.UUID.randomUUID().toString(),
    val type: TypeUrgence,
    val description: String = ""
) {
    fun estPrisEnChargePar(hopital: Hopital): Boolean {
        return hopital.services.contains(type) || 
               (type == TypeUrgence.AUTRE && hopital.services.contains(TypeUrgence.AUTRE))
    }
}
