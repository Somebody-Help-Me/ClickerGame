package com.yh.clicker

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import java.math.BigInteger
import java.text.DecimalFormat
import java.util.Timer
import java.util.TimerTask

class MainActivity : ComponentActivity() {
    var credit: BigInteger = BigInteger.ZERO
    var count: Long = 100

    lateinit var bank: TextView
    lateinit var currentdimention: TextView
    lateinit var tickBtn: Button
    lateinit var buy: View
    var changeDime = ArrayList<Button>()
    var purchaseTier = ArrayList<Button>()

    lateinit var arr: Array<Array<Array<BigInteger>>>
    lateinit var modi: Array<Array<Array<BigInteger>>>
    lateinit var show: Array<Array<TextView>>

    var dimention = 5
    var currentDi = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dimentionSet(dimention)

        getBtns()
        addBtnListener()

        getShow()

        val timer = Timer()
        val task = object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    tick()
                    timer.schedule(this, count)
                }
            }
        }

        // 1초 후부터 count/1000초마다 작업(task)을 실행합니다.
        timer.schedule(task, 0, count)

    }

    private fun getBtns() {
        tickBtn = findViewById(R.id.tickBtn)
        changeDime.add(findViewById(R.id.first))
        changeDime.add(findViewById(R.id.second))
        changeDime.add(findViewById(R.id.third))
        changeDime.add(findViewById(R.id.fourth))
        changeDime.add(findViewById(R.id.fifth))
        for (i in 1..5) purchaseTier.add(findViewByStringId("pt$i"))


    }

    inline fun <reified T : View> findViewByStringId(s: String): T {
        return findViewById(resources.getIdentifier(s, "id", packageName))
    }

    private fun addBtnListener() {
        tickBtn.setOnClickListener { tick() }
        for (btn in changeDime) btn.setOnClickListener(changeDimention)
        for (btn in purchaseTier) btn.setOnClickListener(purchase)

    }

    var changeDimention = View.OnClickListener { v ->
        if (buy.visibility == View.GONE) {
            buy.visibility = View.VISIBLE
            cd(v)
        } else if (changeDime.indexOf(v as Button) != currentDi) cd(v)
        else buy.visibility = View.GONE
    }

    private fun cd(v: View) {
        when (v) {
            changeDime[0] -> currentDi = 0
            changeDime[1] -> currentDi = 1
            changeDime[2] -> currentDi = 2
            changeDime[3] -> currentDi = 3
            changeDime[4] -> currentDi = 4
        }
        currentdimention.text = currentDi.toString()
        refresh()
    }

    var purchase = View.OnClickListener { v: View? ->
        var tier = 0
        when (v) {
            purchaseTier[0] -> tier = 0
            purchaseTier[1] -> tier = 1
            purchaseTier[2] -> tier = 2
            purchaseTier[3] -> tier = 3
            purchaseTier[4] -> tier = 4
        }
        if (credit > arr[currentDi][tier][3]) {
            credit -= arr[currentDi][tier][3]
            arr[currentDi][tier][1] += BigInteger.ONE
            arr[currentDi][tier][2] += BigInteger.ONE
            if (arr[currentDi][tier][2].remainder(BigInteger.TEN)
                    .toInt() == 0
            ) modi[currentDi][tier][0] *= BigInteger("2")
        }
        refresh()
    }

    private fun getShow() {
        buy = findViewById(R.id.buyLayout)
        bank = findViewById(R.id.credit)
        currentdimention = findViewById(R.id.dimention)
        show = Array(dimention) { Array(4) { findViewById(R.id.show0_0) } }
        for (i in 0 until dimention) {
            for (j in 0..3) show[i][j] = findViewByStringId("show${i}_$j")
        }
        refresh()
    }

    private fun dimentionSet(n: Int = 1) {
        arr = Array(n) { Array(n) { Array(4) { BigInteger.ZERO } } }
        arr[0][0][0] = BigInteger.ONE
        arr[0][0][1] = BigInteger.ONE
        arr[0][0][2] = BigInteger.ONE
        modi = Array(n) { Array(n) { Array(2) { BigInteger.ONE } } }
    }

    fun tick() {
        for (i in 0 until dimention) {
            credit += arr[i][0][0]
            for (j in 1 until dimention) arr[i][j - 1][1] += arr[i][j][0]
            for (j in 0 until dimention) arr[i][j][0] = arr[i][j][1].multiply(modi[i][j][0])
        }
        refresh()
    }

    fun refresh() {
        bank.text = formatter(credit)
        for (i in 0 until dimention) {
            for (j in 0..3) show[i][j].text = formatter(arr[currentDi][i][j])
        }
    }

    fun formatter(n: BigInteger): String {
        if (n.compareTo(BigInteger.valueOf(10L).pow(5)) < 0)
            return DecimalFormat("#,###").format(n)
        else return DecimalFormat("0.##E0").format(n)


    }
}

