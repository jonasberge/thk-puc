package calculator

import calculator.base.BaseEvaluatorTest

internal class AddMulNegExprTest : BaseEvaluatorTest<Int, AddMulNegExpr>() {

    override val cases: List<Pair<Int, AddMulNegExpr>> = listOf(
        Pair(3, AddMulNegExpr.Num(3)),
        Pair(-3, AddMulNegExpr.Neg(
            AddMulNegExpr.Num(3)
        )),
        Pair(7, AddMulNegExpr.Add(
            AddMulNegExpr.Num(3),
            AddMulNegExpr.Num(4)
        )),
        Pair(15, AddMulNegExpr.Mul(
            AddMulNegExpr.Num(3),
            AddMulNegExpr.Num(5)
        ))
    )
}
