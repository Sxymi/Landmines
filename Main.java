package git.sxymi.landmines;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    //Database
    static int[] landmines = new int[16];
    static char[] map = new char[16];
    static int game = 0;

    //Program
    public static void main(String[] args) {
        System.out.println("Welcome in Landmines by Sxymi\n" +
                "Rules: \n" +
                "\t1. There are 6 landmines and 10 blank fields.\n" +
                "\t2. Try to select all blank fields and avoid mines.\n" +
                "\t3. Enjoy!");

        System.out.println("Start? [y/n]");
        boolean loop = true;
        Scanner scn = new Scanner(System.in);

        while(loop){
            switch(scn.next()){
                case "y":
                    start();
                    loop = false;
                    break;
                case "n":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input, please try again.");
                    break;
            }
        }
    }

    public static void start(){
        game = 0;
        generateLandmines();

        do{
            drawMap();
            playerMove();
            checkRound();
        }while(game == 0);
    }

    public static int random(){
        Random rnd = new Random();
        return rnd.nextInt(16);
    }

    public static void generateLandmines(){
        int i = 0;
        while(i<6){
            int randomNumber = random();
            if(landmines[randomNumber] == 0){
                landmines[randomNumber] = 1;
                i++;
            }
        }
    }

    public static void drawMap(){
        for(int i=0; i<landmines.length; i++){
            if(landmines[i] == 0 || landmines[i] == 1){
                map[i] = '?';
            }
            else if(landmines[i] == 2){
                map[i] = 'V';
            }
            else{
                map[i] = 'X';
            }
        }

        System.out.println("\t" + map[0] + " | " + map[1] + " | " + map[2] + " | " + map[3]);
        System.out.println("\t" + map[4] + " | " + map[5] + " | " + map[6] + " | " + map[7]);
        System.out.println("\t" + map[8] + " | " + map[9] + " | " + map[10] + " | " + map[11]);
        System.out.println("\t" + map[12] + " | " + map[13] + " | " + map[14] + " | " + map[15]);
    }

    public static void playerMove(){
        Scanner scn = new Scanner(System.in);
        System.out.println("Pick a field [1-16]: ");

        int pick = scn.nextInt();

        while(pick < 1 || pick > 16){
            System.out.println("Wrong input, please try again.");
            pick = scn.nextInt();
        }

        pick = pick - 1;

        if(landmines[pick] == 0){
            landmines[pick] = 2;
        }
        else if(landmines[pick] == 1){
            game = 1;
            landmines[pick] = 3;
            drawMap();
            System.out.println("Oops! There was a mine, GAME OVER!");
            System.out.println("Try again? [y/n]");

            boolean loop = true;

            while(loop){
                switch(scn.next()){
                    case "y":
                        loop = false;
                        Arrays.fill(landmines, 0);
                        start();
                        break;
                    case "n":
                        System.exit(0);
                    default:
                        System.out.println("Wrong input, please try again.");
                        break;
                }
            }
        }
        else if(landmines[pick] == 2){
            System.out.println("Field already selected, please try again.");
            playerMove();
        }
    }

    public static void checkRound(){
        int winner = 0;

        for(int landmine : landmines){
            if(landmine == 2){
                winner++;
            }
        }

        if(winner == 10){
            drawMap();
            System.out.println("Outstanding play! RNG is on your side! Congratulations, you won!");
            game = 1;
        }
    }
}