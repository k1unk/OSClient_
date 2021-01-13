import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Client c = new Client();
    }

    Client() throws IOException {
        st();
    }

    public static void st() throws IOException {
        Socket s = new Socket("localhost", 4999);
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        String text;
        //читаем команды из командной строки, пока не получим команду finishClient
        while (!(text = buff.readLine()).equals("finishClient")) {

            pr.println(text);
            pr.flush();
            BufferedReader bf = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String str;

            str = bf.readLine();
            System.out.println(str);

            //при ожидании - отправляем очередной запрос на сервер
            if (str.equals("wait")) {
                System.out.println("waiting");
                pr.println(text);
                pr.flush();
                while ((bf.readLine()).equals("wait")) {
                    System.out.println("waiting");
                    pr.println(text);
                    pr.flush();
                }
            }
           // if (true) return ;
        }
        pr.println("clientFinishedToWrite");
        pr.flush();
    }
}
