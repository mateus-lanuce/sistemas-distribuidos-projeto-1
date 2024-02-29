package Controller;

import Model.Server.ServerTCP;

import java.io.IOException;
import java.net.Socket;

public class ProcessController {

    private int serverPort;
    private int clientPort;
    private ServerTCP serverThread;

    public ProcessController(int serverPort) {
        this.serverPort = serverPort;

        //iniciar a thread do servidor
        serverThread = new ServerTCP(serverPort);
        new Thread(serverThread).start();

        System.out.println("jorge");
    }

    public void run() {
        try {
            Socket socket = new Socket("localhost", serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
