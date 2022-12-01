package com.nkodem.citiestravelling.algorithms

import kotlin.math.min

class TravellingMerchantProblem {
    // create findHamiltonianCycle() method to get minimum weighted cycle
    fun findHamiltonianCycle(
        distance: Array<IntArray>,
        visitCity: BooleanArray,
        currPos: Int,
        cities: MutableList<Int>,
        count: Int,
        cost: Int,
        hamiltonianCycle: Int = Int.MAX_VALUE,
    ): Int {
        var hamiltonianCycle = hamiltonianCycle
        if (count == cities.size && distance[currPos][0] > 0) {
            hamiltonianCycle = min(hamiltonianCycle, cost + distance[currPos][0])
            return hamiltonianCycle
        }

        // BACKTRACKING STEP
        for (i in 0 until cities.size) {
            if (!visitCity[i] && distance[currPos][i] > 0) {

                // Mark as visited
                visitCity[i] = true
                hamiltonianCycle = findHamiltonianCycle(distance,
                    visitCity,
                    i,
                    cities,
                    count + 1,
                    cost + distance[currPos][i],
                    hamiltonianCycle)

                // Mark ith node as unvisited
                visitCity[i] = false
            }
        }
        return hamiltonianCycle
    }

    // main() method start
//    @JvmStatic
//    fun main(args: Array<String>) {
//        val cities: Int
//
//        //create scanner class object to get input from user
//        val sc = Scanner(System.`in`)
//
//        // get total number of cities from the user
//        println("Enter total number of cities ")
//        cities = sc.nextInt()
//
//
//        //get distance of cities from the user
//        val distance = Array(cities) {
//            IntArray(cities)
//        }
//        for (i in 0 until cities) {
//            for (j in 0 until cities) {
//                println("Distance from city" + (i + 1) + " to city" + (j + 1) + ": ")
//                distance[i][j] = sc.nextInt()
//            }
//        }
//
//        // create an array of type boolean to check if a node has been visited or not
//        val visitCity = BooleanArray(cities)
//
//        // by default, we make the first city visited
//        visitCity[0] = true
//        var hamiltonianCycle = Int.MAX_VALUE
//
//        // call findHamiltonianCycle() method that returns the minimum weight Hamiltonian Cycle
//        hamiltonianCycle =
//            findHamiltonianCycle(distance, visitCity, 0, cities, 1, 0)
//
//        // print the minimum weighted Hamiltonian Cycle
//        println(hamiltonianCycle)
//    }
//    }
}