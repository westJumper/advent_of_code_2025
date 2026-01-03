package main.groovy.aoc.y2025.Day02

def file = new File('./sample.txt')
def result = 0L

file.eachLine { line ->
    def range = line.split(',')

    range.each { it ->
        def parts = it.split('-')
        def start = Long.parseLong(parts[0])
        def end = Long.parseLong(parts[1])

        for (long i = start; i <= end; i++) {
            def value = i.toString()
            def size = value.size()

            if (size % 2 != 0) continue // nelze porovnat, lichý počet znaků

            def mid = size / 2

            def count = true
            for (int a = mid - 1; a >= 0; a--) {
                if (value[a] != value[a + mid]) {
                    count = false
                    break
                }
            }

            if (count) result += Long.parseLong(value)
        }
    }
}

println result
