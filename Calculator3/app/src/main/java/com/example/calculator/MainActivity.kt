package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import kotlin.math.log

class MainActivity : AppCompatActivity(), OnClickListener {
    var textsize = 45f
    private lateinit var one: Button
    private lateinit var two: Button
    private lateinit var three: Button
    private lateinit var four: Button
    private lateinit var five: Button
    private lateinit var six: Button
    private lateinit var seven: Button
    private lateinit var eight: Button
    private lateinit var nine: Button
    private lateinit var zero: Button

    private lateinit var point: Button
    private lateinit var clear: Button
    private lateinit var backspace:Button

    private lateinit var div: Button
    private lateinit var multiply: Button
    private lateinit var plus: Button
    private lateinit var minus: Button
    private lateinit var equal: Button


    private lateinit var operand: TextView
    private lateinit var result: TextView

    private var isPoint = true
    private var isSimvol = false

    fun initUI() {
        one = findViewById(R.id.bir)
        two = findViewById(R.id.ikki)
        three = findViewById(R.id.uch)
        four = findViewById(R.id.tort)
        five = findViewById(R.id.besh)
        six = findViewById(R.id.olti)
        seven = findViewById(R.id.yetti)
        eight = findViewById(R.id.sakkiz)
        nine = findViewById(R.id.toqqiz)
        zero = findViewById(R.id.nol)

        point = findViewById(R.id.nuqta)
        clear = findViewById(R.id.C)
        backspace = findViewById(R.id.ochirish)
        div = findViewById(R.id.bolish)
        multiply = findViewById(R.id.kopaytirish)
        plus = findViewById(R.id.qoshish)
        minus = findViewById(R.id.ayirish)
        equal = findViewById<Button>(R.id.tenglik)


        operand = findViewById(R.id.operand)
        result = findViewById(R.id.result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        one.setOnClickListener(this)
        two.setOnClickListener(this)
        three.setOnClickListener(this)
        four.setOnClickListener(this)
        five.setOnClickListener(this)
        six.setOnClickListener(this)
        seven.setOnClickListener(this)
        eight.setOnClickListener(this)
        nine.setOnClickListener(this)
        zero.setOnClickListener(this)
        clear.setOnClickListener {
            operand.text = "0"
            result.text = ""
            operand.setTextSize(TypedValue.COMPLEX_UNIT_SP,50f)
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP,50f)
            textsize = 45f
            isPoint = true
            isSimvol = true
        }
        point.setOnClickListener {
            if (isPoint) {
                operand.text = operand.text.toString() + "."
                isPoint = false
            }
        }
        div.setOnClickListener { addSimvol(div.text.toString())
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP,50f)}
        multiply.setOnClickListener { addSimvol(multiply.text.toString())
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP,50f)}
        plus.setOnClickListener { addSimvol(plus.text.toString())
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP,50f)}
        minus.setOnClickListener { addSimvol(minus.text.toString())
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP,50f)}
        equal.setOnClickListener {
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP,70f)
            if(result.text.length > 9){
                result.setTextSize(TypedValue.COMPLEX_UNIT_SP,50f)
            }
        }
        backspace.setOnClickListener {
            var a = ""
            for(i in 0..operand.text.length-2){
                a += operand.text[i]
            }
            operand.text = a
            result.text = calculate()
        }
    }


    private fun calculate():String{
        var list = createArr(operand.text.toString())

        var res = ""
        for (i in hisobla1(list)){
            res += i
        }
        return res
    }

    private fun createArr(s:String):MutableList<Any>{
        var list = mutableListOf<Any>()
        var temp = ""
        for (i in s){
            if (i.isDigit() || i == '.'){
                temp += i
            }
            else{
                list.add(temp)
                temp = ""
                list.add(i)
            }
        }
        if (temp.isNotEmpty()){
            list.add(temp.toFloat())
        }
        return list
    }

    fun hisobla1(l: MutableList<Any>): MutableList<Any> {
        var list = l
        var i = 0
        while (list.contains('/') || list.contains('x')) {

            if (list[i] == 'x' || list[i] == '/') {
                var old = list[i - 1].toString().toFloat()
                var key = list[i + 1].toString().toFloat()
                var amal = list[i]
                var res = 0f
                when (amal) {
                    '/' -> {
                        res = old / key
                    }
                    'x' -> {
                        res = old * key
                    }
                }
                list.set(i-1,res)
                list.removeAt(i)
                list.removeAt(i)
                i = i-2
            }
            i++
        }
        while (list.contains('-') || list.contains('+')) {

            if (list[i] == '-' || list[i] == '+') {
                var old = list[i - 1].toString().toFloat()
                var key = list[i + 1].toString().toFloat()
                var amal = list[i]
                var res = 0f
                when (amal) {
                    '+' -> {
                        res = old + key
                    }
                    '-' -> {
                        res = old - key
                    }
                }
                list.set(i-1,res)
                list.removeAt(i)
                list.removeAt(i)
                i = i-2
            }
            i++
        }
        Log.d("TAG", list.toString())

        return list
    }

    override fun onClick(view: View?) {
        val btn = findViewById<Button>(view!!.id)
        if (operand.text == "0") {
            operand.text = ""
        }

        if (operand.text.length <= 19){
            operand.text = operand.text.toString() + btn.text
            isSimvol = true
            result.text=calculate()
//            result.setTextSize(TypedValue.COMPLEX_UNIT_SP,50f)
            if(operand.text.length >= 12){
                operand.setTextSize(TypedValue.COMPLEX_UNIT_SP,textsize)
                textsize -= 2f

            }
        }






    }
    private fun addSimvol(simvol: String) {
        if (isSimvol) {
            operand.text = operand.text.toString() + simvol
            isSimvol = false
        } else {
            operand.text = operand.text.dropLast(1).toString() + simvol
        }

    }

}