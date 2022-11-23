package com.nkodem.citiestravelling.algorithms

class DijkstraSearcher {
    fun findShortestPath(edges: List<Edge>, source: Node, target: Node): ShortestPathResult {

        val distancesFromSource = mutableMapOf<Node, Int>()
        val prevHopNodes = mutableMapOf<Node, Node?>()
        val distinctNodesList = findDistinctNodes(edges)

        distinctNodesList.forEach { v ->
            distancesFromSource[v] = Integer.MAX_VALUE
            prevHopNodes[v] = null
        }
        distancesFromSource[source] = 0

        while (distinctNodesList.isNotEmpty()) {
            val u = distinctNodesList.minByOrNull { distancesFromSource[it] ?: 0 }
            distinctNodesList.remove(u)

            // if found shortest path
            if (u == target) {
                break
            }

            edges
                .filter { it.node1 == u }
                .forEach { edge ->
                    val v = edge.node2
                    val rootToNeighborLen = (distancesFromSource[u] ?: 0) + edge.distance
                    if (rootToNeighborLen < (distancesFromSource[v] ?: 0)) {
                        distancesFromSource[v] = rootToNeighborLen
                        prevHopNodes[v] = u
                    }
                }
        }

        return ShortestPathResult(prevHopNodes, distancesFromSource, source, target)
    }

    private fun findDistinctNodes(edges: List<Edge>): MutableSet<Node> {
        val nodes = mutableSetOf<Node>()
        edges.forEach {
            nodes.add(it.node1)
            nodes.add(it.node2)
        }
        return nodes
    }
}