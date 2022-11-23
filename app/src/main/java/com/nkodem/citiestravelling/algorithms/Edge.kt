package com.nkodem.citiestravelling.algorithms

interface Node

data class Edge(val node1: Node, val node2: Node, val distance: Int)


class ShortestPathResult(val prev: Map<Node, Node?>, val dist: Map<Node, Int>, val source: Node, val destination: Node) {

    fun shortestPath(from: Node = source, to: Node = destination, list: List<Node> = emptyList()): List<Node> {
        val last = prev[to] ?: return if (from == to) {
            list + to
        } else {
            emptyList()
        }
        return shortestPath(from, last, list) + to
    }

    fun shortestDistance(): Int? {
        val shortest = dist[destination]
        if (shortest == Integer.MAX_VALUE) {
            return null
        }
        return shortest
    }
}