import app.controller.Controller;
import app.model.Game;
import app.model.Player;
import app.model.Request;
import client.Constants;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static String serverIpAddress = "127.0.0.1";
    public static int serverPort = 1800;

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            displayMenu();
            System.out.print(">");
            int choice = scanner.nextInt();

            if(choice == Request.VIEW_A_PLAYER_S_GAMES) {
                ArrayList<Game> gameList = Controller.makeRequestForCriteria2(serverIpAddress, serverPort);
                if (gameList != null)
                    displayGameList(gameList);
                else
                    System.out.println("Aucun joueur avec ce username n'a été trouvé");
            }
            else if (choice == Request.VIEW_THE_5_MOST_PLAYED_OPENING) {
                ArrayList<String> openingList = Controller.makeRequestForCriteria3(serverIpAddress, serverPort);
                displayStrings(openingList);
            }
            else if(choice == Request.VIEW_THE_SHORTEST_GAMES) {
                ArrayList<Game> gameList = Controller.makeRequestForCriteria4(serverIpAddress, serverPort);
                if(gameList != null)
                    displayGameList(gameList);
                else
                    System.out.println("There was an issue");
            }
            else if(choice == Request.VIEW_THE_MOST_ACTIVE_PLAYERS) {
                ArrayList<String> playerList = Controller.makeRequestForCriteria5(serverIpAddress, serverPort);
                displayStrings(playerList);
                System.out.println(playerList.size());
            }
            else if(choice == Request.VIEW_THE_BEST_PLAYERS) {
                ArrayList<Player> playerList = Controller.makeRequestForCiteria6(serverIpAddress, serverPort);
                displayPlayers(playerList);
            }
        }
    }

    static void displayMenu() {
        System.out.println("---------------------------------------Menu---------------------------------------");
        System.out.println("1- Visualiser une partie spécifique pas à pas");
        System.out.println("2- Trouver toutes les parties d'un joueur");
        System.out.println("3- Voir les 5 ouvertures les plus jouées");
        System.out.println("4- Trouver les parties les plus courtes");
        System.out.println("5- Lister les joueurs les plus actifs");
        System.out.println("6- Voir le joueur le plus fort selon l'algorithme PAGE RANK");
        System.out.println("7- Voir le joueur le plus fort selon l'algorithme HITS");
        System.out.println("8- Voir le plus grand nombre de coups consécutifs cc qui soient communs à p parties");
    }


    static void displayGameList(ArrayList<Game> gameList) {
        for(Game game : gameList) {
            System.out.println(game);
            System.out.println("-----------------------------------------------------------------------");
        }
    }


    static void displayStrings(ArrayList<String> openingList) {
        for(String opening : openingList) {
            System.out.println(opening);
        }
    }


    static void displayPlayers(ArrayList<Player> playerList) {
        int rank = 1;
        for(Player player : playerList) {
            System.out.println("Rang: " + rank);
            System.out.println("Nom d'utilisateur: " + player.getUsername());
            System.out.println("Page rank: " + player.getPageRank());
            System.out.println("-------------------------------------------");
            rank++;
        }
    }
}
