import calculator.RemoveNegConverter
import calculator.AddMulNegExpr

fun main() {
    val e = AddMulNegExpr.Mul(
        AddMulNegExpr.Add(
            AddMulNegExpr.Num(1),
            AddMulNegExpr.Num(2)
        ),
        AddMulNegExpr.Neg(
            AddMulNegExpr.Num(3)
        )
    )

    val converter = RemoveNegConverter()
    val pe = converter.conv(e)

    println(e)
    println(e.eval())
    println()

    println(pe)
    println(pe.eval())
    println()
}
