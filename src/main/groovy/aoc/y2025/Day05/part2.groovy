package main.groovy.aoc.y2025.Day05

def file = new File('./sample.txt')
def counter = 0
List<Range> validRanges = []
def lineType = "ranges"

file.eachLine { line ->

    if(line.isEmpty()) {

        println counter
        lineType = 'end'
        
    }

    switch(lineType) {
        case 'ranges': // TODO - there are overlapping ranges with this solution
           println line
            def (start, end) = line.split('-')
            def size = (start.toLong()..end.toLong()).size()
            println size
            counter += size

        break
    }

}