package utils

import model.ButtonType

object Constants {

    val buttons: List<ButtonType> =
        listOf(
            ButtonType("%", "operation"),
            ButtonType("^", "operation"),
            ButtonType("1/x", "numberOperation"),
            ButtonType("x^2", "numberOperation"),
            ButtonType("CE", "clear"),
            ButtonType("C", "clear"),
            ButtonType("C", "delete"),
            ButtonType("/", "operation"),
            ButtonType("7", "number"),
            ButtonType("8", "number"),
            ButtonType("9", "number"),
            ButtonType("*", "operation"),
            ButtonType("4", "number"),
            ButtonType("5", "number"),
            ButtonType("6", "number"),
            ButtonType("-", "operation"),
            ButtonType("1", "number"),
            ButtonType("2", "number"),
            ButtonType("3", "number"),
            ButtonType("+", "operation"),
            ButtonType("+/-", "negative"),
            ButtonType("0", "number"),
            ButtonType(".", "dot"),
            ButtonType("=", "result"),
        )
}