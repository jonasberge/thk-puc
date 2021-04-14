package calculator.base

import org.junit.jupiter.api.Assertions.*

abstract class BaseConverterTest<E, A : Evaluator<E>, B : Evaluator<E>> : BaseTest<Pair<A, B>>() {

    abstract val converter: Converter<A, B>

    override fun testCase(case: Pair<A, B>) {
        val result = converter.conv(case.first)

        println("original:  ${case.first.eval()} = ${case.first}")
        println("converted: ${result.eval()} = $result")
        println("expected:  ${case.second.eval()} = ${case.second}")

        assertEquals(case.second.eval(), case.first.eval())
        assertEquals(case.second.eval(), result.eval())

        assertEquals(case.second, result)
    }
}