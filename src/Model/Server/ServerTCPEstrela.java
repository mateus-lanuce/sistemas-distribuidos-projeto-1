package Model.Server;

import Interfaces.ServerInterface;
import Interfaces.MessageInterface;
import Model.Message;
import Model.NodeServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ServerTCPEstrela implements Runnable, ServerInterface {

    ServerSocket serverSocket;
    Socket ClientSocket;
    ArrayList<NodeServer> nextNodeServers;
    ObjectOutputStream output;
    ObjectInputStream input;
    private ArrayList<MessageInterface> sendMessagesLog = new ArrayList<>();
    private ArrayList<MessageInterface> recivedMessagesLog = new ArrayList<>();
    private ArrayList<String> redirectLog = new ArrayList<>();
    NodeServer Iam;

    public ServerTCPEstrela(NodeServer Iam, ArrayList<NodeServer> nextNodeServers) {
        this.Iam = Iam;
        this.nextNodeServers = nextNodeServers;
    }

    public void run() {
        startServer();
    }

    /**
     * @param message the message to be sent
     * @return
     */
    public void sendMessage(MessageInterface message) {

       //se a mensagem for broadcast e o tamanho da lista de próximos nós for maior que 1, enviar para todos os próximos nós
        if (message.getType().equals("broadcast") && nextNodeServers.size() > 1) {
            for (NodeServer nextNodeServer : nextNodeServers) {
                sendMessage(message, nextNodeServer);

                //gerar um delay para não enviar todas as mensagens ao mesmo tempo
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {

            //se eu for o nó de controle, enviar a mensagem para o nó de destino
            if (nextNodeServers.size() > 1) {
                NodeServer receiver = message.getReceiver();
                sendMessage(message, receiver);
            } else {
                //enviar a mensagem para o próximo nó nesse caso apenas se não for um broadcast
                if (!message.getType().equals("broadcast")) {
                    sendMessage(message, nextNodeServers.get(0));
                }
            }
        }
    }

    private void sendMessage(MessageInterface message, NodeServer receiver) {

        try (Socket socket = new Socket(receiver.ip(), receiver.port())) {

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("Enviando mensagem para o próximo nó: " + receiver.name() + " - eu sou o servidor: " + Iam.name());

            out.writeObject(message);
            out.flush();

            socket.close();
            out.close();

            saveLog((Message) message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isMessageForMe(MessageInterface messageInterface) {
        Message message = (Message) messageInterface;
        return message.getReceiver().name().equals(Iam.name());
    }

    public void receiveMessage(MessageInterface message) throws IOException {
        //verificar se a mensagem é para mim
        if (isMessageForMe(message)) {
            saveLog(message);

            //se a mensagem for um broadcast, enviar para o próximo nó
            if (message.getType().equals("broadcast")) {
                sendMessage(message);
            }
        } else {
            sendMessage(message);
        }

    }

    private void saveLog(MessageInterface message) {
        String log;
        if (isMessageForMe(message) || message.getType().equals("broadcast")) {

            showLog(message.toString());
            recivedMessagesLog.add(message);
        } else {

            showLog(message.toString());
            sendMessagesLog.add(message);
        }

    }

    private void showLog(String message) {
        System.out.println("\n");
        System.out.println(message);
        System.out.println("\n");
    }

    private void startServer() {
        try {
            serverSocket = new ServerSocket(Iam.port());

            while (!serverSocket.isClosed()) {

                ClientSocket = serverSocket.accept();

                System.out.println("Conexão estabelecida com o cliente " + ClientSocket.getInetAddress().getHostAddress());

                output = new ObjectOutputStream(ClientSocket.getOutputStream());
                input = new ObjectInputStream(ClientSocket.getInputStream());

                Message message = (Message) input.readObject();

                //receber a mensagem
                receiveMessage(message);

                // fecha a conexão com o cliente se a mensagem for "exit" e for para mim
                if (message.getMessage().equalsIgnoreCase("exit") && isMessageForMe(message)) {
                    serverSocket.close();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showInformations() {

        try {
            System.out.println("Servidor rodando na porta " + serverSocket.getLocalPort());
            System.out.println("HostAddress = " + InetAddress.getLocalHost().getHostAddress());
            System.out.println("HostName = " + InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getRecivedMessagesLog() {

        ArrayList<String> log = new ArrayList<>();
        for (MessageInterface message : recivedMessagesLog) {
            log.add(message.toString());
        }

        return log;
    }

    public ArrayList<String> getSendMessagesLog() {
        ArrayList<String> log = new ArrayList<>();
        for (MessageInterface message : sendMessagesLog) {
            log.add(message.toString());
        }

        return log;
    }
}
