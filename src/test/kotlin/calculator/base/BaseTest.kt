package calculator.base

import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseTest<C> {

    protected abstract val cases: List<C>

    private fun generateCases() : Stream<Arguments> {
        return cases.stream().map { Arguments.of(it) }
    }

    abstract fun testCase(case: C)

    @ParameterizedTest
    @MethodSource("generateCases")
    protected fun runCase(case: C) {
        testCase(case)
    }
}
