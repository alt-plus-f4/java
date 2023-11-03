public class Shape { // D
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
                segments[j][i] = Colors.Empty;
            }
        }
    }
    public Colors[][] getSegments() {
        return segments;
    }
    public void setSegment(int x, int y, Colors color){
        this.segments[y][x] = color; // converts single index to 2x2
    }
    public void setSegments(Colors[][] segments){
        this.segments = segments;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nr; i++){
            sb.append("╔═══╗".repeat(nc));
            sb.append("\n");
            for(int j = 0; j < nc; j++){
                switch (segments[i][j]) {
                    case Blue:
                        sb.append("║ B ║");
                        break;
                    case Yellow:
                        sb.append("║ Y ║");
                        break;
                    case Red:
                        sb.append("║ R ║");
                        break;
                    case Green:
                        sb.append("║ G ║");
                        break;
                    default:
                        sb.append("║ E ║");
                        break;
                }
            }
            sb.append("\n");
            sb.append("╚═══╝".repeat(nc));
            sb.append("\n");
        }
        return sb.toString();
    }

}
