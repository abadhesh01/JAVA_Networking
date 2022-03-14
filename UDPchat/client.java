import java.net.*;
public class client
{
  public static void main(String []args)throws Exception
  {
    
    java.util.Scanner  sc = new java.util.Scanner(System.in);

    int server_port = 8001;
    int client_port = 8999;

    InetAddress ia = InetAddress.getLocalHost();
    System.out.println("Server Host Name: "+ia.getHostName());
    System.out.println("Server Host Address: "+ia.getHostAddress());

    DatagramSocket ds = new DatagramSocket(client_port);
    DatagramPacket dp = null;
    byte buffer[] = new byte[100];

    String str = null;
    while(true)
    {
      
      //Sending	    
      System.out.print("Enter your message: ");	    
      str = sc.nextLine();
      buffer = str.getBytes();      
      dp = new DatagramPacket(buffer,buffer.length,ia,server_port);
      ds.send(dp);

      //Receiving
      System.out.println("Waiting for Server's response.");
      dp = new DatagramPacket(buffer,buffer.length);
      ds.receive(dp);
      str = new String(dp.getData(),0,dp.getLength());
      System.out.println("Message received.");
      System.out.println("Server: "+str);

      //Termination Criteria.
      if(str.equalsIgnoreCase("exit"))
      {
	 ds.close();     
         break;
      }

    }
  }
}
