package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String s = in.readLine();
                    if (s.contains("msg=Exit")) {
                        server.close();
                    } else if (s.contains("msg=Hello")) {
                        System.out.println("Hello");
                    } else {
                        System.out.println("What");
                    }
                    out.flush();
                }
            }
        }
    }
}
