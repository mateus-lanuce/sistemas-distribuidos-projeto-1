import Controller.ProcessController;
import Interfaces.MessageInterface;
import Model.Message;
import Model.NodeServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {

        NodeServer p1 = new NodeServer("localhost", 12345, "P1");
        NodeServer p2 = new NodeServer("localhost", 12346, "P2");

        ProcessController processController = new ProcessController(p1, p2);

        //enviar mensagem para o próximo nó de teste
        MessageInterface teste = new Message("teste", p1, p2, "agora", "unicast");
        processController.sendMessage(teste);
    }
}

class Main2 {
    public static void main(String[] args) throws IOException {

        NodeServer p1 = new NodeServer("localhost", 12345, "P1");
        NodeServer p2 = new NodeServer("localhost", 12346, "P2");

        ProcessController processController2 = new ProcessController(p2, p1);

    }
}

// Client class
class Client {

    Socket cliente;
    ObjectInputStream entrada;
    ObjectOutputStream saida;
    String ip;

    // driver code
    public static void main(String[] args)
    {
        // establish a connection by providing host and port
        // number
        try (Socket socket = new Socket("localhost", 12345)) {

            // writing to server
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
//            out.writeObject(new Message("exit", null, null, null));
            out.flush();
            System.out.println("Mensagem enviada para o servidor");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Conexão encerrada");
        }

    }
}

class Client2 {

    Socket cliente;
    ObjectInputStream entrada;
    ObjectOutputStream saida;
    String ip;

    // driver code
    public static void main(String[] args)
    {
        // establish a connection by providing host and port
        // number
        try (Socket socket = new Socket("localhost", 12346)) {

            // writing to server
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
//            out.writeObject(new Message("Hello from the client side!", null, null, null));
            out.flush();
            System.out.println("Mensagem enviada para o servidor");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Conexão encerrada");
        }

    }
}