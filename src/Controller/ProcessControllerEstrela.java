package Controller;

import Interfaces.ClientInterface;
import Interfaces.MessageInterface;
import Model.NodeServer;
import Model.Server.ServerTCPAnel;
import Model.Server.ServerTCPEstrela;
import View.ClientView;

import java.io.IOException;
import java.util.ArrayList;

public class ProcessControllerEstrela {

    private NodeServer server;
    private ServerTCPAnel serverImp;
    private Thread serverThread;

    public ProcessControllerEstrela(NodeServer server, ArrayList<NodeServer> nextNodeServers) {
        this.server = server;

        //iniciar a thread do servidor
        ServerTCPEstrela serverImp = new ServerTCPEstrela(server, nextNodeServers);
        serverThread = new Thread(serverImp);
        serverThread.start();

        ClientInterface client = new ClientView(server, server);

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

                serverImp.sendMessage(message);
            }
        }

    }

    public ProcessControllerEstrela(NodeServer server, NodeServer nextNodeServer) {
        this.server = server;
        ArrayList<NodeServer> NodeArray = new ArrayList<>();
        NodeArray.add(nextNodeServer);

        //iniciar a thread do servidor
        ServerTCPEstrela serverImp = new ServerTCPEstrela(server, NodeArray);
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

                serverImp.sendMessage(message);
            }
        }
    }

    public ServerTCPAnel getServerImp() {
        return serverImp;
    }
}
