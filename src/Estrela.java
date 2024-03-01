import Controller.ProcessController;
import Controller.ProcessControllerEstrela;
import Interfaces.MessageInterface;
import Model.NodeServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Estrela {
    public static void main(String[] args) throws IOException {

        NodeServer p1 = new NodeServer("localhost", 12345, "P1");
        NodeServer p2 = new NodeServer("localhost", 12346, "P2");
        NodeServer p3 = new NodeServer("localhost", 12347, "P3");
        NodeServer p4 = new NodeServer("localhost", 12348, "P4");
        ArrayList<NodeServer> nextNodeServers = new ArrayList<>();
        nextNodeServers.add(p2);
        nextNodeServers.add(p3);
        nextNodeServers.add(p4);

        ProcessControllerEstrela processController = new ProcessControllerEstrela(p1, nextNodeServers);
    }
}

class MainEstrela2 {
    public static void main(String[] args) throws IOException {

        NodeServer p2 = new NodeServer("localhost", 12346, "P2");
        NodeServer p1 = new NodeServer("localhost", 12345, "P1");

        ProcessControllerEstrela processController2 = new ProcessControllerEstrela(p2, p1);

    }
}

class MainEstrela3 {
    public static void main(String[] args) throws IOException {

        NodeServer p3 = new NodeServer("localhost", 12347, "P3");
        NodeServer p1 = new NodeServer("localhost", 12345, "P1");

        ProcessControllerEstrela processController2 = new ProcessControllerEstrela(p3, p1);

    }
}

class MainEstrela4 {
    public static void main(String[] args) throws IOException {

        NodeServer p4 = new NodeServer("localhost", 12348, "P4");
        NodeServer p1 = new NodeServer("localhost", 12345, "P1");

        ProcessControllerEstrela processController2 = new ProcessControllerEstrela(p4, p1);

    }
}