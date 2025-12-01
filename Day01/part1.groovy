def file = new File('Day01/sample.txt')
def currentPointer = 50
def counter = 0
def rotation = 100

file.eachLine { line ->
    
    def number = Integer.parseInt(line.substring(1, line.length()))

    if(line.startsWith("R")){
        currentPointer += number
    } else if(line.startsWith("L")){
        currentPointer -= number
    }
    
    println "The dial is rotated " + line + " to point at " + currentPointer

    if(Math.abs(currentPointer) % rotation == 0) {
        counter++
    }
}

println counter