import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

public class Worker implements Callable <Integer> {

    private int id;
    private Socket socket;
    Worker(int id, Socket socket) {
        this.id = id;
        this.socket = socket;
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(5000);
        System.out.println("Thread " + this.id);
        OutputStream os = socket.getOutputStream();
        //os.write("coucou".getBytes());
        this.socket.close();
        return id;
    }
}
