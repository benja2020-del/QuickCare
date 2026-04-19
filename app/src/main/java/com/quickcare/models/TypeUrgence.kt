package com.quickcare.models

enum class TypeUrgence {
    ACCIDENT,
    CRISE_CARDIAQUE,
    URGENCE_ACCOUCHEMENT,
    AUTRE;  // Pour les cas non listés

    companion object {
        fun fromString(value: String): TypeUrgence? {
            return values().find { it.name.equals(value, ignoreCase = true) }
        }
    }

    fun getLibelle(): String {
        return when (this) {
            ACCIDENT -> "Accident"
            CRISE_CARDIAQUE -> "Crise cardiaque"
            URGENCE_ACCOUCHEMENT -> "Urgence accouchement"
            AUTRE -> "Autre urgence"
        }
    }
}
