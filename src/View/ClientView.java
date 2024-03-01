package View;

import Interfaces.ClientInterface;
import Interfaces.MessageInterface;
import Model.Message;
import Model.NodeServer;

import java.util.ArrayList;
import java.util.Scanner;

public class ClientView implements ClientInterface {

    static Scanner scanner = new Scanner(System.in);
    NodeServer serverInformations;
    NodeServer nextNodeServer;

    public ClientView(NodeServer serverInformations, NodeServer nextNodeServer) {
        this.serverInformations = serverInformations;
        this.nextNodeServer = nextNodeServer;
    }

    @Override
    public int getOptionMenu() {
        System.out.println("\nDigite a opção desejada: ");
        return scanner.nextInt();
    }

    @Override
    public MessageInterface Menu(ArrayList<String> sendMessagesLog, ArrayList<String> recivedMessagesLog) {
        // Limpar o console antes de mostrar o menu
        clearConsole();


        System.out.println("Bem vindo ao cliente " + serverInformations.name() + "!");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Enviar mensagem unicast");
        System.out.println("2. Enviar mensagem broadcast");
        System.out.println("3. Ver log de mensagens Enviadas");
        System.out.println("4. Ver log de mensagens Recebidas");
        System.out.println("5. Encerrar conexão");

        int option = getOptionMenu();
        // Limpar o buffer do scanner
        scanner.nextLine();

        switch (option) {
            case 1:
                return getUniCastMessage();
            case 2:
                return getBroadCastMessage();
            case 3:
                showLog(sendMessagesLog);
                break;
            case 4:
                showLog(recivedMessagesLog);
                break;
            case 5:
                return sendExitMessage();
            default:
                showError("Opção inválida");
                break;
        }

        return null;
    }



    @Override
    public void showReceivedMessage(MessageInterface message) {
        System.out.println("Mensagem recebida: " + message.getMessage());
    }

    /**
     * @param message
     */
    @Override
    public void showSentMessage(MessageInterface message) {
        System.out.println("Mensagem enviada: " + message.getMessage());
        System.out.println("Para: " + message.getReceiver().name());
    }

    /**
     * @param log
     */
    @Override
    public void showLog(ArrayList<String> log) {
        for (String message : log) {
            System.out.println(message);
        }
    }

    /**
     * @param text
     */
    @Override
    public void showError(String text) {
        System.out.println(text);
    }

    /**
     * @return
     */
    @Override
    public MessageInterface getUniCastMessage() {
        clearConsole();
        System.out.println("Digite a mensagem que deseja enviar:");
        String message = scanner.nextLine();
        System.out.println("Digite o nome do processo para o qual deseja enviar a mensagem:");
        String receiverName = scanner.nextLine();
//        System.out.println("Digite o ip do processo para o qual deseja enviar a mensagem:");
//        String receiverIp = scanner.nextLine();
        System.out.println("Digite a porta do processo para o qual deseja enviar a mensagem:");
        int receiverPort = scanner.nextInt();

        return new Message(message, serverInformations, new NodeServer("localhost", receiverPort, receiverName), null, "unicast");
    }

    /**
     * @return
     */
    @Override
    public MessageInterface getBroadCastMessage() {
        clearConsole();
        System.out.println("Digite a mensagem que deseja enviar:");
        String message = scanner.nextLine();

        return new Message(message, serverInformations, nextNodeServer, null, "broadcast");
    }

    private MessageInterface sendExitMessage() {
        // enviar mensagem de saída
        System.out.println("Processo encerrado!");
        return new Message("exit", serverInformations, serverInformations, null, "exit");
    }

    private static void clearConsole()
    {
        System.out.println("\033[H\033[2J");

        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }
}
