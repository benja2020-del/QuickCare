package com.quickcare.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.quickcare.R

class ResultatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultat)

        val nom = intent.getStringExtra("HOPITAL_NOM") ?: "Non disponible"
        val adresse = intent.getStringExtra("HOPITAL_ADRESSE") ?: "Non disponible"
        val distance = intent.getStringExtra("DISTANCE") ?: "Non disponible"
        val services = intent.getStringExtra("SERVICES") ?: "Non disponible"
        val latHopital = intent.getDoubleExtra("HOPITAL_LAT", 0.0)
        val lonHopital = intent.getDoubleExtra("HOPITAL_LON", 0.0)
        val userLat = intent.getDoubleExtra("USER_LAT", 0.0)
        val userLon = intent.getDoubleExtra("USER_LON", 0.0)

        findViewById<TextView>(R.id.tv_nom_hopital).text = nom
        findViewById<TextView>(R.id.tv_adresse).text = adresse
        findViewById<TextView>(R.id.tv_distance).text = "Distance : $distance"
        findViewById<TextView>(R.id.tv_services).text = "Services : $services"

        findViewById<Button>(R.id.btn_itineraire).setOnClickListener {
            ouvrirItineraire(userLat, userLon, latHopital, lonHopital)
        }

        findViewById<Button>(R.id.btn_retour).setOnClickListener {
            finish()
        }
    }

    private fun ouvrirItineraire(userLat: Double, userLon: Double, hopitalLat: Double, hopitalLon: Double) {
        val uri = Uri.parse("https://www.google.com/maps/dir/$userLat,$userLon/$hopitalLat,$hopitalLon")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}
