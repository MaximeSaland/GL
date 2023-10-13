import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class Worker implements Callable <Integer> {

    private int id;
    private Socket socket;

    private AtomicInteger counter = null;
    Worker(int id, Socket socket, AtomicInteger counter) {
        this.id = id;
        this.socket = socket;
        this.counter = counter;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Thread " + this.id);
        InputStream is = socket.getInputStream();
        byte bytes[] = new byte[1024];
        is.read(bytes);
        String request = new String(bytes);
        System.out.println(request);
        Thread.sleep(5000);
        incrementBy(request.split("\r\n|\r|\n").length - 2);
        System.out.println("Counter: " + counter);
        this.socket.close();
        return id;
    }

    private void incrementBy(int n) {
        for (int i = 0; i < n; i++) {
            counter.incrementAndGet();
        }
    }
}
