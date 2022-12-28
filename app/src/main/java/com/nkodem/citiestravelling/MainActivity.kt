package com.nkodem.citiestravelling

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nkodem.citiestravelling.algorithms.Graph
import com.nkodem.citiestravelling.algorithms.TravellingMerchantProblem


class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val namesId = listOf<EditText>(
            findViewById<EditText>(R.id.cityName1),
            findViewById<EditText>(R.id.cityName2),
            findViewById<EditText>(R.id.cityName3),
            findViewById<EditText>(R.id.cityName4),
            findViewById<EditText>(R.id.cityName5),
            findViewById<EditText>(R.id.cityName6),
            findViewById<EditText>(R.id.cityName7),
            findViewById<EditText>(R.id.cityName8),
        )
        val roadsId = listOf<EditText>(
            findViewById<EditText>(R.id.from1to2),
            findViewById<EditText>(R.id.from1to3),
            findViewById<EditText>(R.id.from1to4),
            findViewById<EditText>(R.id.from1to5),
            findViewById<EditText>(R.id.from1to6),
            findViewById<EditText>(R.id.from1to7),
            findViewById<EditText>(R.id.from1to8),
            findViewById<EditText>(R.id.from2to3),
            findViewById<EditText>(R.id.from2to4),
            findViewById<EditText>(R.id.from2to5),
            findViewById<EditText>(R.id.from2to6),
            findViewById<EditText>(R.id.from2to7),
            findViewById<EditText>(R.id.from2to8),
            findViewById<EditText>(R.id.from3to4),
            findViewById<EditText>(R.id.from3to5),
            findViewById<EditText>(R.id.from3to6),
            findViewById<EditText>(R.id.from3to7),
            findViewById<EditText>(R.id.from3to8),
            findViewById<EditText>(R.id.from4to5),
            findViewById<EditText>(R.id.from4to6),
            findViewById<EditText>(R.id.from4to7),
            findViewById<EditText>(R.id.from4to8),
            findViewById<EditText>(R.id.from5to6),
            findViewById<EditText>(R.id.from5to7),
            findViewById<EditText>(R.id.from5to8),
            findViewById<EditText>(R.id.from6to7),
            findViewById<EditText>(R.id.from6to8),
            findViewById<EditText>(R.id.from7to8),

            )

        fun getNames(): List<String>{
            val names: MutableList<String> = mutableListOf()
            for(name in namesId)
                names+=name.text.toString()
            return names
        }
        fun getName(number: Int): String{
            return namesId[number].text.toString()
        }
        fun getRoad(number: Int): String{
            return roadsId[number].text.toString()
        }
        fun getRoads():List<String>{
            val roads: MutableList<String> = mutableListOf()
            for(road in roadsId)
                roads+=road.text.toString()
            return roads
        }


        findViewById<FloatingActionButton>(R.id.run).setOnClickListener {
            var graph = Graph()

            // NORMAL
            graph.addNewEdge(getName(0),getName(1),getRoad(0).toInt())
            graph.addNewEdge(getName(0),getName(2),getRoad(1).toInt())
            graph.addNewEdge(getName(0),getName(3),getRoad(2).toInt())
            graph.addNewEdge(getName(0),getName(4),getRoad(3).toInt())
            graph.addNewEdge(getName(0),getName(5),getRoad(4).toInt())
            graph.addNewEdge(getName(0),getName(6),getRoad(5).toInt())
            graph.addNewEdge(getName(0),getName(7),getRoad(6).toInt())

            graph.addNewEdge(getName(1),getName(2),getRoad(7).toInt())
            graph.addNewEdge(getName(1),getName(3),getRoad(8).toInt())
            graph.addNewEdge(getName(1),getName(4),getRoad(9).toInt())
            graph.addNewEdge(getName(1),getName(5),getRoad(10).toInt())
            graph.addNewEdge(getName(1),getName(6),getRoad(11).toInt())
            graph.addNewEdge(getName(1),getName(7),getRoad(12).toInt())

            graph.addNewEdge(getName(2),getName(3),getRoad(13).toInt())
            graph.addNewEdge(getName(2),getName(4),getRoad(14).toInt())
            graph.addNewEdge(getName(2),getName(5),getRoad(15).toInt())
            graph.addNewEdge(getName(2),getName(6),getRoad(16).toInt())
            graph.addNewEdge(getName(2),getName(7),getRoad(17).toInt())

            graph.addNewEdge(getName(3),getName(4),getRoad(18).toInt())
            graph.addNewEdge(getName(3),getName(5),getRoad(19).toInt())
            graph.addNewEdge(getName(3),getName(6),getRoad(20).toInt())
            graph.addNewEdge(getName(3),getName(7),getRoad(21).toInt())

            graph.addNewEdge(getName(4),getName(5),getRoad(22).toInt())
            graph.addNewEdge(getName(4),getName(6),getRoad(23).toInt())
            graph.addNewEdge(getName(4),getName(7),getRoad(24).toInt())

            graph.addNewEdge(getName(5),getName(6),getRoad(25).toInt())
            graph.addNewEdge(getName(5),getName(7),getRoad(26).toInt())

            graph.addNewEdge(getName(6),getName(7),getRoad(27).toInt())


            // Second dir
            graph.addNewEdge(getName(1),getName(0),getRoad(0).toInt())
            graph.addNewEdge(getName(2),getName(0),getRoad(1).toInt())
            graph.addNewEdge(getName(3),getName(0),getRoad(2).toInt())
            graph.addNewEdge(getName(4),getName(0),getRoad(3).toInt())
            graph.addNewEdge(getName(5),getName(0),getRoad(4).toInt())
            graph.addNewEdge(getName(6),getName(0),getRoad(5).toInt())
            graph.addNewEdge(getName(7),getName(0),getRoad(6).toInt())

            graph.addNewEdge(getName(2),getName(1),getRoad(7).toInt())
            graph.addNewEdge(getName(3),getName(1),getRoad(8).toInt())
            graph.addNewEdge(getName(4),getName(1),getRoad(9).toInt())
            graph.addNewEdge(getName(5),getName(1),getRoad(10).toInt())
            graph.addNewEdge(getName(6),getName(1),getRoad(11).toInt())
            graph.addNewEdge(getName(7),getName(1),getRoad(12).toInt())

            graph.addNewEdge(getName(3),getName(2),getRoad(13).toInt())
            graph.addNewEdge(getName(4),getName(2),getRoad(14).toInt())
            graph.addNewEdge(getName(5),getName(2),getRoad(15).toInt())
            graph.addNewEdge(getName(6),getName(2),getRoad(16).toInt())
            graph.addNewEdge(getName(7),getName(2),getRoad(17).toInt())

            graph.addNewEdge(getName(4),getName(3),getRoad(18).toInt())
            graph.addNewEdge(getName(5),getName(3),getRoad(19).toInt())
            graph.addNewEdge(getName(6),getName(3),getRoad(20).toInt())
            graph.addNewEdge(getName(7),getName(3),getRoad(21).toInt())

            graph.addNewEdge(getName(5),getName(4),getRoad(22).toInt())
            graph.addNewEdge(getName(6),getName(4),getRoad(23).toInt())
            graph.addNewEdge(getName(7),getName(4),getRoad(24).toInt())

            graph.addNewEdge(getName(6),getName(5),getRoad(25).toInt())
            graph.addNewEdge(getName(7),getName(5),getRoad(26).toInt())

            graph.addNewEdge(getName(7),getName(6),getRoad(27).toInt())




            TravellingMerchantProblem().solve(getNames(),graph)
        }


}



}