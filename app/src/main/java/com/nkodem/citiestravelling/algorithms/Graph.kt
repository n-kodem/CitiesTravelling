package com.nkodem.citiestravelling.algorithms

import kotlin.math.abs
import kotlin.collections.*

class Graph {
    data class IntNode(val i: Any) : Node
    var nodes: List<Edge> = listOf()

    fun addNewEdge(source:Int,destination:Int){
        nodes = nodes + Edge(IntNode(source), IntNode(destination), abs(destination-source))
    }

    fun containsEdge(source: Int,destination: Int):Boolean{
        for (edge in nodes)
            if (edge.node1==IntNode(source) && edge.node2==IntNode(destination))
                return true
        return false
    }

    fun findP(source:Any,destination:Any): String{
        val res = DijkstraSearcher().findShortestPath(nodes,IntNode(source),IntNode(destination))

        if (res.shortestPath().isEmpty())
            return "No path available"

        var result: String =""
        for(node in res.shortestPath())
            result+=" ${(node as IntNode).i} ->"
        return "Path found: ${result.substring(0,result.length-3)}"
    }
    fun GetPDis(source:Any,destination:Any): Int?{
        val res = DijkstraSearcher().findShortestPath(nodes,IntNode(source),IntNode(destination))

        if (res.shortestPath().isEmpty())
            return null
        var reslist = res.dist.toList()
        var sum = 0
        for (e in reslist)
            sum+=e.toList()[0].toString().toInt()
        return sum

    }
}