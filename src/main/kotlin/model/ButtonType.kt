package model

data class ButtonType(
    val symbol: String,
    val type: Buttons,
)

enum class Buttons(val value: String) {
    OPERATION("operation"),
    NUMBER_OPERATION("numberOperation"),
    CLEAR("clear"),
    DELETE("delete"),
    NUMBER("number"),
    PLUS_NEGATIVE("negative"),
    DOT("dot"),
    RESULT("result"),
}