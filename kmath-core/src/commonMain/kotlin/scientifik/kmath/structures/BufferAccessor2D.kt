package scientifik.kmath.structures

import kotlin.reflect.KClass

/**
 * A context that allows to operate on a [MutableBuffer] as on 2d array
 */
class BufferAccessor2D<T : Any>(val type: KClass<T>, val rowNum: Int, val colNum: Int) {

    inline operator fun Buffer<T>.get(i: Int, j: Int) = get(i + colNum * j)

    inline operator fun MutableBuffer<T>.set(i: Int, j: Int, value: T) {
        set(i + colNum * j, value)
    }

    inline fun create(init: (i: Int, j: Int) -> T) =
        MutableBuffer.auto(type, rowNum * colNum) { offset -> init(offset / colNum, offset % colNum) }

    fun create(mat: Structure2D<T>) = create { i, j -> mat[i, j] }

    //TODO optimize wrapper
    fun MutableBuffer<T>.collect(): Structure2D<T> =
        NDStructure.auto(type, rowNum, colNum) { (i, j) -> get(i, j) }.as2D()


    inner class Row(val buffer: MutableBuffer<T>, val rowIndex: Int) : MutableBuffer<T> {
        override val size: Int get() = colNum

        override fun get(index: Int): T = buffer[rowIndex, index]

        override fun set(index: Int, value: T) {
            buffer[rowIndex, index] = value
        }

        override fun copy(): MutableBuffer<T> = MutableBuffer.auto(type, colNum) { get(it) }

        override fun iterator(): Iterator<T> = (0 until colNum).map(::get).iterator()

    }

    /**
     * Get row
     */
    fun MutableBuffer<T>.row(i: Int) = Row(this, i)
}