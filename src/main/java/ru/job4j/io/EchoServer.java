package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String s = in.readLine();
                    if (s.contains("msg=") || s.contains("msg= ")) {
                        throw new IllegalArgumentException("Message not found");
                    }
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
        } catch (Exception e) {
           LOG.error("Exception in message", e);
        }
    }
}
