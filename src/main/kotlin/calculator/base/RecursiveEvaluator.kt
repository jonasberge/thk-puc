package calculator.base

import java.lang.IllegalArgumentException
import kotlin.reflect.KClass
import kotlin.reflect.cast

abstract class RecursiveEvaluator<T, K : Any>(private val klass: KClass<K>) : Evaluator<T> {

    protected abstract fun eval(expr: K): T

    private fun eval(expr: RecursiveEvaluator<T, K>): T {
        if (klass.isInstance(expr))
            return eval(klass.cast(expr))

        throw IllegalArgumentException()
    }

    override fun eval() = eval(this)
}
