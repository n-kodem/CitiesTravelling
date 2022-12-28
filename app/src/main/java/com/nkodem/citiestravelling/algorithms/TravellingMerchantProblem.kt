package com.nkodem.citiestravelling.algorithms

class TravellingMerchantProblem {

    fun <T> List<T>.permutations(): List<List<T>> = if(isEmpty()) listOf(emptyList()) else  mutableListOf<List<T>>().also{result ->
        for(i in this.indices){
            (this - this[i]).permutations().forEach{
                result.add(it + this[i])
            }
        }
    }

    fun solve(cities:List<String>, graph:Graph) {
        val routes = cities.permutations()
        var shortestRoute = emptyList<String>()
        var shortestDistance = Double.MAX_VALUE
        for (route in routes) {
            val distance = graph.getEdgeDistance(route[0],route[1])
            if (distance != -1) {
                if (distance<shortestDistance){
                    shortestRoute = route
                    shortestDistance = distance.toDouble()
                }
            }
        }
        var dst = 0
        for (city in 0..shortestRoute.size-2){
            println(shortestRoute[(shortestRoute.size-1)-city]
                    +" "+
                    graph.getEdgeDistance(shortestRoute[(shortestRoute.size-1)-city],shortestRoute[shortestRoute.indexOf(shortestRoute[(shortestRoute.size-1)-city])-1]))
            dst+=graph.getEdgeDistance(shortestRoute[(shortestRoute.size-1)-city],shortestRoute[shortestRoute.indexOf(shortestRoute[(shortestRoute.size-1)-city])-1])
        }
        println(shortestRoute[0])
        println("Total distance: $dst")
    }
}