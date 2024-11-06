package com.example.maravilhaslocalizacao

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ktx.awaitMap
import com.google.maps.android.ktx.utils.maps.CameraUpdateFactory
import kotlinx.coroutines.*


class MainActivity : ComponentActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Verificar se a permissão já foi concedida
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }

        setContent {
            MaravilhasApp()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val cristoRedentor = LatLng(-22.9519, -43.2105)
        map.addMarker(MarkerOptions().position(cristoRedentor).title("Cristo Redentor"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(cristoRedentor, 10f))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permissão concedida
        }
    }
}

@Composable
fun MaravilhasApp() {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var userLocation by remember { mutableStateOf<LatLng?>(null) }
    val defaultLocation = LatLng(-22.9519, -43.2105) // Cristo Redentor como localização padrão
    var map: GoogleMap? by remember { mutableStateOf(null) }

    // Solicitar localização do usuário
    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    userLocation = LatLng(it.latitude, it.longitude)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Maravilhas do Mundo") })
        }
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Bem-vindo ao aplicativo Maravilhas do Mundo!", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(16.dp))

            // Exibir o MapView
            Box(modifier = Modifier.fillMaxSize()) {
                AndroidView(factory = { context ->
                    MapView(context).apply {
                        onCreate(null)
                        getMapAsync { googleMap ->
                            map = googleMap
                            googleMap.uiSettings.isZoomControlsEnabled = true
                            val location = userLocation ?: defaultLocation
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))

                            // Adicionar marcadores das maravilhas
                            maravilhas.forEach { maravilha ->
                                val position = LatLng(maravilha.latitude, maravilha.longitude)
                                googleMap.addMarker(
                                    MarkerOptions()
                                        .position(position)
                                        .title(maravilha.name)
                                )
                            }
                        }
                    }
                }, modifier = Modifier.fillMaxSize())
            }
        }
    }
}

