package main.groovy.aoc.y2025.Day05

def file = new File('./input.txt')
def counter = 0
List<Range> validRanges = []
def lineType = "ranges"

file.eachLine { line ->

    if(line.isEmpty()) {
        lineType = 'numbers'
        println 'start comparing'
        return
    }

    switch(lineType) {
        case 'ranges':
           
            def (start, end) = line.split('-')
            validRanges << (start.toLong()..end.toLong())

        break
        case 'numbers':  

            def isInRange = validRanges.any { range ->
                range.containsWithinBounds(line.toLong()) // contains is too expensive and slow, containsWithinBounds is faster when comparing ≤ x ≤
            }
            
            if(isInRange) counter++

        break
    }

}

println counter