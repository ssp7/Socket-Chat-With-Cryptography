package edu.unl.cse.csce361.socket_chat;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Chat {

    private Socket socket;
    private Scanner scanner;
    private boolean isHost;

    public Chat() {
        scanner = new Scanner(System.in);
        try {
            socket = connect();
        } catch (IOException ioException) {
            System.err.println("Connection failed: " + ioException);
            System.exit(1);
        }
    }

    @SuppressWarnings("WeakerAccess")
    public void communicate() {
        try {
            communicate(
                    new BufferedReader(new InputStreamReader(System.in)),
                    new BufferedReader(new InputStreamReader(socket.getInputStream())),
                    System.out,
                    new PrintStream(socket.getOutputStream()));
            socket.close();
        } catch (IOException ioException) {
            System.err.println("Connection dropped: " + ioException);
            System.exit(1);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void communicate(BufferedReader localInput,
                             BufferedReader remoteInput,
                             PrintStream localOutput,
                             PrintStream remoteOutput) {
        System.out.println("Connection established. Host goes first.");
        String message = "";
        boolean myTurnToTalk = isHost;
        try {
            while (!message.equals("EXIT")) {
                if (myTurnToTalk) {
                    message = localInput.readLine();
                    remoteOutput.println(encipher(message));
                } else {
                    message = decipher(remoteInput.readLine());
                    localOutput.println(message);
                }
                myTurnToTalk = !myTurnToTalk;
            }
        } catch (IOException ioException) {
            System.err.println("Connection dropped: " + ioException);
            System.exit(1);
        }
    }

    private String encipher(String plaintext) {
//        String ciphertext = ...;
//        return ciphertext;
        return plaintext;
    }

    private String decipher(String ciphertext) {
//        String plaintext = ...;
//        return plaintext;
        return ciphertext;
    }

    private Socket connect() throws IOException {
        System.out.print("Are you the chat host? [Y] ");
        String answerString = scanner.nextLine().toUpperCase();
        char answer = answerString.length() > 0 ? answerString.charAt(0) : 'Y';
        isHost = (answer != 'N');
        return isHost ? connectAsServer() : connectAsClient();
    }

    private Socket connectAsServer() throws IOException {
        byte[] address = InetAddress.getLocalHost().getAddress();
        System.out.println("Host address: " + address[0] + "." + address[1] + "." + address[2] + "." + address[3]);
        String prompt = "Select port number";
        int port = getPort(prompt);
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Waiting for client.");
        return serverSocket.accept();
    }

    private Socket connectAsClient() throws IOException {
        byte[] address = getRemoteHostAddress();
        String prompt = "Enter port host is opening at " +
                address[0] + "." + address[1] + "." + address[2] + "." + address[3];
        int port = getPort(prompt);
        return new Socket(InetAddress.getByAddress(address), port);
    }

    private byte[] getRemoteHostAddress() {
        // This assumes IPv4. Probably a good assumption.
        boolean haveGoodAddress = false;
        byte[] address = new byte[4];
        while (!haveGoodAddress) {
            System.out.print("Enter IP address of host <##.##.##.##>: ");
            haveGoodAddress = true;
            try {
                String addressString = scanner.nextLine();
                String[] tokens = addressString.split("\\.");
                if (tokens.length == 4) {
                    for (int i = 0; i < 4; i++) {
                        address[i] = Byte.parseByte(tokens[i]);
                    }
                } else {
                    System.out.println("The IP address should be four dot-separated numbers; <"
                            + addressString + "> does not conform.");
                    haveGoodAddress = false;
                }
            } catch (NumberFormatException nfException) {
                System.out.println("The IP address should be exactly as reported to the host user.");
                String message = nfException.getMessage();
                if (message.startsWith("Value out of range. Value")) {
                    String[] messageTokens = message.split("\"");
                    long value = Long.parseLong(messageTokens[1]);   // this may break if message format changes
                    if ((127 < value) && (value < 256)) {
                        System.out.println("Note that Java does not have unsigned integers, so subtract 256 from " +
                                "values greater than 127. For example, " + value + " should be " + (value - 256) + ".");
                    }
                }
                haveGoodAddress = false;
            }
        }
        return address;
    }

    private int getPort(String prompt) {
        boolean haveGoodNumber = false;
        int port = 0;
        while (!haveGoodNumber) {
            System.out.print(prompt + ": ");
            haveGoodNumber = true;
            try {
                port = scanner.nextInt();
                if (port < 0) throw new InputMismatchException("Expected non-negative value, got " + port);
                if (port >= 2 * (Short.MAX_VALUE + 1)) {
                    throw new InputMismatchException("Expected value less than 65536, got " + port);
                }
            } catch (InputMismatchException ignored) {
                System.out.println("The port number must be a positive integer strictly less than 65536.");
                haveGoodNumber = false;
            } finally {
                scanner.nextLine();
            }
        }
        return port;
    }

    public static void main(String[] args) {
        Chat chat = new Chat();
        chat.communicate();
    }
}
