import java.net.*;
import java.io.*;

public class AddServer{
  static int requestCount = 0;
  public static void main(String[] args){
      try{
        ServerSocket socket = new ServerSocket(6013);
        while(true){
          Socket client = socket.accept();
          PrintWriter printer = new PrintWriter(client.getOutputStream(), true);
          printer.println("We have received total of "+requestCount+" requests.");
          client.close();
        }
      }
      catch(IOException ioe){
        System.err.println(ioe);
      }
  }
}
