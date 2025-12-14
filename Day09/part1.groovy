
def file = new File('Day09/input.txt')

def largestRectangleArea = 0

def points = file.readLines().collect { line ->
    line.split(",").collect{it.toLong()}
}

// largest area
def pairs = []
for (int i = 0; i < points.size(); i++) {
    for (int j = i + 1; j < points.size(); j++) {

        def a1 = points[i][0]
        def a2 = points[i][1]
        def b1 = points[j][0]
        def b2 = points[j][1]

        largestRectangleArea = Math.max(largestRectangleArea, (Math.abs(a1-b1)+1) * (Math.abs(a2-b2)+1))
    }

}
println "max area: " + largestRectangleArea