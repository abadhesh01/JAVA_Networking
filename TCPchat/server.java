import java.io.*;
import java.net.*;
public class server
{
  public static void main(String []args) throws Exception
  {
      java.util.Scanner sc = new java.util.Scanner(System.in);
      int port = 8389;

      InetAddress ia = InetAddress.getLocalHost();
      System.out.println("Server Host Name: "+ia.getHostName());
      System.out.println("Server Host Address: "+ia.getHostAddress());

      ServerSocket ssk = new ServerSocket(port,1);
      Socket sk = ssk.accept();
      System.out.println("Client accepted successfully.");

      //Writes data to the socket.
      DataOutputStream message = new DataOutputStream(sk.getOutputStream());
      //Reads data from the socket.
      DataInputStream response = new DataInputStream(sk.getInputStream());

      String str = null; 
      while(true)
      {

	//Receiving      
	System.out.println("Waiting for Client's response.");      
        str = response.readUTF();
        System.out.println("Client: "+str);

	//Sending
	System.out.print("Enter your message: ");
        str = sc.nextLine();
        message.writeUTF(str);
        message.flush(); 

	//Termination Criteria.
	if(str.equalsIgnoreCase("exit"))
        {		
	    break;
        }	    
      
      }

      ssk.close();
      sk.close();
      message.close();
      response.close();
  }
}
