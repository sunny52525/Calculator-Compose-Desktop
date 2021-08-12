package utils

import model.ButtonType
import model.Buttons

object Constants {

    val buttons =
        listOf(
            ButtonType("%", Buttons.OPERATION),
            ButtonType("^", Buttons.OPERATION),
            ButtonType("1/x", Buttons.NUMBER_OPERATION),
            ButtonType("x^2", Buttons.NUMBER_OPERATION),
            ButtonType("CE", Buttons.CLEAR),
            ButtonType("C", Buttons.CLEAR),
            ButtonType("C", Buttons.DELETE),
            ButtonType("/", Buttons.OPERATION),
            ButtonType("7", Buttons.NUMBER),
            ButtonType("8", Buttons.NUMBER),
            ButtonType("9", Buttons.NUMBER),
            ButtonType("*", Buttons.OPERATION),
            ButtonType("4", Buttons.NUMBER),
            ButtonType("5", Buttons.NUMBER),
            ButtonType("6", Buttons.NUMBER),
            ButtonType("-", Buttons.OPERATION),
            ButtonType("1", Buttons.NUMBER),
            ButtonType("2", Buttons.NUMBER),
            ButtonType("3", Buttons.NUMBER),
            ButtonType("+", Buttons.OPERATION),
            ButtonType("+/-", Buttons.PLUS_NEGATIVE),
            ButtonType("0", Buttons.NUMBER),
            ButtonType(".", Buttons.DOT),
            ButtonType("=", Buttons.RESULT),
        )
}