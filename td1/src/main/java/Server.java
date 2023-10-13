import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {

    private final int maxConnection = 2;
    private Worker[] workerList;

    private boolean isRunning = true;
    private AtomicInteger headerLineCount =  new AtomicInteger(0);

    public void start() throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(maxConnection);

        try (ServerSocket serverSocket = new ServerSocket(2134)) {
            serverSocket.setSoTimeout(200);
            System.out.println("Connexion waiting");
            stopServer(serverSocket);
            int socketId = 0;
            while (isRunning) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println(socket);
                    System.out.println("Client connected");
                    pool.submit(new Worker(socketId, socket, headerLineCount));
                    socketId++;
                } catch (SocketTimeoutException e) {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
            System.out.println("End of program");
        }
    }

    private void stopServer(ServerSocket ss) {
        Thread closeServer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket closeSocket = new ServerSocket(2135);
                    closeSocket.accept();
                    isRunning = false;
                    closeSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        closeServer.start();
    }

    byte [] toHtlm(String htmlContent) {
        String ret = "HTTP/1.1 200 OK\nContent-Type: text/html\n\n" + htmlContent;
        return ret.getBytes();
    }
    byte [] getContent(String request) {
        return toHtlm("<h1> Mon titre </h1>\nJe suis un contenu");
    }
}
