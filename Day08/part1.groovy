
def file = new File('Day08/sample.txt')

def connectMaxPairs = 10
def counter = 1

def points = file.readLines().collect { line ->
    line.split(",").toList()
}

// create pairs and sort them based on distance
def pairs = []
for (int i = 0; i < points.size(); i++) {
    for (int j = i + 1; j < points.size(); j++) {
        def a = points[i]
        def b = points[j]
        pairs << [a, b, distance3D(a, b)]
    }
}
pairs = pairs.sort { it[2] }

def groups = []

// loop through the first max pairs based on variable above
for (int i = 0; i < connectMaxPairs; i++) {
    def pA = pairs[i][0]
    def pB = pairs[i][1]

    // find if point a and point b are in any existing group already to handle where to put it
    // -1 == points not found in any group
    // other number is that it is in a group
    int idxA = groups.findIndexOf { g -> g.contains(pA) }
    int idxB = groups.findIndexOf { g -> g.contains(pB) }

    if (idxA == -1 && idxB == -1) {
        // create new group
        groups << ([pA, pB] as Set) // use set to make points in group unique
    } else if (idxA != -1 && idxB == -1) {
        // pA is in a group but pB is not, add pB to group where pA is
        groups[idxA] << pB
    } else if (idxA == -1 && idxB != -1) {
        // pB is in a group but pA is not, add pA to group where pB is
        groups[idxB] << pA
    } else if (idxA != idxB) {
        // pA and pB are in a group, merge those two groups
        groups[idxA].addAll(groups[idxB])
        groups.remove(idxB)
    }
    // if idxA == idxB it means they are already in a same group, skip
}

// sort by size and take only size to final list
def sizes = groups.collect { it.size() }.sort { -it }
println "Sizes of circuits: ${sizes}"

// first 3 circuit sizes multiplied
for(int i = 0; i < 3; i++){
    println i + ": " + sizes[i]
    counter = counter * sizes[i]
}
println "counter: " + counter

double distance3D(List a, List b) {
    def dx = a[0].toLong() - b[0].toLong()
    def dy = a[1].toLong() - b[1].toLong()
    def dz = a[2].toLong() - b[2].toLong()
    return Math.sqrt(dx*dx + dy*dy + dz*dz)
}
