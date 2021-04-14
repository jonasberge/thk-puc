package calculator

import calculator.base.RecursiveEvaluator

/**
 * Expression with only Addition.
 * There is neither Multiplication nor Negation.
 */
sealed class AddExpr : RecursiveEvaluator<Int, AddExpr>(AddExpr::class) {

    data class Add(val p1: AddExpr, val p2: AddExpr) : AddExpr()
    data class Num(val v: Int) : AddExpr()

    override fun eval(expr: AddExpr): Int {
        return when (expr) {
            is Add -> eval(expr.p1) + eval(expr.p2)
            is Num -> expr.v
        }
    }
}
