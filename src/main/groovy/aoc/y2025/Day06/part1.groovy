package main.groovy.aoc.y2025.Day06

def file = new File('./sample.txt')
def counter = 0
def checker = []

file.eachLine { line ->
    checker << line.trim().split(' ').collect { it }.findAll { it != '' }
}

checker[-1].eachWithIndex{ operation, index ->

    def result = 0

    switch(operation){
        case '+':
            checker[0..-2].eachWithIndex{ value, i -> // exclude last item (-1)
                result+=value[index].toLong() // integer is too small, must use long
            }
        break
        case '*':
            checker[0..-2].eachWithIndex{ value, i -> // exclude last item (-1)
                if (result == 0) {
                    result = value[index].toLong()
                } else {
                    result *= value[index].toLong()
                }
            }
        break
    }

    counter+= result

}

println counter