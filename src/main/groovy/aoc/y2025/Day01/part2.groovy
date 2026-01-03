package main.groovy.aoc.y2025.Day01

def file = new File('./sample.txt')
def currentPointer = 50
def passwordCount = 0
def dial = 100
def originalFromStart = 50

file.eachLine { line ->

    originalFromStart = currentPointer
    def moveNumber = Integer.parseInt(line.substring(1, line.length()))
    def rotations = Math.floor(moveNumber / dial).toInteger()
    def remainderFromDial = Math.floor(moveNumber % dial).toInteger()

        // add full rotations to password count and then handle remainder only
    passwordCount+= rotations

    // calculate new pointer based on remainder only
    if(line.startsWith("R")){
        currentPointer += remainderFromDial
    } else if(line.startsWith("L")){
        currentPointer -= remainderFromDial
    }

    // add one rotation if we exceed the dial in either direction with remainder
    // but only if we did not start exactly on a dial point because then we moved but not crossed a 0 point
    if(originalFromStart != 0 && (currentPointer > dial || currentPointer < 0)){
        passwordCount++
    }

    // if we end up exactly on a dial point, we need to count that as one more - this is from part 1 logic
    if(Math.abs(currentPointer) % dial == 0) {
        passwordCount++
    }

    // get the raw pointer (pointer within 0 to dial range) for next iteration
    def rawPointer = 0
    if(currentPointer == 0 || Math.abs(currentPointer) == dial){
        rawPointer = 0
    } else if(currentPointer < 0){
        rawPointer = dial - Math.abs(currentPointer)%dial
    } else {
        rawPointer = Math.abs(currentPointer)%dial
    }
    currentPointer = rawPointer
}

println passwordCount