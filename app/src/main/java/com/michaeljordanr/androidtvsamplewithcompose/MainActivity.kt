package com.michaeljordanr.androidtvsamplewithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.michaeljordanr.androidtvsamplewithcompose.ui.theme.AndroidTVSampleWithComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val sampleChannels = listOf(
            Channel(
                "mtv",
                "MTV",
                "https://fl3.moveonjoy.com/MTV/index.m3u8",
                "https://www.tenhomaisdiscosqueamigos.com/wp-content/uploads/2021/08/MTV-logo-e1627856560927.jpeg"
            ),
            Channel(
                "aljazeera",
                "Al Jazeera English",
                "https://live-hls-web-aje.getaj.net/AJE/01.m3u8",
                "https://upload.wikimedia.org/wikipedia/en/thumb/f/f2/Aljazeera_eng.svg/220px-Aljazeera_eng.svg.png"
            ),
            Channel(
                "sintel",
                "Sintel (On-Demand)",
                "https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8f/Sintel_poster_and_logo.jpg/220px-Sintel_poster_and_logo.jpg"
            )
        )

        setContent {
            AndroidTVSampleWithComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        TvStreamScreen(channels = sampleChannels)
                    }
                }
            }
        }
    }
}