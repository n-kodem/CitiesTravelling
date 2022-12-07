package com.nkodem.citiestravelling.algorithms

import kotlin.math.min
import kotlin.sequences.*

class TravellingMerchantProblem {

    fun <T> List<T>.permutations(): List<List<T>> = if(isEmpty()) listOf(emptyList()) else  mutableListOf<List<T>>().also{result ->
        for(i in this.indices){
            (this - this[i]).permutations().forEach{
                result.add(it + this[i])
            }
        }
    }

        fun solve(cities:List<String>) {
            val routes = cities.permutations()
            var shortestRoute = emptyList<String>()
            var shortestDistance = Double.MAX_VALUE
            for (route in routes) {
                val distance = Graph().GetPDis(route[0],route[1])
                if (distance != null) {
                    if (distance<shortestDistance){
                        shortestRoute = route
                        shortestDistance = distance.toDouble()
                    }
                }
            }
            for (city in shortestRoute){
                println(city)

            }
            println("Total distance: $shortestDistance")
        }
}