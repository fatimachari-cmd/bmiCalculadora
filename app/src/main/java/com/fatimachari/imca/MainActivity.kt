
package com.fatimachari.imca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fatimachari.imca.ui.theme.ImcATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ImcATheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BMIScreen()
                }
            }
        }
    }
}

@Composable
fun BMIScreen() {
    var nom: String by remember { mutableStateOf("") }
    var pes: Int by remember { mutableStateOf(80) }
    var alcada: Int by remember { mutableStateOf(180) }
    var bmi: Double by remember { mutableStateOf(0.0) }

    Column() {
        Text(text = "Calculadora IMC")

        TextField(
            value = "",
            onValueChange = { nom = it },
            label = { Text(text = "Nom") }
        )

        Row {
            Text(text = "Pes: $pes kg")

            Button(onClick = {
                if (pes > 1) {
                    pes--
                }
            }) {
                Text(text = "-")
            }

            Button(onClick = {
                pes++
            }) {
                Text(text = "+")
            }
        }

        Text(text = "Alçada: $alcada cm")

        Slider(
            value = alcada.toFloat(),
            onValueChange = { valor ->
                alcada = valor.toInt()
            },
            valueRange = 100f..250f
        )

        Button(onClick = {
            val alcadaMetres = alcada / 100.0
            bmi = pes / (alcadaMetres * alcadaMetres)
        }) {
            Text(text = "Calcular IMC")
        }

        if (bmi != 0.0) {
            Text(text = "IMC: $bmi")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImcATheme {
        BMIScreen()
    }
}
