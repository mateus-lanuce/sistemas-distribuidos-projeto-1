import Controller.ProcessController;
import Model.NodeServer;
import java.io.IOException;

public class Anel {
    public static void main(String[] args) throws IOException {

        NodeServer p1 = new NodeServer("localhost", 12345, "P1");
        NodeServer p2 = new NodeServer("localhost", 12346, "P2");

        ProcessController processController = new ProcessController(p1, p2);
    }
}

class Main2 {
    public static void main(String[] args) throws IOException {

        NodeServer p2 = new NodeServer("localhost", 12346, "P2");
        NodeServer p3 = new NodeServer("localhost", 12347, "P3");

        ProcessController processController2 = new ProcessController(p2, p3);


    }
}

class Main3 {
    public static void main(String[] args) throws IOException {

        NodeServer p3 = new NodeServer("localhost", 12347, "P3");
        NodeServer p4 = new NodeServer("localhost", 12348, "P4");

        ProcessController processController2 = new ProcessController(p3, p4);

    }
}

class Main4 {
    public static void main(String[] args) throws IOException {

        NodeServer p4 = new NodeServer("localhost", 12348, "P4");
        NodeServer p1 = new NodeServer("localhost", 12345, "P1");

        ProcessController processController2 = new ProcessController(p4, p1);

    }
}