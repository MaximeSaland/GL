import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final int maxConnection= 2;
    private Worker[] workerList;

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(2134);
        System.out.println("Connexion waiting");

        int nbConnection = 0;
        while (nbConnection < maxConnection) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            nbConnection ++;

            InputStream is = socket.getInputStream();
            byte bytes[] = new byte[1024];
            int byteRead = is.read(bytes);
            String request = new String(bytes);

            System.out.println("Le client demande : " + request);
            OutputStream os = socket.getOutputStream();
            os.write(getContent(request));
        }
        System.out.println("End of program");
        serverSocket.close();

    }

    byte [] toHtlm(String htmlContent) {
        String ret = "HTTP/1.1 200 OK\nContent-Type: text/html\n\n" + htmlContent;
        return ret.getBytes();
    }
    byte [] getContent(String request) {
        return toHtlm("<h1> Mon titre </h1>\nJe suis un contenu");
    }
}
