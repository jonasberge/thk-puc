package calculator

import calculator.base.Converter
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException

class RemoveMulConverter : Converter<AddMulExpr, AddExpr> {

    override fun conv(expr: AddMulExpr) : AddExpr {
        return when (expr) {
            // Adopted expressions
            is AddMulExpr.Add -> AddExpr.Add(conv(expr.p1), conv(expr.p2))
            is AddMulExpr.Num -> AddExpr.Num(expr.v)

            // Eliminated expressions
            is AddMulExpr.Mul -> convertMul(expr)
        }
    }

    private fun convertMul(expr: AddMulExpr.Mul) : AddExpr {

        // 1. First operand is a number
        if (expr.p1 is AddMulExpr.Num)
            return sumExpr(expr.p1.v, conv(expr.p2))

        // 2. Second operand is a number
        if (expr.p2 is AddMulExpr.Num)
            return sumExpr(expr.p2.v, conv(expr.p1))

        // 3. Both operands are expressions
        return simplify(expr.p1, expr.p2)
    }

    // 3*e = e + (e + e)
    // 5*e = e + (e + (e + (e + e)))
    // N*e = e + (e + (e + ...)) with N e's
    // N*e = sum(N, e)
    private fun sumExpr(amount: Int, expr: AddExpr) : AddExpr {
        if (amount == 0) throw UnsupportedOperationException()
        if (amount < 0) throw IllegalArgumentException()

        var result = expr
        for (i in 1 until amount)
            result = AddExpr.Add(expr, result)

        return result
    }

    // (1+2)*(3+4) = 1*(3+4)+2*(3+4)
    // (u°¹v)*(x°²y) = u*(x°²y)°¹v*(x°²y), where °¹ and °² are any binary operators
    // (u°v)*e = u*e°v*e, where ° is any binary operator
    private fun simplify(lhsOriginal: AddMulExpr, rhsOriginal: AddMulExpr) : AddExpr {

        // Make sure our left hand side does not contain a multiplication.
        // Otherwise we would face an issue. e.g. with: (u*v)*e = (u*e)*(v*e)
        // The result is still a multiplication of two expressions.
        val lhsConverted = conv(lhsOriginal)

        if (lhsConverted is AddExpr.Num)
            // In case the conversion yields a number
            // we can just sum the expression that many times.
            return sumExpr(lhsConverted.v, conv(rhsOriginal))

        // Extract u and v.
        // u = operands.first
        // v = operands.second
        val operands = when (lhsConverted) {
            is AddExpr.Add -> Pair(lhsConverted.p1, lhsConverted.p2)
            else -> throw IllegalArgumentException()
        }

        // Create u*e and v*e, where e is the right hand side.
        val lhsResult = AddMulExpr.Mul(revert(operands.first), rhsOriginal)
        val rhsResult = AddMulExpr.Mul(revert(operands.second), rhsOriginal)

        // Combine them back together.
        val result = when (lhsConverted) {
            is AddExpr.Add -> AddMulExpr.Add(lhsResult, rhsResult)
            else -> throw IllegalArgumentException()
        }

        // Finally, convert the result to the desired format.
        // The root expression will not be a multiplication.
        // Child expressions will be converted recursively.
        return conv(result)
    }

    // Converts an AddExpr back to an AddMulExpr.
    private fun revert(expr: AddExpr) : AddMulExpr {
        return when (expr) {
            is AddExpr.Add -> AddMulExpr.Add(revert(expr.p1), revert(expr.p2))
            is AddExpr.Num -> AddMulExpr.Num(expr.v)
        }
    }
}