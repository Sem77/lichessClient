package client;

import app.model.Game;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private String ipAdressServer;
    private int portServer;
    private Socket connexionSocket;

    private PrintWriter outStream;

    public Client(String ipAdressServer, int portServer) throws IOException {
        this.ipAdressServer = ipAdressServer;
        this.portServer = portServer;
        connexionSocket = new Socket(ipAdressServer, portServer);
        outStream = new PrintWriter(new BufferedWriter(new OutputStreamWriter(connexionSocket.getOutputStream())), true);
    }

    public void makeRequest(int choice) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        //send a choice and the criterions
        //receive a list of games
        if(choice == Constants.VIEW_A_PLAYER_S_GAMES) {
            System.out.print("Type the username: ");
            String username = scanner.nextLine();

            String toSend = choice + " " + username; // request to send to the server
            outStream.println(toSend); // sending the request to the server

            ObjectInputStream inStream = new ObjectInputStream(connexionSocket.getInputStream());
            ArrayList<Game> response = (ArrayList<Game>) inStream.readObject(); // attempting an arraylist of games from the server

        }
    }
}
