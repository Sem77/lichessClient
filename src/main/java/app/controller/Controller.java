package app.controller;

import app.model.Game;
import client.Constants;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    /**
     * Establish a connection with the server
     * Make a request to find all games that a player played
     * Close the connection
     * @return list of the games
     */
    public static ArrayList<Game> makeRequestForCriteria2(String serverIpAddress, int serverPort) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please type the username of the player");
            System.out.print("> ");
            String username = scanner.nextLine();

            // opening the connection
            Socket connexionSocket = new Socket(serverIpAddress, serverPort);
            PrintWriter outStream = new PrintWriter(new BufferedWriter(new OutputStreamWriter(connexionSocket.getOutputStream())), true);
            ObjectInputStream inStream = new ObjectInputStream(connexionSocket.getInputStream());

            // sending request
            String request = Constants.VIEW_A_PLAYER_S_GAMES + " " + username;
            outStream.println(request);

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

        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }

        return null;
    }
}
