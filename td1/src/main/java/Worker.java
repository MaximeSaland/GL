import java.net.Socket;
import java.util.concurrent.Callable;

public class Worker implements Callable <Integer> {

    private int id;
    private Socket socket;
    public void Worker(int id, Socket socket) {
        this.id = id;
        this.socket = socket;
    }

    @Override
    public Integer call() throws Exception {
        return 0;
    }
}
