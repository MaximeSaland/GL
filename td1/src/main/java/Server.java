import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.*;

public class Server {

    private final int maxConnection = 2;
    private Worker[] workerList;

    public void start() throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(maxConnection);
        try (ServerSocket serverSocket = new ServerSocket(2134)) {
            System.out.println("Connexion waiting");
//        Queue<Future<Integer>> resultList = new LinkedBlockingQueue<>();
            int i = 0;
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                Future<Integer> result = pool.submit(new Worker(i, socket));
                //          resultList.add(result);
/*              InputStream is = socket.getInputStream();
                byte bytes[] = new byte[1024];
                int byteRead = is.read(bytes);
                String request = new String(bytes);
                System.out.println("Le client demande : " + request);
                OutputStream os = socket.getOutputStream();
                os.write(getContent(request));*/
                i = i + 1;
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
            System.out.println("End of program");
        }
    }

    byte [] toHtlm(String htmlContent) {
        String ret = "HTTP/1.1 200 OK\nContent-Type: text/html\n\n" + htmlContent;
        return ret.getBytes();
    }
    byte [] getContent(String request) {
        return toHtlm("<h1> Mon titre </h1>\nJe suis un contenu");
    }
}
