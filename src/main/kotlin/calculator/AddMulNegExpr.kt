package calculator

import calculator.base.RecursiveEvaluator

/**
 * Expression with Addition, Multiplication and Negation.
 */
sealed class AddMulNegExpr : RecursiveEvaluator<Int, AddMulNegExpr>(AddMulNegExpr::class) {

    data class Add(val p1: AddMulNegExpr, val p2: AddMulNegExpr) : AddMulNegExpr()
    data class Mul(val p1: AddMulNegExpr, val p2: AddMulNegExpr) : AddMulNegExpr()
    data class Neg(val v: AddMulNegExpr) : AddMulNegExpr()
    data class Num(val v: Int) : AddMulNegExpr()

    override fun eval(expr: AddMulNegExpr): Int {
        return when (expr) {
            is Add -> eval(expr.p1) + eval(expr.p2)
            is Mul -> eval(expr.p1) * eval(expr.p2)
            is Neg -> - eval(expr.v)
            is Num -> expr.v
        }
    }
}
