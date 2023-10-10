import java.io.IOException;

class Main {
    public static void main(String [] args) {
        Server s = new Server();
        try{
            s.start();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
