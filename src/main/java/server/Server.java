package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final Integer LOCALHOST_PORT = 8088;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(LOCALHOST_PORT)) {
            System.out.println("Сервер стартанул");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter outWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader inReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    String name = inReader.readLine();
                    System.out.println(String.format("Подключение установлено. Информация от клиента: " +
                            "Hi %s, your port is %d", name, clientSocket.getPort()));
                    outWriter.println(clientSocket.getPort());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
