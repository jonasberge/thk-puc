package calculator.base

import org.junit.jupiter.api.Assertions.*

abstract class BaseEvaluatorTest<E, T : Evaluator<E>> : BaseTest<Pair<E, T>>() {

    override fun testCase(case: Pair<E, T>) {
        assertEquals(case.first, case.second.eval())
    }
}