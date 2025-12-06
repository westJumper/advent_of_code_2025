def file = new File('Day02/sample.txt')
def result = 0

file.eachLine { line ->
    def range = line.split(',')

    range.each { it ->
        def start = Long.parseLong(it.split('-')[0])
        def end = Long.parseLong(it.split('-')[1])

        for (i = start; i <= end; i++){
            
            String value = i.toString()
            Integer size = value.size()

            if(size % 2 != 0) continue // cannot match, different number of characters

            Integer mid = size / 2

            Boolean count = true
            for(a = mid-1; a >= 0; a--){
                if(value[a] != value[a+mid]) {
                    count = false
                    break
                }
            }

            if (count) result += Long.parseLong(value)

        }
    }
}

println result