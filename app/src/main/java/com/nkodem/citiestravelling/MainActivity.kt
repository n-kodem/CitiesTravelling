package com.nkodem.citiestravelling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }
    val namesId = listOf<EditText>(
        findViewById<EditText>(R.id.cityName1),
        findViewById<EditText>(R.id.cityName2),
        findViewById<EditText>(R.id.cityName3),
        findViewById<EditText>(R.id.cityName4),
        findViewById<EditText>(R.id.cityName5),
        findViewById<EditText>(R.id.cityName6),
        findViewById<EditText>(R.id.cityName7),
        findViewById<EditText>(R.id.cityName8),
        findViewById<EditText>(R.id.cityName9),
        findViewById<EditText>(R.id.cityName10),
        findViewById<EditText>(R.id.cityName11),
        findViewById<EditText>(R.id.cityName12),
        findViewById<EditText>(R.id.cityName13),
        findViewById<EditText>(R.id.cityName14),
        findViewById<EditText>(R.id.cityName15),
        findViewById<EditText>(R.id.cityName16),
    )
    val roads = listOf<EditText>(
        findViewById<EditText>(R.id.from1to2),
        findViewById<EditText>(R.id.from1to3),
        findViewById<EditText>(R.id.from1to4),
        findViewById<EditText>(R.id.from1to5),
        findViewById<EditText>(R.id.from1to6),
        findViewById<EditText>(R.id.from1to7),
        findViewById<EditText>(R.id.from1to8),
        findViewById<EditText>(R.id.from1to9),
        findViewById<EditText>(R.id.from1to10),
        findViewById<EditText>(R.id.from1to11),
        findViewById<EditText>(R.id.from1to12),
        findViewById<EditText>(R.id.from1to13),
        findViewById<EditText>(R.id.from1to14),
        findViewById<EditText>(R.id.from1to15),
        findViewById<EditText>(R.id.from1to16),
        findViewById<EditText>(R.id.from2to3),
        findViewById<EditText>(R.id.from2to4),
        findViewById<EditText>(R.id.from2to5),
        findViewById<EditText>(R.id.from2to6),
        findViewById<EditText>(R.id.from2to7),
        findViewById<EditText>(R.id.from2to8),
        findViewById<EditText>(R.id.from2to9),
        findViewById<EditText>(R.id.from2to10),
        findViewById<EditText>(R.id.from2to11),
        findViewById<EditText>(R.id.from2to12),
        findViewById<EditText>(R.id.from2to13),
        findViewById<EditText>(R.id.from2to14),
        findViewById<EditText>(R.id.from2to15),
        findViewById<EditText>(R.id.from2to16),
        findViewById<EditText>(R.id.from3to4),
        findViewById<EditText>(R.id.from3to5),
        findViewById<EditText>(R.id.from3to6),
        findViewById<EditText>(R.id.from3to7),
        findViewById<EditText>(R.id.from3to8),
        findViewById<EditText>(R.id.from3to9),
        findViewById<EditText>(R.id.from3to10),
        findViewById<EditText>(R.id.from3to11),
        findViewById<EditText>(R.id.from3to12),
        findViewById<EditText>(R.id.from3to13),
        findViewById<EditText>(R.id.from3to14),
        findViewById<EditText>(R.id.from3to15),
        findViewById<EditText>(R.id.from3to16),
        findViewById<EditText>(R.id.from4to5),
        findViewById<EditText>(R.id.from4to6),
        findViewById<EditText>(R.id.from4to7),
        findViewById<EditText>(R.id.from4to8),
        findViewById<EditText>(R.id.from4to9),
        findViewById<EditText>(R.id.from4to10),
        findViewById<EditText>(R.id.from4to11),
        findViewById<EditText>(R.id.from4to12),
        findViewById<EditText>(R.id.from4to13),
        findViewById<EditText>(R.id.from4to14),
        findViewById<EditText>(R.id.from4to15),
        findViewById<EditText>(R.id.from4to16),
        findViewById<EditText>(R.id.from5to6),
        findViewById<EditText>(R.id.from5to7),
        findViewById<EditText>(R.id.from5to8),
        findViewById<EditText>(R.id.from5to9),
        findViewById<EditText>(R.id.from5to10),
        findViewById<EditText>(R.id.from5to11),
        findViewById<EditText>(R.id.from5to12),
        findViewById<EditText>(R.id.from5to13),
        findViewById<EditText>(R.id.from5to14),
        findViewById<EditText>(R.id.from5to15),
        findViewById<EditText>(R.id.from5to16),
        findViewById<EditText>(R.id.from6to7),
        findViewById<EditText>(R.id.from6to8),
        findViewById<EditText>(R.id.from6to9),
        findViewById<EditText>(R.id.from6to10),
        findViewById<EditText>(R.id.from6to11),
        findViewById<EditText>(R.id.from6to12),
        findViewById<EditText>(R.id.from6to13),
        findViewById<EditText>(R.id.from6to14),
        findViewById<EditText>(R.id.from6to15),
        findViewById<EditText>(R.id.from6to16),
        findViewById<EditText>(R.id.from7to8),
        findViewById<EditText>(R.id.from7to9),
        findViewById<EditText>(R.id.from7to10),
        findViewById<EditText>(R.id.from7to11),
        findViewById<EditText>(R.id.from7to12),
        findViewById<EditText>(R.id.from7to13),
        findViewById<EditText>(R.id.from7to14),
        findViewById<EditText>(R.id.from7to15),
        findViewById<EditText>(R.id.from7to16),
        findViewById<EditText>(R.id.from8to9),
        findViewById<EditText>(R.id.from8to10),
        findViewById<EditText>(R.id.from8to11),
        findViewById<EditText>(R.id.from8to12),
        findViewById<EditText>(R.id.from8to13),
        findViewById<EditText>(R.id.from8to14),
        findViewById<EditText>(R.id.from8to15),
        findViewById<EditText>(R.id.from8to16),
        findViewById<EditText>(R.id.from9to10),
        findViewById<EditText>(R.id.from10to11),
        findViewById<EditText>(R.id.from10to12),
        findViewById<EditText>(R.id.from10to13),
        findViewById<EditText>(R.id.from10to14),
        findViewById<EditText>(R.id.from10to15),
        findViewById<EditText>(R.id.from10to16),
        findViewById<EditText>(R.id.from11to12),
        findViewById<EditText>(R.id.from11to13),
        findViewById<EditText>(R.id.from11to14),
        findViewById<EditText>(R.id.from11to15),
        findViewById<EditText>(R.id.from11to16),
        findViewById<EditText>(R.id.from12to13),
        findViewById<EditText>(R.id.from12to14),
        findViewById<EditText>(R.id.from12to15),
        findViewById<EditText>(R.id.from12to16),
        findViewById<EditText>(R.id.from13to14),
        findViewById<EditText>(R.id.from13to15),
        findViewById<EditText>(R.id.from13to16),
        findViewById<EditText>(R.id.from14to15),
        findViewById<EditText>(R.id.from14to16),
        findViewById<EditText>(R.id.from15to16)
    )
    fun getNames(): List<String>{
        val names: MutableList<String> = mutableListOf()
        for(name in namesId)
            names+=name.text.toString()
        return names
    }
    fun getName(number: Int): String{
        return namesId[number+1].text.toString()
    }
    fun getRoad(){

    }
}