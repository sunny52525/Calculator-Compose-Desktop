// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.Window
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.CalculatorButton
import utils.Constants

fun main() = Window(size = IntSize(320, 605),resizable = false) {


    Calculator()

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Calculator() {
    Column(modifier = Modifier.background(Color.DarkGray)) {

        var result by remember {
            mutableStateOf("0")
        }
        var sign by remember {
            mutableStateOf("")
        }

        var leftHandSide by remember {
            mutableStateOf("")
        }
        var rightHandSide by remember {
            mutableStateOf("")
        }


        Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {


            Text(
                text = result,
                modifier = Modifier.align(Alignment.BottomEnd).padding(end = 10.dp, bottom = 10.dp),
                color = Color.White,
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold
            )

        }
        LazyVerticalGrid(
            cells = GridCells.Fixed(4), contentPadding = PaddingValues(5.dp), modifier = Modifier.width(320.dp)
        ) {


            items(Constants.buttons) {

                CalculatorButton(it.symbol) {

                }
            }
        }
    }
}

