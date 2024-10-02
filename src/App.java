import processing.core.*;

public class App extends PApplet{
    int board[][] = {//Three colors 0 is empty, 1 is red, 2 is green, 3 is blue
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0},
    };
    int shapeCords[][] = {{-1,-1},{-1,-1},{-1,-1},{-1,-1}}; 
    int shape = 1; //1 is square, 2 is L, 3 is BL, 4 is Line, 5 is LZig, 6 is BLZig, 7 is DaT!
    boolean falling = false;
    int speed = 18;
    int fallingX = -1;
    int fallingY = -1;
    int fallingColor = 0;
    int score = 0;
    boolean gameIsRunning = true;
    public static void main(String[] args)  {
        PApplet.main("App");
    }

    public void setup(){
        background(0);
    }

    public void settings(){
        size(300,600);
    }

    public void draw(){
        if(frameCount%speed==0){
            if(gameIsRunning){
                if (falling) {
                    clearShape();
                    
                    boolean canFall = true;
                    for(int i = 0; i<shapeCords.length;i++){
                        int x = shapeCords[i][0];
                        int y = shapeCords[i][1];
                        if (y >= 19 || (y + 1 < 20 && board[y + 1][x] != 0)) {
                        canFall = false;
                        break;
                        }
                    }
                    if(canFall){
                        fallingY++;
                        chooseShape(shape);
                        setShape();
                    }else{
                        falling = false;
                        setShape();
                    }
                    
                }else{
                    fallingX = (int) random(1,9);
                    fallingY = 0;
                    fallingColor = (int)random(1,4);
                    shape = (int)random(1,7);
                    chooseShape(shape);
                    if (board[fallingY][fallingX] == 0) {
                        board[fallingY][fallingX] = fallingColor;
                        falling = true;
                    }
                }
                clearRows();
                background(0);
                drawShapes();
                for (int y = 0; y < 20; y++) {
                    for (int x = 0; x < 10; x++) {
                        if (board[y][x] == 1) {
                            fill(255, 0, 0);  
                        } else if (board[y][x] == 2) {
                            fill(0, 255, 0);  
                        } else if (board[y][x] == 3) {
                            fill(0, 0, 255);  
                        } else {
                            continue;
                        }
                        rect(cord(x), cord(y), 30, 30);
                    }
                }
            }
        }
    }

    public void keyPressed() {
        if (falling) {
            clearShape(); 
            if (keyCode == LEFT) {
                boolean canMoveLeft = true;
                for(int i = 0; i<shapeCords.length;i++){
                    int x = shapeCords[i][0];
                    int y = shapeCords[i][1];
                    if (x - 1 < 0 || board[y][x - 1] != 0) {
                        canMoveLeft = false;
                        break;
                    }
                }
                if(canMoveLeft){fallingX--;}
            } else if (keyCode == RIGHT) {
                boolean canMoveRight = true;
                for(int i = 0; i<shapeCords.length;i++){
                    int x = shapeCords[i][0];
                    int y = shapeCords[i][1];
                    if (x + 1 >= 10 || board[y][x + 1] != 0) {
                        canMoveRight = false;
                        break;
                    }
                }
                if(canMoveRight){fallingX++;}
            } else if (keyCode == DOWN && fallingY < 18 && board[fallingY + 1][fallingX] == 0) {
                boolean canFall = true;
                for(int i = 0; i<shapeCords.length;i++){
                    int x = shapeCords[i][0];
                    int y = shapeCords[i][1];
                    if (y >= 19 || (y + 1 < 20 && board[y + 1][x] != 0)) {
                    canFall = false;
                    break;
                    }
                }
                if(canFall){fallingY++;}
            }
            chooseShape(shape);
            setShape();
        }
    }
    

    public static int cord(int location){
        int coordinates = 30*location;
        return coordinates;
    }

    public void makeSquare(){
        shapeCords[0][0] = fallingX;
        shapeCords[0][1] = fallingY;

        shapeCords[1][0] = fallingX + 1;
        shapeCords[1][1] = fallingY;

        shapeCords[2][0] = fallingX;
        shapeCords[2][1] = fallingY + 1;

        shapeCords[3][0] = fallingX + 1;
        shapeCords[3][1] = fallingY + 1;
    }

    public void makeL(){
        shapeCords[0][0] = fallingX;
        shapeCords[0][1] = fallingY;

        shapeCords[1][0] = fallingX ;
        shapeCords[1][1] = fallingY+1;

        shapeCords[2][0] = fallingX;
        shapeCords[2][1] = fallingY + 2;

        shapeCords[3][0] = fallingX + 1;
        shapeCords[3][1] = fallingY + 2;
    }

    public void makeBL(){
        shapeCords[0][0] = fallingX;
        shapeCords[0][1] = fallingY;

        shapeCords[1][0] = fallingX ;
        shapeCords[1][1] = fallingY+1;

        shapeCords[2][0] = fallingX;
        shapeCords[2][1] = fallingY + 2;

        shapeCords[3][0] = fallingX - 1;
        shapeCords[3][1] = fallingY + 2;
    }

    public void makeLine(){
        shapeCords[0][0] = fallingX;
        shapeCords[0][1] = fallingY;

        shapeCords[1][0] = fallingX ;
        shapeCords[1][1] = fallingY+1;

        shapeCords[2][0] = fallingX;
        shapeCords[2][1] = fallingY + 2;

        shapeCords[3][0] = fallingX;
        shapeCords[3][1] = fallingY + 3;
    }

    public void makeLZig(){
        shapeCords[0][0] = fallingX;
        shapeCords[0][1] = fallingY;

        shapeCords[1][0] = fallingX;
        shapeCords[1][1] = fallingY+1;

        shapeCords[2][0] = fallingX+1;
        shapeCords[2][1] = fallingY+1;

        shapeCords[3][0] = fallingX+1;
        shapeCords[3][1] = fallingY+2;
    }
    public void makeBLZig(){
        shapeCords[0][0] = fallingX;
        shapeCords[0][1] = fallingY;

        shapeCords[1][0] = fallingX;
        shapeCords[1][1] = fallingY+1;

        shapeCords[2][0] = fallingX-1;
        shapeCords[2][1] = fallingY+1;

        shapeCords[3][0] = fallingX-1;
        shapeCords[3][1] = fallingY+2;
    }

    public void makeDaT(){
        shapeCords[0][0] = fallingX;
        shapeCords[0][1] = fallingY;

        shapeCords[1][0] = fallingX;
        shapeCords[1][1] = fallingY+1;

        shapeCords[2][0] = fallingX+1;
        shapeCords[2][1] = fallingY+1;

        shapeCords[3][0] = fallingX;
        shapeCords[3][1] = fallingY+2;
    }

    public void chooseShape(int shapeToMake){//1 is square, 2 is L, 3 is BL, 4 is Line, 5 is LZig, 6 is BLZig, 7 is DaT!
        switch(shapeToMake){
            case 1:
                makeSquare();
                break;
            case 2:
                makeL();
                break;
            case 3:
                makeBL();
                break;
            case 4:
                makeLine();
                break;
            case 5:
                makeLZig();
                break;
            case 6:
                makeBLZig();
                break;
            case 7:
                makeDaT();
                break;
        }
    }

    public void setShape(){
        for (int i = 0; i < shapeCords.length; i++) {
            int x = shapeCords[i][0];
            int y = shapeCords[i][1];
            if (x >= 0 && x < 10 && y >= 0 && y < 20) {
                board[y][x] = fallingColor;
            }
        }
    }

    public void drawShapes(){
        board[shapeCords[0][1]][shapeCords[0][0]] = fallingColor;
        board[shapeCords[1][1]][shapeCords[1][0]] = fallingColor;
        board[shapeCords[2][1]][shapeCords[2][0]] = fallingColor;
        board[shapeCords[3][1]][shapeCords[3][0]] = fallingColor;
    }

    public void clearShape(){
        for (int i = 0; i < shapeCords.length; i++) {
            int x = shapeCords[i][0];
            int y = shapeCords[i][1];
            if (y >= 0 && y < 20 && x >= 0 && x < 10) {
                board[y][x] = 0;
            }
        }
    }

    public void clearRows(){
        for(int y = 0; y < 20; y++){
            boolean rowIsFull = true;
            int color = board[y][0];
            
            if(color == 0){
                rowIsFull = false;
            }else{
                for(int x = 1; x<10;x++){
                    if(board[y][x]!=color){
                        rowIsFull = false;
                        break;
                    }
                }
                
                if(rowIsFull){
                    score++;
                    for(int x = 0;x<10;x++){
                        board[y][x] = 0;
                    }
                    for (int i = y; i > 0; i--) {
                        for (int x = 0; x < 10; x++) {
                            board[i][x] = board[i - 1][x];
                        }
                    }
                }
            }
        }
    }

    public void checkEndGame(){
        for(int i = 0;i<10;i++){
            
        }
    }

}