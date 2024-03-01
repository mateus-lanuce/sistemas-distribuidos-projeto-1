package Controller;

import Interfaces.MessageInterface;
import Model.NodeServer;
import Model.Server.ServerTCP;

public class ProcessController {

    private NodeServer server;
    private ServerTCP serverThread;

    public ProcessController(NodeServer server, NodeServer nextNodeServer) {
        this.server = server;

        //iniciar a thread do servidor
        serverThread = new ServerTCP(server, nextNodeServer);
        new Thread(serverThread).start();



    }

    public void sendMessage(MessageInterface message) {
        serverThread.sendMessage(message);
    }


}
