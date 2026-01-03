package main.groovy.aoc.y2025.Day11

// find all simple paths from 'you' to 'out' using a BFS algorithm (Breadth-First Search)

def file = new File('./sample.txt')

// read the graph into Map<String, List<String>>
Map<String, List<String>> graph = [:].withDefault { [] }
file.eachLine { line ->
    int sep = line.indexOf(':')

    String start = line.substring(0, sep).trim()
    String rest  = line.substring(sep + 1).trim()   // +1 is space after ':'

    List<String> neighbors = rest.split(' ').collect { it.trim() }
    graph[start] = neighbors
}

// queue stores entire paths (List<String>)
Queue<List<String>> queue = new ArrayDeque<>()
queue.add(['you'])

List<List<String>> allPaths = []

while (!queue.isEmpty()) {
    // take the current path from the queue
    List<String> path = queue.poll()
    String node = path[-1] // current node = last in the path

    // get neighbors (empty list if node has no outgoing edges)
    List<String> neighbors = graph[node]

    neighbors.each { nb ->
        if (nb == 'out') {
            // found a full path to 'out' -> record it to allPaths
            allPaths << (path + nb)
        } else {
            // if neighbor is already in path, skip to avoid cycles
            if (!path.contains(nb)) {
                // add new path with neighbor to the queue
                queue.add(path + nb)
            }
        }
    }
}

allPaths.each { p ->
    println p.join(' -> ')
}
println "Total paths found: ${allPaths.size()}"