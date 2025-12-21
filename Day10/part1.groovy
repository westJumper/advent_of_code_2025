
def file = new File('Day10/sample.txt')
def finalCalculation = 0

file.eachLine { line ->
    println "starting new line: $line"

    // 1) get goal between [ and ] -> this is goal
    int l = line.indexOf('[')
    int r = line.indexOf(']')
    String goalStr = line.substring(l + 1, r) // e.g. ".###.#"
    int goalLen = goalStr.length()

    int goalInt = 0
    goalStr.each { ch ->
        goalInt = (goalInt << 1)
        if (ch == '#') goalInt |= 1
    }
    println "goalStr: $goalStr"
    println "goalInt: " + Integer.toBinaryString(goalInt).padLeft(goalLen, '0')

    // 2) parse switches
    def switches = []
    def matcher = (line =~ /\((.*?)\)/) // matches all between ( and )
    matcher.each {
        switches << it[1].split(',').collect { it.trim().toInteger() }
    }
    println "switches: $switches"

    // 3) create masks for each switch with MSB orientation (left index -> bit = goalLen-1-index)
    def switchesMasks = []
    switches.each { switchDef ->
        int mask = 0
        switchDef.each { idx -> // for each index in the switch add int bit to mask so that position in the array is 1
            mask |= (1 << (goalLen - 1 - idx))
        }
        switchesMasks << mask
    }
    println "masks:    " + switchesMasks.collect { Integer.toBinaryString(it).padLeft(goalLen, '0') }

    // 4) initial state = all bits 0, go through each to find minimum presses to reach goal
    def queue = new ArrayDeque()
    def visited = new HashSet<Integer>()
    int initialStateInt = 0

    queue.add([initialStateInt, 0])         // [state, steps]
    visited.add(initialStateInt)

    int presses = -1                        // result
    while (!queue.isEmpty()) {
        def pair = queue.poll()
        int current = pair[0] as int
        int steps   = pair[1] as int

        if (current == goalInt) {
            presses = steps
            break
        }

        // try applying all switches to the current state 
        for (int mask : switchesMasks) {
            int next = current ^ mask
            if (visited.add(next)) {        // add returns true only if it wasn't already present
                queue.add([next, steps + 1]) // add current state to queue and increase steps
            }
        }
    }

    println "minimum number of presses: $presses"

    finalCalculation += presses
    println "-----------------------------end of line-----------------------------"
}

println "finalCalculation: $finalCalculation"