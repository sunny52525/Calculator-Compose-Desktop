package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.input.pointer.pointerMoveFilter

@Composable
fun CalculatorButton(symbol: String, onclick: (String) -> Unit) {

    var active by remember { mutableStateOf(false) }
    Button(
        onClick = {
            onclick(symbol)
        },
        modifier = Modifier.height(60.dp).width(70.dp).padding(2.dp).pointerMoveFilter(
            onEnter = {
                active = true
                false
            },
            onExit = {

                active = false
                false
            }
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (!active) Black else Gray
        )
    ) {

        Box(
            content = {
                Text(text = symbol, color = White)
            },
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize().background(if (!active) Black else Gray)
        )
    }
}


