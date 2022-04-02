package org.academiadecodigo.bootcamp67;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    /**
     * Properties
     */
    private final int portNumber = 6000;
    private Socket serverSocket;
    public static ArrayList<Player> players;
    public static int counterPlayers;

    private Game game;
    private boolean gameStarted = false;
    private Grid grid;

    /**
     * Methods
     */

    public void start() throws InterruptedException, IOException {
        try {

            //Server connection
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Waiting for connection in port: " + portNumber);

            //Collection
            players = new ArrayList<>();

            //Server Connection
            while (counterPlayers < 2) {

                System.out.println("Waiting for connection...");
                this.serverSocket = serverSocket.accept();
                System.out.println("Connection accepted!");

                // Connection with Threads
                Player player = new Player(this.serverSocket, this);
                System.out.println("Nova ligaçao");
                ExecutorService cachedPool = Executors.newCachedThreadPool();
                cachedPool.submit(player);

                //Save in ArrayList
                players.add(player);
                counterPlayers++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("waiting for game start");


        while (gameStarted == false) {
            //System.out.println("waiting"); //TODO sem isto o jogo nao funcionaaaaaa

            synchronized (players) {

                if (players.get(0).isReady() && players.get(1).isReady()) {
                    //System.out.println("inside if");
                    game = new Game(players.get(0), players.get(1));
                    gameStarted = true;
                    game.start();

                }
            }
        }
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }


}
