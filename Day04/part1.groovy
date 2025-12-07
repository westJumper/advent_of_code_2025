def file = new File('Day04/sample.txt')
def counter = 0

def checker = file.readLines().collect() { line ->
    line.split("").toList()
}

checker.eachWithIndex{ line, x ->

    line.eachWithIndex{ row, y ->
        
        if(checker[x][y] == '@') {

            //println "checking x + y: " + x + " " + y
            def adjascent = 0

            if(adjascentHasAtSymbol(checker, x + 1, y - 1)) adjascent++
            if(adjascentHasAtSymbol(checker, x + 1, y)) adjascent++
            if(adjascentHasAtSymbol(checker, x + 1, y + 1)) adjascent++
            if(adjascentHasAtSymbol(checker, x - 1, y - 1)) adjascent++
            if(adjascentHasAtSymbol(checker, x - 1, y)) adjascent++
            if(adjascentHasAtSymbol(checker, x - 1, y + 1))adjascent++
            if(adjascentHasAtSymbol(checker, x, y - 1)) adjascent++
            if(adjascentHasAtSymbol(checker, x, y + 1)) adjascent++

            //println "adjascent: " + adjascent

            if(adjascent < 4) {
                //println "less than 4 is line + row: " + x + " " + y
                counter++
            }
            //println "--------------------------------"
        }
    }
}

println counter

def adjascentHasAtSymbol(checker, x, y) {
    //println x + " " + y
    if(x < 0 || y < 0) return false
     
    try {
        if(checker[x][y] == '@') { // this would take -1 as the last item of an array instead of out of bound with error -> check above for x and y
            return true
        }
    } catch(e) {
        return false
    }
    return false
}