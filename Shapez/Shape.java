public class Shape {
    public enum Colors{
        Empty,
        Blue,
        Yellow,
        Red,
        Green
    }

    // Segments
    // 2x2 array -> 0-4 are colors
    private Colors[][] segments;

    // Const number of rows
    private static final int nr = 2;

    // Const number of columns
    private static final int nc = 2;

    // Default value for helping purposes
    Shape(){
        segments = new Colors[nc][nr];
        for(int i = 0; i < nc; i++){
            for(int j = 0; j < nr; j++){
                segments[nc][nr] = Colors.Empty;
            }
        }
    }

    public Colors[][] getSegments() {
        return segments;
    }
    public void setSegment(int index, Colors color){
        this.segments[index / nr][index % nc] = color;
    }

    public void printSegments(){
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                System.out.print(segments[j][i] + " ");
            }
            System.out.println();
        }
    }
}
