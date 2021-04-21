package calculator

import calculator.base.Converter

class RemoveMulConverter : Converter<AddMulExpr, AddExpr> {

    override fun conv(expr: AddMulExpr) : AddExpr {
        return when (expr) {
            // Adopted expressions
            is AddMulExpr.Add -> AddExpr.Add(conv(expr.p1), conv(expr.p2))
            is AddMulExpr.Num -> AddExpr.Num(expr.v)

            // Eliminated expressions
            is AddMulExpr.Mul -> conv(simplify(expr.p1, expr.p2))
        }
    }

    // Simplifies an expression (u°v)*e to (u*e)°(v*e), where ° is a binary operator.
    // The goal is to eliminate the multiplication operator in the root node.
    // e.g. (1+2)*(3+4) = 1*(3+4)+2*(3+4)
    private fun simplify(lhsOriginal: AddMulExpr, rhsOriginal: AddMulExpr) : AddMulExpr {

        // Make sure the ° operator is not a multiplication.
        // Otherwise we would end up with multiplying two expressions again.
        val lhsConverted = conv(lhsOriginal)

        // When the left hand side is a number we can simply multiply.
        if (lhsConverted is AddExpr.Num)
            return multiply(lhsConverted.v, rhsOriginal)

        // Extract u and v from u°v.
        val operands = when (lhsConverted) {
            is AddExpr.Add -> Pair(lhsConverted.p1, lhsConverted.p2)
            else -> throw IllegalStateException()
        }

        // Create u*e and v*e.
        val lhsResult = AddMulExpr.Mul(revert(operands.first), rhsOriginal)
        val rhsResult = AddMulExpr.Mul(revert(operands.second), rhsOriginal)

        // Combine u*e and v*e to (u*e)°(v*e).
        return when (lhsConverted) {
            is AddExpr.Add -> AddMulExpr.Add(lhsResult, rhsResult)
            else -> throw IllegalStateException()
        }
    }

    // Multiplies an expression n*e by adding e to itself (n-1) times.
    // The resulting tree is right-heavy.
    // e.g. 4*e = e+(e+(e+e))
    private fun multiply(multiplier: Int, expr: AddMulExpr) : AddMulExpr {

        if (multiplier == 0)
            return AddMulExpr.Num(0)

        var amount = multiplier
        var original = expr

        // Negate every term of the sum if the multiplier is negative.
        if (multiplier < 0) {
            original = negate(original)
            amount = -multiplier
        }

        var result = original
        for (i in 1 until amount)
            result = AddMulExpr.Add(original, result)

        return result
    }

    private fun negate(expr: AddMulExpr) : AddMulExpr {
        return when (expr) {
            is AddMulExpr.Add -> AddMulExpr.Add(negate(expr.p1), negate(expr.p2))
            is AddMulExpr.Mul -> AddMulExpr.Mul(negate(expr.p1), expr.p2)
            is AddMulExpr.Num -> AddMulExpr.Num(-expr.v)
        }
    }

    private fun revert(expr: AddExpr) : AddMulExpr {
        return when (expr) {
            is AddExpr.Add -> AddMulExpr.Add(revert(expr.p1), revert(expr.p2))
            is AddExpr.Num -> AddMulExpr.Num(expr.v)
        }
    }
}
