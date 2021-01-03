package com.codexdroid.codexdroiddevcv.extra_utility

import android.view.animation.Interpolator
import kotlin.math.cos
import kotlin.math.pow

class BounceInterpolator(var amp : Double,var freq : Double) : Interpolator {



    override fun getInterpolation(input: Float): Float {

        return (-1 * Math.E.pow(-input / amp) * cos(freq * input) +1).toFloat()

    }
}

