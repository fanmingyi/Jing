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
    @Test
    fun addition_isCorrect() {

        Observable.timer(2, TimeUnit.SECONDS).subscribe { aLong ->
            var aLong = aLong
            aLong = 23L
        }//                observeOn(AndroidSchedulers.mainThread()).
    }
}
