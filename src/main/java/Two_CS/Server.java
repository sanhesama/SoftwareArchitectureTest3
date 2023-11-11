package Two_CS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class Server {
    private ServerSocket serverSocket;
    private Connection conn;
    private Statement stmt;

    public Server(int port) throws IOException, SQLException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts", "root", "123456");
        stmt = conn.createStatement();
    }

    public void start() throws IOException {
        while (true) {
            new ClientHandler(serverSocket.accept(), stmt).start();
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        Server server = new Server(8000);
        server.start();
    }
}

class ClientHandler extends Thread {
    private Socket socket;
    private Statement stmt;

    public ClientHandler(Socket socket, Statement stmt) {
        this.socket = socket;
        this.stmt = stmt;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] params = inputLine.split(",");
                String cmd = params[0];
                if (cmd.equals("add")) {
                    int id = Integer.parseInt(params[1]);
                    String name = params[2];
                    String phone = params[3];
                    String email = params[4];
                    String address = params[5];
                    stmt.executeUpdate("INSERT INTO contact (id, name, phone, email, address) VALUES ('"+ id + "', '" + name + "', '" + phone + "', '" + email + "', '" + address + "')");
                    out.println("ok");
                } else if (cmd.equals("delete")) {
                    int id = Integer.parseInt(params[1]);
                    stmt.executeUpdate("DELETE FROM contact WHERE id = " + id);
                    out.println("ok");
                } else if (cmd.equals("update")) {
                    int id = Integer.parseInt(params[1]);
                    String name = params[2];
                    String phone = params[3];
                    String email = params[4];
                    String address = params[5];
                    stmt.executeUpdate("UPDATE contact SET name = '" + name + "', phone = '" + phone + "', email = '" + email + "', address = '" + address + "' WHERE id = " + id);
                    out.println("ok");
                } else if (cmd.equals("search")) {
                    String keyword = params[1];
                    ResultSet rs = stmt.executeQuery("SELECT * FROM contact WHERE name LIKE '%" + keyword + "%' OR phone LIKE '%" + keyword + "%'");
                    while (rs.next()) {
                        out.println(rs.getInt("id") + "," + rs.getString("name") + "," + rs.getString("phone") + "," + rs.getString("email") + "," + rs.getString("address"));
                    }
                    out.println("end");
                }
            }

            socket.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
