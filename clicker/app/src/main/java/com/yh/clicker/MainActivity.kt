package com.yh.clicker

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yh.clicker.ui.theme.ClickerTheme
import java.math.BigInteger
import java.util.Timer
import java.util.TimerTask

class MainActivity : ComponentActivity() {
    var credit: BigInteger = BigInteger.ZERO
    lateinit var tickBtn: Button
    lateinit var arr: Array<Array<BigInteger>>
    lateinit var arr0: Array<Array<TextView>>
    lateinit var arr1: Array<Array<TextView>>
    lateinit var arr2: Array<Array<TextView>>
    lateinit var arr3: Array<Array<TextView>>

    var dimention = 5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tickBtn = findViewById(R.id.tickBtn)
        tickBtn.setOnClickListener { tick() }
        dimentionSet(dimention)


//        val timer = Timer()
//        val task = object : TimerTask() {
//            override fun run() {
//                tick()
//            }
//        }
//
//        // 1초 후부터 5초마다 작업(task)을 실행합니다.
//        timer.schedule(task, 0, 5000)


    }

    private fun dimentionSet(n: Int = 1) {
        arr = Array(n) { Array<BigInteger>(n) { BigInteger.ZERO } }
        arr[0][0] = BigInteger("1")
    }

    fun tick() {
        for (i in 0 until dimention) {
            credit.add(arr[i][0])
            for (j in 1 until dimention) arr[i][j - 1].add(arr[i][j])
        }

    }
}

