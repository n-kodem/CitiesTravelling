package com.nkodem.citiestravelling.algorithms

class TravellingMerchantProblem {

    fun <T> List<T>.permutations(): List<List<T>> = if(isEmpty()) listOf(emptyList()) else  mutableListOf<List<T>>().also{result ->
        for(i in this.indices){
            (this - this[i]).permutations().forEach{
                result.add(it + this[i])
            }
        }
    }

    fun solve(cities:List<String>, graph:Graph): Pair<List<String>,List<Int>> {
        // Permutacja wszystkich miast
        val routes = cities.permutations()

//        for(i in routes)
//            println(i.toString())

        // Zmienna do przechowania najkrótrzej drogi
        var shortestRoute = emptyList<String>()
        // Zmienna do przechowania najmniejszego dystansu
        var shortestDistance = Double.MAX_VALUE
        // Sprawdzamy każdą z opcji
        for (route in routes) {
            val distance = graph.getEdgeDistance(route[0],route[1]) + graph.getEdgeDistance(route[1],route[2]) +graph.getEdgeDistance(route[2],route[3]) +graph.getEdgeDistance(route[3],route[4]) +graph.getEdgeDistance(route[4],route[5]) +graph.getEdgeDistance(route[5],route[6]) +graph.getEdgeDistance(route[6],route[7])
            println("dystans: $distance")
            if (distance != -1) {
                if (distance<shortestDistance){
                    shortestRoute = route
                    shortestDistance = distance.toDouble()
                }
            }
        }
        var dst:MutableList<Int> = mutableListOf()
        for (city in 0..shortestRoute.size-2){
            println(shortestRoute[(shortestRoute.size-1)-city]
                    +" "+
                    graph.getEdgeDistance(shortestRoute[(shortestRoute.size-1)-city],shortestRoute[shortestRoute.indexOf(shortestRoute[(shortestRoute.size-1)-city])-1]))
            dst+=graph.getEdgeDistance(shortestRoute[(shortestRoute.size-1)-city],shortestRoute[shortestRoute.indexOf(shortestRoute[(shortestRoute.size-1)-city])-1])
        }
        println(shortestRoute[0])
        println("Total distance: ${dst.sum()}")
        return Pair<List<String>,List<Int>>(shortestRoute.reversed(), dst)
    }
}