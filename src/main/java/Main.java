import app.controller.Controller;
import app.model.Game;
import app.model.Player;
import app.model.Request;
import app.model.Strokes;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static String serverIpAddress = "127.0.0.1";
    public static int serverPort = 1800;

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        displayMenu();
        System.out.print(">");
        int choice = scanner.nextInt();

        while(choice != 0) {

            if(choice == Request.VIEW_A_GAME) {
                Game game = Controller.makeRequestForCriteia1(serverIpAddress, serverPort);
                if(game != null) {
                    System.out.println(game);
                }
                else {
                    System.out.println("Aucune partie avec ce lien c'a été trouvée");
                }
            }
            else if(choice == Request.VIEW_A_PLAYER_S_GAMES) {
                ArrayList<String> gameList = Controller.makeRequestForCriteria2(serverIpAddress, serverPort);
                if (gameList != null)
                    displayStrings(gameList);
                else
                    System.out.println("\u001B[31m Aucun joueur avec ce username n'a été trouvé" + "\033[0m");
            }
            else if (choice == Request.VIEW_THE_5_MOST_PLAYED_OPENING) {
                ArrayList<String> openingList = Controller.makeRequestForCriteria3(serverIpAddress, serverPort);
                displayStrings(openingList);
            }
            else if(choice == Request.VIEW_THE_SHORTEST_GAMES) {
                ArrayList<String> gameList = Controller.makeRequestForCriteria4(serverIpAddress, serverPort);
                if(gameList != null)
                    displayStrings(gameList);
                else
                    System.out.println("Les parties d'échec n'ont pas été trouvées");
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

            displayMenu();
            System.out.print(">");
            choice = scanner.nextInt();
        }
        //scanner.close();
    }

    static void displayMenu() {
        System.out.print("\033[0;33m");
        System.out.println("---------------------------------------Menu---------------------------------------");
        System.out.println("1- Visualiser une partie spécifique pas à pas");
        System.out.println("2- Trouver toutes les parties d'un joueur");
        System.out.println("3- Voir les 5 ouvertures les plus jouées");
        System.out.println("4- Trouver les parties les plus courtes");
        System.out.println("5- Lister les joueurs les plus actifs");
        System.out.println("6- Voir le joueur le plus fort selon l'algorithme PAGE RANK");
        System.out.println("7- Voir le joueur le plus fort selon l'algorithme HITS");
        System.out.println("8- Voir le plus grand nombre de coups consécutifs cc qui soient communs à p parties");
        System.out.println("0- Quitter");
        System.out.print("\033[0m");
    }


    static void displayGameList(ArrayList<Game> gameList) {
        for(Game game : gameList) {
            System.out.println("\033[0;36m");
            System.out.println(game);
            System.out.println("-----------------------------------------------------------------------");
            System.out.print("\033[0m");
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
