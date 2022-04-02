package org.academiadecodigo.bootcamp67;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Game {

    private final PrintWriter outPlayer1;
    private final BufferedReader inPlayer1;
    private final BufferedReader inPlayer2;
    private final PrintWriter outPlayer2;

    private Player player1;
    private Player player2;

    private String player1Target;
    private String player2Target;

    int indexPlayer1ShipPos1;
    int indexPlayer1ShipPos2;
    int indexPlayer1ShipPos3;
    int indexPlayer2ShipPos1;
    int indexPlayer2ShipPos2;
    int indexPlayer2ShipPos3;
    int player1BoatCounter = 3;
    int player2BoatCounter = 3;


    public Game(Player player1, Player player2) throws IOException {
        this.player1 = player1;
        this.player2 = player2;
        inPlayer1 = new BufferedReader(new InputStreamReader(player1.getPlayerSocket().getInputStream()));
        outPlayer1 = new PrintWriter(player1.getPlayerSocket().getOutputStream(), true);
        inPlayer2 = new BufferedReader(new InputStreamReader(player2.getPlayerSocket().getInputStream()));
        outPlayer2 = new PrintWriter(player2.getPlayerSocket().getOutputStream(), true);
    }


    public void start() {
        notifyBothPlayers("Game is Starting get ready");

        intialBoatsPosPlayer1();
        intialBoatsPosPlayer2();

        outPlayer1.println(player1.getGrid().getGridOutput());
        outPlayer2.println(player2.getGrid().getGridOutput());

        while (player1BoatCounter > 0 && player2BoatCounter > 0) {

            shoot();

            checkPlayer1Hit();
            checkPlayer2Hit();
            outPlayer1.println(player1.getGrid().getGridOutput());
            outPlayer2.println(player2.getGrid().getGridOutput());

        }
        checkWinner();
    }

    private void checkWinner() {
        if (player1BoatCounter == 0) {
            notifyBothPlayers(player2.getNickName() + "is the winner");
        } else {
            notifyBothPlayers(player2.getNickName() + " is the winner");
        }

    }

    private void notifyBothPlayers(String notification) {
        outPlayer1.println(notification);
        outPlayer2.println(notification);
    }

    private void notifyPlayer1(String notificationP1) {
        outPlayer1.println(notificationP1);
    }

    private void notifyPlayer2(String notificationP2) {
        outPlayer2.println(notificationP2);
    }

    private void shoot() {
        notifyBothPlayers("Choose shooting coordinates:");
        try {
            player1Target = inPlayer1.readLine().toUpperCase();
            player2Target = inPlayer2.readLine().toUpperCase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void gridControlMissedHitP1() {

        for (int i = 0; i < player1.getGrid().getGridPosPlayerString().length; i++) {

            if (player1Target.equals(player1.getGrid().getGridPosPlayerString()[i])) {
                player1.getGrid().setControlHitMiss(i);
            }

        }
    }

    private void gridControlMissedHitP2() {

        for (int i = 0; i < player2.getGrid().getGridPosPlayerString().length; i++) {

            if (player2Target.equals(player2.getGrid().getGridPosPlayerString()[i])) {
                System.out.println("Entrou no for e falhou");
                player2.getGrid().setControlHitMiss(i);
            }

        }
    }


    private void gridControlHitP1() {

        for (int i = 0; i < player1.getGrid().getGridPosPlayerString().length; i++) {

            if (player1Target.equals(player1.getGrid().getGridPosPlayerString()[i])) {
                System.out.println("Nº boats before :" + player1BoatCounter);
                player1.getGrid().setControlHit(i);
                player1BoatCounter--;
                System.out.println("Nº boats after:" + player1BoatCounter);
            }

        }
    }

    private void gridControlHitP2() {

        for (int i = 0; i < player2.getGrid().getGridPosPlayerString().length; i++) {

            if (player1Target.equals(player2.getGrid().getGridPosPlayerString()[i])) {
                System.out.println("Nº boats before :" + player2BoatCounter);
                player2.getGrid().setControlHit(i);
                player2BoatCounter--;
                System.out.println("Nº boats after" + player2BoatCounter);
            }

        }
    }


    private void checkPlayer1Hit() {
        if (player1Target.equals(player2.getShip1Pos()) ||
                player1Target.equals(player2.getShip2Pos()) ||
                player1Target.equals(player2.getShip3Pos())) {

            gridControlHitP1();

            notifyPlayer1("You destroyed a ship");
            notifyPlayer2("");

        } else {
            notifyPlayer1("You miss");
            gridControlMissedHitP1();
        }
    }


    private void checkPlayer2Hit() {
        if (player2Target.equals(player1.getShip1Pos()) ||
                player2Target.equals(player1.getShip2Pos()) ||
                player2Target.equals(player1.getShip3Pos())) {


            gridControlHitP2();
            notifyPlayer2("You destroyed a ship");
            notifyPlayer1("");


        } else {
            notifyPlayer2("You miss");
            gridControlMissedHitP2();

        }

    }


    public void intialBoatsPosPlayer1() {
        for (int i = 0; i < player1.getGrid().getGridPosPlayer().length; i++) {
            if (player1.getShip1Pos().equals(player1.getGrid().gridPosPlayerString[i])) {
                this.indexPlayer1ShipPos1 = i;
            }
            if (player1.getShip2Pos().equals(player1.getGrid().gridPosPlayerString[i])) {
                this.indexPlayer1ShipPos2 = i;
            }
            if (player1.getShip3Pos().equals(player1.getGrid().gridPosPlayerString[i])) {
                this.indexPlayer1ShipPos3 = i;
            }
            player1.getGrid().setInitialBoat(indexPlayer1ShipPos1);
            player1.getGrid().setInitialBoat(indexPlayer1ShipPos2);
            player1.getGrid().setInitialBoat(indexPlayer1ShipPos3);
        }
    }

    public void intialBoatsPosPlayer2() {
        for (int i = 0; i < player2.getGrid().getGridPosPlayer().length; i++) {
            if (player2.getShip1Pos().equals(player2.getGrid().gridPosPlayerString[i])) {
                this.indexPlayer2ShipPos1 = i;
            }
            if (player2.getShip2Pos().equals(player2.getGrid().gridPosPlayerString[i])) {
                this.indexPlayer2ShipPos2 = i;
            }
            if (player2.getShip3Pos().equals(player2.getGrid().gridPosPlayerString[i])) {
                this.indexPlayer2ShipPos3 = i;
            }
            player2.getGrid().setInitialBoat(indexPlayer2ShipPos1);
            player2.getGrid().setInitialBoat(indexPlayer2ShipPos2);
            player2.getGrid().setInitialBoat(indexPlayer2ShipPos3);
        }
    }

}
