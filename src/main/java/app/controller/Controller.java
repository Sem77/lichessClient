package app.controller;

import app.model.Game;
import app.model.Player;
import app.model.Request;
import app.model.Strokes;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    public static Game makeRequestForCriteia1(String serverIpAddress, int serverPort) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir l' URL de la partie d'échec");
        System.out.print("> ");
        String link = scanner.nextLine().trim(); // remove spaces around

        try {
            // opening the connection
            Socket connexionSocket = new Socket(serverIpAddress, serverPort);
            ObjectOutputStream outStream = new ObjectOutputStream(connexionSocket.getOutputStream());
            ObjectInputStream inStream = new ObjectInputStream(connexionSocket.getInputStream());

            // sending request
            ArrayList<String> criterias = new ArrayList<>();
            criterias.add(link);
            Request request = new Request(Request.VIEW_A_GAME, criterias);
            outStream.writeObject(request);

            // getting the answer from the server
            Game game = (Game) inStream.readObject();

            scanner.close();
            // closing the connection
            outStream.close();
            inStream.close();
            connexionSocket.close();

            return game;
        } catch (UnknownHostException uhe) {
            System.out.println("Could not establish a connection with server. Let's try again");
        } catch (IOException ioe) {
            System.out.println("Could not establish a connection with server. Let's try again");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        return null;
    }

    /**
     * Establish a connection with the server
     * ask for a username
     * Make a request to find all games that a player played
     * Close the connection
     * @return list of the games
     */
    public static ArrayList<String> makeRequestForCriteria2(String serverIpAddress, int serverPort) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Veuillez saisir le nom du joueur");
            System.out.print("> ");
            String username = scanner.nextLine().trim(); // remove spaces around

            // opening the connection
            Socket connexionSocket = new Socket(serverIpAddress, serverPort);
            ObjectOutputStream outStream = new ObjectOutputStream(connexionSocket.getOutputStream());
            ObjectInputStream inStream = new ObjectInputStream(connexionSocket.getInputStream());

            // sending request
            ArrayList<String> criterias = new ArrayList<>();
            criterias.add(username);
            Request request = new Request(Request.VIEW_A_PLAYER_S_GAMES, criterias);
            outStream.writeObject(request);

            // getting the answer from the server
            ArrayList<String> gameList = (ArrayList<String>) inStream.readObject();

            scanner.close();
            // closing the connection
            outStream.close();
            inStream.close();
            connexionSocket.close();

            return gameList;

        } catch (UnknownHostException uhe) {
            System.out.println("Could not establish a connection with server. Let's try again");
        } catch (IOException ioe) {
            System.out.println("Could not establish a connection with server. Let's try again");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }

        return null;
    }


    /**
     * Établie une connexion avec le serveur
     * Demande les 5 ouvertures les plus jouées
     * @param serverIpAddress
     * @param serverPort
     * @return
     */
    public static ArrayList<String> makeRequestForCriteria3(String serverIpAddress, int serverPort) {
        try {
            // opening the connection
            Socket connexionSocket = new Socket(serverIpAddress, serverPort);
            ObjectOutputStream outStream = new ObjectOutputStream(connexionSocket.getOutputStream());
            ObjectInputStream inStream = new ObjectInputStream(connexionSocket.getInputStream());

            // sending request
            Request request = new Request(Request.VIEW_THE_5_MOST_PLAYED_OPENING);
            outStream.writeObject(request);

            ArrayList<String> openingList = (ArrayList<String>) inStream.readObject();

            // closing the connection
            outStream.close();
            inStream.close();
            connexionSocket.close();

            return openingList;

        } catch (UnknownHostException uhe) {
            System.out.println("Could not establish a connection with server. Let's try again");
        } catch (IOException ioe) {
            System.out.println("Could not establish a connection with server. Let's try again");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }

        return null;
    }


    /**
     * Demandes les parties les plus courtes
     * @param serverIpAddress
     * @param serverPort
     * @return
     */
    public static ArrayList<String> makeRequestForCriteria4(String serverIpAddress, int serverPort) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Combien de parties d'échec?");
        String nbShortestGames = scanner.nextLine();

        try {
            // opening the connection
            Socket connexionSocket = new Socket(serverIpAddress, serverPort);
            ObjectOutputStream outStream = new ObjectOutputStream(connexionSocket.getOutputStream());
            ObjectInputStream inStream = new ObjectInputStream(connexionSocket.getInputStream());

            // sending request
            ArrayList<String> criterias = new ArrayList<>();
            criterias.add(nbShortestGames);
            Request request = new Request(Request.VIEW_THE_SHORTEST_GAMES, criterias);
            outStream.writeObject(request);

            ArrayList<String> gameList = (ArrayList<String>) inStream.readObject();

            // closing the connection
            outStream.close();
            inStream.close();
            connexionSocket.close();

            return gameList;

        }catch (UnknownHostException uhe) {
            System.out.println("Could not establish a connection with server. Let's try again");
        } catch (IOException ioe) {
            System.out.println("Could not establish a connection with server. Let's try again");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        return null;
    }


    /**
     * Demande les N joueurs les plus actifs
     * @param serverIpAddress
     * @param serverPort
     * @return
     */
    public static ArrayList<String> makeRequestForCriteria5(String serverIpAddress, int serverPort) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Combien de joueurs?");
        String nbPlayers = scanner.nextLine();

        try {
            // opening the connection
            Socket connexionSocket = new Socket(serverIpAddress, serverPort);
            ObjectOutputStream outStream = new ObjectOutputStream(connexionSocket.getOutputStream());
            ObjectInputStream inStream = new ObjectInputStream(connexionSocket.getInputStream());

            // sending request
            ArrayList<String> criterias = new ArrayList<>();
            criterias.add(nbPlayers);
            Request request = new Request(Request.VIEW_THE_MOST_ACTIVE_PLAYERS, criterias);
            outStream.writeObject(request);

            // getting the answer from the server
            ArrayList<String> playerList = (ArrayList<String>) inStream.readObject();

            // closing the connection
            outStream.close();
            inStream.close();
            connexionSocket.close();

            return playerList;

        } catch (UnknownHostException uhe) {
            System.out.println("Could not establish a connection with server. Let's try again");
        } catch (IOException ioe) {
            System.out.println("Could not establish a connection with server. Let's try again");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }

        scanner.close();

        return null;
    }


    public static ArrayList<Player> makeRequestForCiteria6(String serverIpAddress, int serverPort) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Combien de joueurs?");
        String nbPlayers = scanner.nextLine();

        try {
            // opening the connection
            Socket connexionSocket = new Socket(serverIpAddress, serverPort);
            ObjectOutputStream outStream = new ObjectOutputStream(connexionSocket.getOutputStream());
            ObjectInputStream inStream = new ObjectInputStream(connexionSocket.getInputStream());

            // sending request
            ArrayList<String> criterias = new ArrayList<>();
            criterias.add(nbPlayers);
            Request request = new Request(Request.VIEW_THE_BEST_PLAYERS_PR, criterias);
            outStream.writeObject(request);

            // getting the answer from the server
            ArrayList<Player> playerList = (ArrayList<Player>) inStream.readObject();

            // closing the connection
            outStream.close();
            inStream.close();
            connexionSocket.close();

            return playerList;

        } catch (UnknownHostException uhe) {
            System.out.println("Could not establish a connection with server. Let's try again");
        } catch (IOException ioe) {
            System.out.println("Could not establish a connection with server. Let's try again");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        scanner.close();
        return null;
    }


    public static ArrayList<Player> makeRequestForCiteria7(String serverIpAddress, int serverPort) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Combien de joueurs?");
        String nbPlayers = scanner.nextLine();

        try {
            // opening the connection
            Socket connexionSocket = new Socket(serverIpAddress, serverPort);
            ObjectOutputStream outStream = new ObjectOutputStream(connexionSocket.getOutputStream());
            ObjectInputStream inStream = new ObjectInputStream(connexionSocket.getInputStream());

            // sending request
            ArrayList<String> criterias = new ArrayList<>();
            criterias.add(nbPlayers);
            Request request = new Request(Request.VIEW_THE_BEST_PLAYERS_HITS, criterias);
            outStream.writeObject(request);

            // getting the answer from the server
            ArrayList<Player> playerList = (ArrayList<Player>) inStream.readObject();

            // closing the connection
            outStream.close();
            inStream.close();
            connexionSocket.close();

            return playerList;

        } catch (UnknownHostException uhe) {
            System.out.println("Could not establish a connection with server. Let's try again");
        } catch (IOException ioe) {
            System.out.println("Could not establish a connection with server. Let's try again");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        scanner.close();
        return null;
    }
}
