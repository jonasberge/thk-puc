package calculator

import calculator.base.BaseConverterTest
import calculator.base.BaseTest
import org.junit.jupiter.api.Assertions.*

internal class RemoveNegConverterTest : BaseConverterTest<Int, AddMulNegExpr, AddMulExpr>() {

    override val converter = RemoveNegConverter()

    override val cases: List<Pair<AddMulNegExpr, AddMulExpr>> = listOf(
        Pair(
            AddMulNegExpr.Neg(
                AddMulNegExpr.Num(9)
            ),
            AddMulExpr.Mul(
                AddMulExpr.Num(-1),
                AddMulExpr.Num(9)
            )
        )
    )
}
