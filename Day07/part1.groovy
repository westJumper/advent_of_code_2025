def file = new File('Day07/sample.txt')
def counter = 0
def alreadyPipes = []

def checker = file.readLines().collect() { line ->
    line.split("").toList()
}

checker.eachWithIndex{ line, x ->
    line.eachWithIndex{ row, y ->

        // handle start
        if((checker[x][y] == 'S')) {
            checker[x+1][y] = '|'
        } 

        // handle falling | to .
        if(checker[x][y] == '.' && checker[x-1][y] == "|") { 
            checker[x][y] = "|"
        }
        
        // handle splitter ^ below |
        if (checker[x][y] == '^' && checker[x-1][y] == '|') {
            counter++
            if(checker[x][y+1] != '|'){
                checker[x][y+1] = '|'
            }
            if(checker[x][y-1] != '|'){
                checker[x][y-1] = '|'
            }
        }  
    }
}

checker.each{
    println it
}
println counter