package com.yh.clicker

import android.os.Bundle
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
import java.util.Timer
import java.util.TimerTask

class MainActivity : ComponentActivity() {

    lateinit var asset:Array<IntArray>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dimentionSet(5)
        for(row in asset){
            for(col in row){
                println(col)
            }
        }

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

    private fun dimentionSet(n:Int=1){
        asset=Array(n){IntArray(n)}
        asset[0][1]=1
    }

    fun tick(){

    }
}

