package scientifik.kmath.operations

import kotlin.math.pow

/**
 * Advanced Number-like field that implements basic operations
 */
interface ExtendedFieldOperations<T> :
    FieldOperations<T>,
    TrigonometricOperations<T>,
    PowerOperations<T>,
    ExponentialOperations<T>

interface ExtendedField<T> : ExtendedFieldOperations<T>, Field<T>

/**
 * Real field element wrapping double.
 *
 * TODO inline does not work due to compiler bug. Waiting for fix for KT-27586
 */
inline class Real(val value: Double) : FieldElement<Double, Real, RealField> {
    override fun unwrap(): Double = value

    override fun Double.wrap(): Real = Real(value)

    override val context get() = RealField

    companion object
}

/**
 * A field for double without boxing. Does not produce appropriate field element
 */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
object RealField : Field<Double>, ExtendedFieldOperations<Double>, Norm<Double, Double> {
    override val zero: Double = 0.0
    override fun add(a: Double, b: Double): Double = a + b
    override fun multiply(a: Double, b: Double): Double = a * b
    override fun multiply(a: Double, k: Number): Double = a * k.toDouble()

    override val one: Double = 1.0
    override fun divide(a: Double, b: Double): Double = a / b

    override fun sin(arg: Double): Double = kotlin.math.sin(arg)
    override fun cos(arg: Double): Double = kotlin.math.cos(arg)

    override fun power(arg: Double, pow: Number): Double = arg.pow(pow.toDouble())

    override fun exp(arg: Double): Double = kotlin.math.exp(arg)
    override fun ln(arg: Double): Double = kotlin.math.ln(arg)

    override fun norm(arg: Double): Double = kotlin.math.abs(arg)

    override fun Double.unaryMinus(): Double = -this

    override fun Double.minus(b: Double): Double = this - b
}

/**
 * A field for [Int] without boxing. Does not produce corresponding field element
 */
object IntRing : Ring<Int>, Norm<Int, Int> {
    override val zero: Int = 0
    override fun add(a: Int, b: Int): Int = a + b
    override fun multiply(a: Int, b: Int): Int = a * b
    override fun multiply(a: Int, k: Number): Int = (k * a)
    override val one: Int = 1

    override fun norm(arg: Int): Int = arg
}

/**
 * A field for [Short] without boxing. Does not produce appropriate field element
 */
object ShortRing : Ring<Short>, Norm<Short, Short> {
    override val zero: Short = 0
    override fun add(a: Short, b: Short): Short = (a + b).toShort()
    override fun multiply(a: Short, b: Short): Short = (a * b).toShort()
    override fun multiply(a: Short, k: Number): Short = (a * k)
    override val one: Short = 1

    override fun norm(arg: Short): Short = arg
}

/**
 * A field for [Byte] values
 */
object ByteRing : Ring<Byte>, Norm<Byte, Byte> {
    override val zero: Byte = 0
    override fun add(a: Byte, b: Byte): Byte = (a + b).toByte()
    override fun multiply(a: Byte, b: Byte): Byte = (a * b).toByte()
    override fun multiply(a: Byte, k: Number): Byte = (a * k)
    override val one: Byte = 1

    override fun norm(arg: Byte): Byte = arg
}

/**
 * A field for [Long] values
 */
object LongRing : Ring<Long>, Norm<Long, Long> {
    override val zero: Long = 0
    override fun add(a: Long, b: Long): Long = (a + b)
    override fun multiply(a: Long, b: Long): Long = (a * b)
    override fun multiply(a: Long, k: Number): Long = (a * k)
    override val one: Long = 1

    override fun norm(arg: Long): Long = arg
}