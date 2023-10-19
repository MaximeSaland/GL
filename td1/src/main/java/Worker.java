import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class Worker implements Callable <Integer> {

    private int id;
    private Socket socket;

    private AtomicInteger counter = null;

    private static final String HTML_PATH= "html";

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

        String content = parseRequest(request);
        OutputStream os = socket.getOutputStream();
        if (content != null) {
            os.write(htmlToByte(content));
        }
        else {
            os.write(htmlToByte("<h1>404 Error file not found </h1>"));
        }

        // Exercice précédent
        //Thread.sleep(5000);

        incrementBy(request.split("\r\n|\r|\n").length - 2);
        System.out.println("Counter: " + counter);

        os.close();
        is.close();
        this.socket.close();
        return id;
    }

    private void incrementBy(int n) {
        for (int i = 0; i < n; i++) {
            counter.incrementAndGet();
        }
    }

    private String parseRequest(String request) {
        String path = request.split("\n")[0].split(" ")[1].split("\\?")[0];
        String[] queryParams = request.split("\n")[0].split(" ")[1].split("\\?");
        HashMap<String, String> params = new HashMap<>();
        final String[] content = {null};
        try {
            content[0] = getFileContent(HTML_PATH + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (queryParams.length == 2) {
            String[][] queryParams2 = Arrays.stream(queryParams[1].split("&"))
                .map(e -> e.split("="))
                .toArray(String[][]::new);
            for (String[] param : queryParams2) {
                params.put(param[0], param[1]);
            }
            params.forEach((k, v) -> {content[0] = content[0].replaceAll("\\$\\{"+k+"\\}", v);});
        }
        return content[0];
    }

    private String getFileContent(String filePath) throws Exception{
        Path path = Paths.get(filePath);
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes);
    }

    byte [] htmlToByte(String htmlContent) {
        String ret = "HTTP/1.1 200 OK\nContent-Type: text/html\n\n" + htmlContent;
        return ret.getBytes();
    }
}
