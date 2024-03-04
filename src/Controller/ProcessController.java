package Controller;

import Interfaces.ClientInterface;
import Interfaces.MessageInterface;
import Model.NodeServer;
import Model.Server.ServerTCPAnel;
import View.ClientView;

import java.io.IOException;
import java.util.ArrayList;

public class ProcessController {

    private NodeServer server;
    private ServerTCPAnel serverImp;
    private Thread serverThread;

    public ProcessController(NodeServer server, NodeServer nextNodeServer) {
        this.server = server;

        //iniciar a thread do servidor
        this.serverImp = new ServerTCPAnel(server, nextNodeServer);
        serverThread = new Thread(serverImp);
        serverThread.start();

        ClientInterface client = new ClientView(server, nextNodeServer);

        while (serverThread.isAlive()) {
            serverImp.showInformations();
            ArrayList<String> sendMessagesLog = serverImp.getSendMessagesLog();
            ArrayList<String> recivedMessagesLog = serverImp.getRecivedMessagesLog();

            MessageInterface message = client.Menu(sendMessagesLog, recivedMessagesLog);

            if (message != null) {

                //se a mensagem for do tipo exit, encerrar a thread do servidor
                if (message.getType().equals("exit")) {
                    serverThread.interrupt();
                    System.exit(0);
                    break;
                }

                try {
                    sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void sendMessage(MessageInterface message) throws IOException {
        serverImp.receiveMessage(message);
    }

    public ServerTCPAnel getServerImp() {
        return serverImp;
    }
}
