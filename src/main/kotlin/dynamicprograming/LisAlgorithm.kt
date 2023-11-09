package dynamicprograming

import kotlin.math.max

class LisAlgorithm {
    fun calcLisCount(a: IntArray): IntArray {
        val d = IntArray(size = a.size)

        for (i in 1 until a.size) {
            for (j in 0 until i) {
                if (a[j] < a[i]) {
                    d[i] = max(d[j], d[i])
                }
            }
            d[i] += 1
        }

        return d
    }

    fun calcIsSum(a: IntArray): Int {
        val preSum = IntArray(size = a.size)
        preSum[0] = a[0]
        for (i in a.indices) {
            for (j in 0 until i) {
                if (a[j] < a[i]) {
                    preSum[i] = max(preSum[i], preSum[j] + a[i])
                }
            }
        }

        return preSum.max()
    }
}
