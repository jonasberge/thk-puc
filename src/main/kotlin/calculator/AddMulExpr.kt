package calculator

import calculator.base.RecursiveEvaluator

/**
 * Expression with Addition and Multiplication.
 * There is no Negation.
 */
sealed class AddMulExpr : RecursiveEvaluator<Int, AddMulExpr>(AddMulExpr::class) {

    data class Add(val p1: AddMulExpr, val p2: AddMulExpr) : AddMulExpr()
    data class Mul(val p1: AddMulExpr, val p2: AddMulExpr) : AddMulExpr()
    data class Num(val v: Int) : AddMulExpr()

    override fun eval(expr: AddMulExpr): Int {
        return when (expr) {
            is Add -> eval(expr.p1) + eval(expr.p2)
            is Mul -> eval(expr.p1) * eval(expr.p2)
            is Num -> expr.v
        }
    }
}
