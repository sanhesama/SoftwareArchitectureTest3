package Three_CS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            // 创建Socket对象，指定服务器地址和端口号
            Socket socket = new Socket("localhost", 8888);

            // 获取输入输出流
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // 添加联系人
            out.println("add,John,123456789");
            String response = in.readLine();
            System.out.println("服务器返回消息：" + response);

            // 查询联系人
            out.println("query,John");
            response = in.readLine();
            System.out.println("服务器返回消息：" + response);

            // 修改联系人
            out.println("update,John,987654321");
            response = in.readLine();
            System.out.println("服务器返回消息：" + response);

            // 查询联系人
            out.println("query,John");
            response = in.readLine();
            System.out.println("服务器返回消息：" + response);

            // 删除联系人
            out.println("delete,John");
            response = in.readLine();
            System.out.println("服务器返回消息：" + response);

            // 查询联系人
            out.println("query,John");
            response = in.readLine();
            System.out.println("服务器返回消息：" + response);

            // 关闭连接
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
