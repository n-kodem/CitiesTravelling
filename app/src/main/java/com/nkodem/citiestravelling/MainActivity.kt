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
import com.nkodem.citiestravelling.algorithms.TravellingMerchantProblem


class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<FloatingActionButton>(R.id.run).setOnClickListener {



            val cities: MutableList<Int> = arrayListOf(1,2,3,4,5)


//        // get total number of cities from the user
//        println("Enter total number of cities ")
//        cities = sc.nextInt()


//        //get distance of cities from the user
//        val distance = Array(cities) {
//            IntArray(cities)
//        }
//        for (i in 0 until cities.size) {
//            for (j in 0 until cities.size) {
//                println("Distance from city" + (i + 1) + " to city" + (j + 1) + ": ")
//                distance[i][j] = sc.nextInt()
//            }
//        }

        // create an array of type boolean to check if a node has been visited or not
//        val visitCity = BooleanArray(cities)

        // by default, we make the first city visited
//        visitCity[0] = true
//        var hamiltonianCycle = Int.MAX_VALUE

        // call findHamiltonianCycle() method that returns the minimum weight Hamiltonian Cycle
//        hamiltonianCycle =
//
//            TravellingMerchantProblem().findHamiltonianCycle(distance, visitCity, 0, cities, 1, 0)
//
//        // print the minimum weighted Hamiltonian Cycle
//        println(hamiltonianCycle)
        }



    val namesId = listOf<EditText>(
        this.findViewById(R.id.cityName1) as EditText,
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
        return namesId[number+1].text.toString()
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


}
}