package calculator.base

interface Converter<F, T> {

    fun conv(expr: F) : T
}