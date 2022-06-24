package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static String splitter(String inputString) {
        String[] str = inputString.split(" ");
        String[] message = str[1].split("=");
        return message[1];
    }

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                   out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String input = splitter(in.readLine());
                    if (input.equals("Hello")) {
                        out.write(input.getBytes());
                        out.flush();
                    } else
                    if (input.equals("Exit")) {
                        out.write(input.getBytes());
                        server.close();
                    } else {
                        out.write(input.getBytes());
                        out.flush();
                    }
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                    }
                     out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in log example", e);
        }
    }
}