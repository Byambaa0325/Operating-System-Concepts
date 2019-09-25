import java.net.*;
import java.io.*;

public class AddClient{
  public static void main(String[] args){
    try{
      Socket socket = new Socket("127.0.0.1",6013);
      InputStream in = socket.getInputStream();
      BufferedReader bin = new BufferedReader(new InputStreamReader(in));
      String line;
      while((line = bin.readLine()) != null){
        System.out.println(line);
      }
      socket.close();
    }catch(IOException ioe){
      System.err.println(ioe);
    }
  }
}
