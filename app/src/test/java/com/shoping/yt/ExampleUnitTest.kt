package com.shoping.yt

import io.reactivex.Observable
import io.reactivex.functions.Consumer
import org.junit.Test

import org.junit.Assert.*
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    var navigationIcon = intArrayOf(R.mipmap.ic_main_middle_daily,R.mipmap.ic_main_middle_game)

    @Test
    fun addition_isCorrect() {

        for (i in navigationIcon.iterator()) {
            println(i)
        }
    }
}
