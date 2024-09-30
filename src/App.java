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
    boolean falling = false;
    int speed = 20;
    int fallingX = -1;
    int fallingY = -1;
    int fallingColor = 0;
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
            if (falling) {
                if(fallingY < 19 && board[fallingY + 1][fallingX] == 0){
                    board[fallingY][fallingX] = 0;
                    fallingY++;
                    board[fallingY][fallingX] = fallingColor;
                }else{
                    falling = false;
                }
                
            }else{
                fallingX = (int) random(1,10);
                fallingY = 0;
                fallingColor = (int)random(1,4);
                if (board[fallingY][fallingX] == 0) {
                    board[fallingY][fallingX] = fallingColor;
                    falling = true;
                }
            }
            
            int streak = 0;
            for(int y = 0;y<20;y++){
                int lineColor = -1;
                for(int x = 0;x<10;x++){
                    if(board[y][x]!=0){
                        if(x==0){
                            lineColor = board[y][x];
                        }else{
                            if(board[y][x]==lineColor){
                                for(int i = 0;i<10;i++){
                                    board[y][i]=0;
                                }
                                break;
                            }
                        }
                    }
                }
            }

            background(0);
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

    public void keyPressed(){
        if (falling){
            if(keyCode==LEFT && fallingX > 0 && board[fallingY][fallingX - 1] == 0){
                board[fallingY][fallingX] = 0;
                fallingX--;
                board[fallingY][fallingX] = fallingColor;
            }else if (keyCode == RIGHT && fallingX < 9 && board[fallingY][fallingX + 1] == 0) {
                board[fallingY][fallingX] = 0;
                fallingX++;
                board[fallingY][fallingX] = fallingColor;
            }
        }
    }

    public static int cord(int location){
        int coordinates = 30*location-30;
        return coordinates;
    }
}
