package Two_CS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Client(String host, int port) throws IOException {
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void addContact(int id,String name, String phone, String email, String address) throws IOException {
        out.println("add," + id + ","+ name + "," + phone + "," + email + "," + address);
        String response = in.readLine();
        if (response.equals("ok")) {
            System.out.println("Contact added successfully.");
        } else {
            System.out.println("Failed to add contact.");
        }
    }

    public void deleteContact(int id) throws IOException {
        out.println("delete," + id);
        String response = in.readLine();
        if (response.equals("ok")) {
            System.out.println("Contact deleted successfully.");
        } else {
            System.out.println("Failed to delete contact.");
        }
    }

    public void updateContact(int id, String name, String phone, String email, String address) throws IOException {
        out.println("update," + id + "," + name + "," + phone + "," + email + "," + address);
        String response = in.readLine();
        if (response.equals("ok")) {
            System.out.println("Contact updated successfully.");
        } else {
            System.out.println("Failed to update contact.");
        }
    }

    public void searchContact(String keyword) throws IOException {
        out.println("search," + keyword);
        String response;
        while (!(response = in.readLine()).equals("end")) {
            String[] fields = response.split(",");
            System.out.println("ID: " + fields[0] + ", Name: " + fields[1] + ", Phone: " + fields[2] + ", Email: " + fields[3] + ", Address: " + fields[4]);
        }
    }

    public void close() throws IOException {
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client("localhost", 8000);

        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            System.out.println("1. Add contact");
            System.out.println("2. Delete contact");
            System.out.println("3. Update contact");
            System.out.println("4. Search contact");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("ID: ");
                    int idadd = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Address: ");
                    String address = scanner.nextLine();
                    client.addContact(idadd,name, phone, email, address);
                    break;
                case 2:
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    client.deleteContact(id);
                    break;
                case 3:
                    System.out.print("ID: ");
                    int id2 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Name: ");
                    String name2 = scanner.nextLine();
                    System.out.print("Phone: ");
                    String phone2 = scanner.nextLine();
                    System.out.print("Email: ");
                    String email2 = scanner.nextLine();
                    System.out.print("Address: ");
                    String address2 = scanner.nextLine();
                    client.updateContact(id2, name2, phone2, email2, address2);
                    break;
                case 4:
                    System.out.print("Keyword: ");
                    String keyword = scanner.nextLine();
                    client.searchContact(keyword);
                    break;
                case 5:
                    done = true;
                    client.close();
                    break;
            }
        }
    }
}
