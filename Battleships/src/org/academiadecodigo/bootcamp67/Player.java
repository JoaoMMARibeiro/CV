package org.academiadecodigo.bootcamp67;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player implements Runnable {


    /**
     * Properties
     */


    private String nickName;
    private Socket playerSocket;
    private PrintWriter out;
    private BufferedReader in;
    public Server server;
    private String ship1Pos;
    private String ship2Pos;
    private String ship3Pos;
    private boolean isReady;
    private Grid grid;


    private int ship = 0;


    //Em vez de mensagens ter a escolha dos aviões nestas threads

    /**
     * Methods
     *
     * @param playerSocket
     */

    // Construtor
    public Player(Socket playerSocket, Server server) {
        this.playerSocket = playerSocket;
        this.server = server;
        grid = new Grid();
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
            out = new PrintWriter(playerSocket.getOutputStream(), true);


            if (nickName == null) {
                out.println("Welcome! Please insert your Nickname: ");
                nickName = in.readLine();
            }
            gridInit();

            if (ship3Pos == null) {
                out.println("Choose Position to Plane Ship:");
                ship1Pos = in.readLine();
                ship++;
                System.out.println(ship1Pos);
                out.println("Choose Position to War Ship:");
                ship++;
                ship2Pos = in.readLine();
                out.println("Choose Position to Small Ship:");
                ship3Pos = in.readLine();
                ship++;
                out.println("Waiting for other player");
            }
            synchronized (this) {
                isReady = true;
                System.out.println("Player: <" + nickName + "> is ready!");

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public String getNickName() {
        return nickName;
    }

    public Socket getPlayerSocket() {
        return playerSocket;
    }

    public PrintWriter getOut() {
        return out;
    }

    public BufferedReader getIn() {
        return in;
    }

    public Server getServer() {
        return server;
    }

    public String getShip1Pos() { //
        return ship1Pos.toUpperCase();
    }

    public String getShip2Pos() {
        return ship2Pos.toUpperCase();
    }

    public String getShip3Pos() {
        return ship3Pos.toUpperCase();
    }

    public boolean isReady() {
        return isReady;
    }

    public Grid getGrid() {
        return grid;
    }

    public void gridInit() {
        out.println(grid.getGridOutput());
    }

}
