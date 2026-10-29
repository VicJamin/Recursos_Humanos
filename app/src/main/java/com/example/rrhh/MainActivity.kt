package com.example.rrhh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.rrhh.ui.Navegacion
import com.example.rrhh.ui.theme.RRHHTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RRHHTheme {
                Navegacion()
            }
        }
    }
}
