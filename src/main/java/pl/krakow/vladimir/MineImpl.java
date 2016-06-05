package pl.krakow.vladimir;

/**
 * Created by vladimir on 05.06.2016.
 */
public class MineImpl implements MineSweeper{
    public static int N;
    public static int M;
    public static char[][] mines;

    @Override
    public void setMineField(String mineField) throws IllegalArgumentException {
        String[] lines = mineField.split("\n");

        if(!check(lines)){
            throw new IllegalArgumentException();
        }

        N = lines.length;
        M = lines[0].length();

        mines = new char[N][M];
        fillMines(lines);

    }
    private static boolean check(String[] lines) {

        int n=lines.length;
        if(n==0)
            return false;

        int m=0;

        for(int i=0; i<n; i++){
            //Should be only . or *
            if(lines[i].replaceAll("\\.","").replaceAll("\\*","").length()>0)
                return false;

            //Check length
            int l = lines[i].length();
            if(l==0)
                return false;

            if(m==0){       //first row
                m = l;
            }
            else if(l!=m){  //compare all line length with first
                return false;
            }
        }

        return true;
    }
    private static void fillMines(String[] lines) {
        for(int i=0; i<lines.length; i++){
            for(int j=0; j<lines[i].length(); j++){
                if(lines[i].charAt(j)=='.')
                    mines[i][j] = '0';
                else{
                    mines[i][j] = '*';
                }
            }
        }
    }

    @Override
    public String getHintField() throws IllegalStateException {
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(mines[i][j] == '*'){
                    addMine(i,j);
                }
            }
        }

        String[] lines = new String[N];
        for(int i=0; i<N; i++){
            lines[i] = String.valueOf(mines[i]);
        }
        return String.join("\n", lines);
    }

    private static void addMine(int i, int j) {

        //increment for all neighbores (8 cells)
        for(int k=-1; k<=1; k++){
            for(int l=-1; l<=1; l++){
                if(!(k==0 && l==0)){
                    int new_i = i+k;
                    int new_j = j+l;

                    if(new_i>=0 && new_i<N && new_j>=0 && new_j<M){
                        if(mines[new_i][new_j] != '*'){
                            mines[new_i][new_j]+=1;
                        }
                    }
                }
            }
        }
    }
}
