package main.groovy.aoc.y2025.Day03

def file = new File('./sample.txt')
def counter = 0

file.eachLine { line ->

    println line

    def numbers = line.split("").collect { Integer.parseInt(it) }
    def numbersCount = numbers.size()

    // first and second number automatically assign
    def first = numbers[0]
    def second = numbers[1]
    def firstIndex = 0
    def secondIndex = 1

    // start checking first number from second position
    for (def i = 1; i < numbers.size(); i++) {
        if (numbers[i] > first && i != numbersCount - 1) {
            first = numbers[i]
            second = numbers[i + 1]
            firstIndex = i
            secondIndex = i + 1
            for (def y = i + 1; y < numbers.size(); y++) {
                if (numbers[y] > second) {
                    second = numbers[y]
                    secondIndex = y
                }
            }
        } else if (numbers[i] > second) {
            second = numbers[i]
            secondIndex = i
        }
    }

    // to show position of those numbers
    def output = []
    for (def a = 0; a < numbersCount; a++) {
        if (secondIndex == a || firstIndex == a) {
            output.push("x")
        } else {
            output.push("-")
        }
    }
    println output.reverse().join("")

    println "result: " + first.toString() + second.toString()
    counter += Integer.parseInt(first.toString() + second.toString())
    println "----------------------------------------------------------------------------------------------------"
}

println counter
