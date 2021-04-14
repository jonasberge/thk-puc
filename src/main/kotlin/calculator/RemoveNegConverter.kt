package calculator

import calculator.base.Converter

class RemoveNegConverter : Converter<AddMulNegExpr, AddMulExpr> {

    override fun conv(expr: AddMulNegExpr) : AddMulExpr {
        return when (expr)  {
            // Adopted expressions
            is AddMulNegExpr.Add -> AddMulExpr.Add(conv(expr.p1), conv(expr.p2))
            is AddMulNegExpr.Mul -> AddMulExpr.Mul(conv(expr.p1), conv(expr.p2))
            is AddMulNegExpr.Num -> AddMulExpr.Num(expr.v)

            // Eliminated expressions
            is AddMulNegExpr.Neg -> AddMulExpr.Mul(
                AddMulExpr.Num(-1),
                conv(expr.v)
            )
        }
    }
}
