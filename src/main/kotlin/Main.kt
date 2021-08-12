// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.Window
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.Buttons
import ui.CalculatorButton
import utils.Constants
import kotlin.math.pow


@ExperimentalFoundationApi
fun main() = Window(size = IntSize(320, 605), resizable = false) {


    Calculator()

}

@ExperimentalFoundationApi
@Composable
@Preview
fun Calculator() {
    var result by remember {
        mutableStateOf("0")
    }


    var expression: String by remember {
        mutableStateOf("")
    }


    Column(modifier = Modifier.background(Color.DarkGray).width(320.dp)) {


        Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {

            Column(modifier = Modifier.align(Alignment.BottomEnd)) {

                Text(
                    text = result,
                    modifier = Modifier.padding(end = 10.dp, bottom = 10.dp).align(Alignment.End),
                    color = Color.White,
                    fontSize = 60.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = expression,
                    modifier = Modifier.padding(end = 10.dp, bottom = 10.dp),
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

            }

        }
        LazyVerticalGrid(
            cells = GridCells.Fixed(4), contentPadding = PaddingValues(5.dp), modifier = Modifier.width(320.dp)
        ) {


            items(Constants.buttons) {

                CalculatorButton(it) { button, buttonType ->
                    println(buttonType.symbol)
                    println(button.value)

                    when (button) {
                        Buttons.OPERATION -> {
                            if (
                                expression.isNotBlank() &&

                                isOperation(expression.last()).not()
                            )
                                expression += buttonType.symbol
                        }
                        Buttons.NUMBER_OPERATION -> {

                        }
                        Buttons.CLEAR -> {
                            result = ""
                            expression = ""
                        }
                        Buttons.DELETE -> {

                            if (expression.isNotBlank()) {
                                expression = expression.substring(0, expression.length - 1)
                            }

                        }
                        Buttons.NUMBER -> {
                            expression += buttonType.symbol
                        }
                        Buttons.PLUS_NEGATIVE -> {
                            if (expression.isNotBlank()) {
                                expression = if (expression.first() == '-') {
                                    "+${expression.substring(1, expression.length)}"

                                } else if (expression.first() == '+') {
                                    "-${expression.substring(1, expression.length)}"

                                } else {
                                    "-${expression}"
                                }
                            }
                        }
                        Buttons.DOT -> {
                            if (expression.contains('.').not()) {
                                expression = "$expression."
                            }
                        }
                        Buttons.RESULT -> {
                            if (isOperation(expression.last()))
                                return@CalculatorButton

                            evaluate(expression).toString().apply {
                                result = if (contains('.')) {
                                    val index = indexOf('.')
                                    print("index,$index")
                                    substring(0, index + 2)
                                } else {
                                    this
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


fun isOperation(char: Char): Boolean {
    when (char) {
        '-' -> {
            return true
        }
        '+' -> {
            return true
        }
        '/' -> {
            return true
        }
        '*' -> {
            return true
        }
        '.' -> {
            return true
        }
        '%' -> {
            return true
        }
        '^' -> {
            return true
        }

    }
    return false
}

fun evaluate(expression: String): Double {
    val tokens = expression.toCharArray()

    // Stack for numbers: 'values'
    val values = java.util.Stack<Double>()

    // Stack for Operators: 'ops'
    val ops = java.util.Stack<Char>()
    var i = 0
    while (i < tokens.size) {


        // Current token is a
        // whitespace, skip it
        if (tokens[i] == ' ') {
            i++
            continue
        }

        // Current token is a number,
        // push it to stack for numbers
        if (tokens[i] in '0'..'9'
        ) {
            val stringBuffer = StringBuffer()

            while (i < tokens.size && tokens[i] >= '0' && tokens[i] <= '9') stringBuffer.append(tokens[i++])
            values.push(stringBuffer.toString().toInt().toDouble())


            i--
        } else if (tokens[i] == '(') ops.push(tokens[i]) else if (tokens[i] == ')') {
            while (ops.peek() != '(') values.push(
                applyOp(
                    ops.pop(),
                    values.pop(),
                    values.pop()
                )
            )
            ops.pop()
        } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
            // While top of 'ops' has same
            // or greater precedence to current
            // token, which is an operator.
            // Apply operator on top of 'ops'
            // to top two elements in values stack
            while (!ops.empty() &&
                hasPrecedence(
                    tokens[i],
                    ops.peek()
                )
            ) values.push(
                applyOp(
                    ops.pop(),
                    values.pop(),
                    values.pop()
                )
            )

            // Push current token to 'ops'.
            ops.push(tokens[i])
        }
        i++
    }

    // Entire expression has been
    // parsed at this point, apply remaining
    // ops to remaining values
    while (!ops.empty()) values.push(
        applyOp(
            ops.pop(),
            values.pop(),
            values.pop()
        )
    )

    // Top of 'values' contains
    // result, return it
    return values.pop()
}

// Returns true if 'op2' has higher
// or same precedence as 'op1',
// otherwise returns false.
fun hasPrecedence(
    op1: Char, op2: Char
): Boolean {
    if (op2 == '(' || op2 == ')') return false
    return !((op1 == '*' || op1 == '/' || op1 == '^') &&
            (op2 == '+' || op2 == '-'))
}

// A utility method to apply an
// operator 'op' on operands 'a'
// and 'b'. Return the result.
fun applyOp(
    op: Char,
    b: Double, a: Double
): Double {
    when (op) {
        '+' -> return a + b
        '-' -> return a - b
        '*' -> return a * b
        '/' -> {
            if (b == 0.0) throw UnsupportedOperationException(
                "Cannot divide by zero"
            )
            return a / b
        }
        '^' -> {
            return a.pow(b)
        }

    }
    return 0.toDouble()
}