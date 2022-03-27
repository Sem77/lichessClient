package app.controller;

import app.model.Game;
import app.model.Request;
import client.Constants;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    /**
     * Establish a connection with the server
     * ask for a username
     * Make a request to find all games that a player played
     * Close the connection
     * @return list of the games
     */
    public static ArrayList<Game> makeRequestForCriteria2(String serverIpAddress, int serverPort) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please type the username of the player");
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
            ArrayList<Game> gameList = (ArrayList<Game>) inStream.readObject();

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
}
