import java.io.*;
import java.net.*;

public class client
{
  public static void main(String []args) throws Exception
  {
      java.util.Scanner sc = new java.util.Scanner(System.in);
      int port = 8389;

      InetAddress ia = InetAddress.getLocalHost();
      System.out.println("Client Host Name: "+ia.getHostName());
      System.out.println("Client Host Address: "+ia.getHostAddress());

      Socket sk = new Socket(ia,port);

      //Writes data to the socket.
      DataOutputStream message = new DataOutputStream(sk.getOutputStream());
      //Reads data from the socket.
      DataInputStream response = new DataInputStream(sk.getInputStream());

      String str = null;
      while(true)
      {	      

	//Sending      
        System.out.print("Enter your message: ");
        str = sc.nextLine();
        message.writeUTF(str);
        message.flush();        

	//Receiving
	System.out.println("Waiting for Server's response.");
	str = response.readUTF();
        System.out.println("Server: "+str);

        //Termination Criteria.
	if(str.equalsIgnoreCase("exit"))
        {
            break;
        }
      
      }

      sk.close();
      message.close();
      response.close();
  }
}

