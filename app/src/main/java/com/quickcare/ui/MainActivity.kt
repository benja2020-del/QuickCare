package com.quickcare.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.quickcare.R
import com.quickcare.models.LocalisationUtilisateur
import com.quickcare.models.Position
import com.quickcare.models.TypeUrgence
import com.quickcare.models.Urgence
import com.quickcare.services.RechercheHopital

class MainActivity : AppCompatActivity() {

    private lateinit var localisationUtilisateur: LocalisationUtilisateur
    private lateinit var rechercheHopital: RechercheHopital
    private lateinit var progressBar: ProgressBar
    private var userPosition: Position? = null

    companion object {
        private const val PERMISSION_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        localisationUtilisateur = LocalisationUtilisateur(this)
        rechercheHopital = RechercheHopital()
        progressBar = findViewById(R.id.progressBar)

        verifierEtDemanderPermission()

        setupButtons()
    }

    private fun verifierEtDemanderPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                obtenirLocalisation()
            }
            ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) -> {
                showPermissionExplanationDialog()
            }
            else -> {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    private fun showPermissionExplanationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Localisation nécessaire")
            .setMessage("QuickCare a besoin de votre position pour trouver l'hôpital le plus proche.")
            .setPositiveButton("OK") { _, _ ->
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSION_REQUEST_CODE
                )
            }
            .setNegativeButton("Refuser") { _, _ ->
                afficherErreurLocalisation()
            }
            .show()
    }

    private fun obtenirLocalisation() {
        progressBar.visibility = ProgressBar.VISIBLE
        localisationUtilisateur.obtenirPosition { position ->
            progressBar.visibility = ProgressBar.GONE
            userPosition = position
            if (position == null) {
                afficherErreurLocalisation()
            }
        }
    }

    private fun afficherErreurLocalisation() {
        AlertDialog.Builder(this)
            .setTitle("Position non disponible")
            .setMessage("Impossible d'obtenir votre position. Veuillez activer le GPS.")
            .setPositiveButton("Réessayer") { _, _ ->
                verifierEtDemanderPermission()
            }
            .setNegativeButton("Saisir manuellement") { _, _ ->
                // Permettre la saisie manuelle de la ville
                afficherSaisieManuelle()
            }
            .show()
    }

    private fun afficherSaisieManuelle() {
        // Implémentation simplifiée - on utilise une position par défaut
        userPosition = Position(-4.3219, 15.3197) // Kinshasa centre
    }

    private fun setupButtons() {
        findViewById<Button>(R.id.btn_accident).setOnClickListener {
            traiterUrgence(TypeUrgence.ACCIDENT)
        }
        findViewById<Button>(R.id.btn_cardiaque).setOnClickListener {
            traiterUrgence(TypeUrgence.CRISE_CARDIAQUE)
        }
        findViewById<Button>(R.id.btn_accouchement).setOnClickListener {
            traiterUrgence(TypeUrgence.URGENCE_ACCOUCHEMENT)
        }
        findViewById<Button>(R.id.btn_autre).setOnClickListener {
            traiterUrgence(TypeUrgence.AUTRE)
        }
    }

    private fun traiterUrgence(typeUrgence: TypeUrgence) {
        if (userPosition == null) {
            afficherErreurLocalisation()
            return
        }

        val urgence = Urgence(type = typeUrgence)
        val hopitalProche = rechercheHopital.trouverLePlusProche(urgence, userPosition!!)

        if (hopitalProche == null) {
            afficherAucunHopital(typeUrgence)
        } else {
            val intent = Intent(this, ResultatActivity::class.java).apply {
                putExtra("HOPITAL_NOM", hopitalProche.nom)
                putExtra("HOPITAL_ADRESSE", hopitalProche.adresse)
                putExtra("HOPITAL_LAT", hopitalProche.latitude)
                putExtra("HOPITAL_LON", hopitalProche.longitude)
                putExtra("DISTANCE", hopitalProche.getDistanceFormatted(userPosition!!.latitude, userPosition!!.longitude))
                putExtra("SERVICES", hopitalProche.services.joinToString { it.getLibelle() })
                putExtra("USER_LAT", userPosition!!.latitude)
                putExtra("USER_LON", userPosition!!.longitude)
            }
            startActivity(intent)
        }
    }

    private fun afficherAucunHopital(typeUrgence: TypeUrgence) {
        AlertDialog.Builder(this)
            .setTitle("Aucun hôpital trouvé")
            .setMessage("Aucun hôpital ne traite les ${typeUrgence.getLibelle()} dans votre région.")
            .setPositiveButton("OK", null)
            .show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    obtenirLocalisation()
                } else {
                    afficherErreurLocalisation()
                }
            }
        }
    }
}
