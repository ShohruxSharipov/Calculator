package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(),OnClickListener {

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

    private lateinit var div: Button
    private lateinit var multiply: Button
    private lateinit var plus: Button
    private lateinit var minus: Button


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
        div = findViewById(R.id.bolish)
        multiply = findViewById(R.id.kopaytirish)
        plus = findViewById(R.id.qoshish)
        minus = findViewById(R.id.ayirish)


        operand = findViewById(R.id.operand)
        result = findViewById(R.id.result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(view: View?) {
        val btn = findViewById<Button>(view!!.id)
        if (operand.text == "0") {
            operand.text = ""
        }
        operand.text = operand.text.toString() + btn.text
    }
}