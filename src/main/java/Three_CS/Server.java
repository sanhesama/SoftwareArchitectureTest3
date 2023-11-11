package Three_CS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static Map<String, String> contacts = new HashMap<>();

    public static void main(String[] args) {
        try {
            // 创建ServerSocket对象，并监听指定端口号
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("服务器已启动，等待客户端连接...");

            while (true) {
                // 等待客户端连接
                Socket clientSocket = serverSocket.accept();
                System.out.println("客户端已连接：" + clientSocket.getInetAddress().getHostAddress());

                // 创建线程处理客户端请求
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                // 获取输入输出流
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // 处理客户端请求
                String request;
                while ((request = in.readLine()) != null) {
                    if (request.equals("exit")) {
                        break;
                    }

                    String response = processRequest(request);
                    out.println(response);
                }

                // 关闭连接
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String processRequest(String request) {
            // 分割请求参数
            String[] params = request.split(",");
            String operation = params[0];
            String name = params[1];

            switch (operation) {
                case "add":
                    String phoneNumber = params[2];
                    contacts.put(name, phoneNumber);
                    return "联系人添加成功";
                case "delete":
                    contacts.remove(name);
                    return "联系人删除成功";
                case "query":
                    String phoneNumber1 = contacts.get(name);
                    if (phoneNumber1 != null) {
                        return "联系人电话：" + phoneNumber1;
                    } else {
                        return "联系人不存在";
                    }
                case "update":
                    String newPhoneNumber = params[2];
                    if (contacts.containsKey(name)) {
                        contacts.put(name, newPhoneNumber);
                        return "联系人修改成功";
                    } else {
                        return "联系人不存在";
                    }
                default:
                    return "无效的请求";
            }
        }
    }
}
