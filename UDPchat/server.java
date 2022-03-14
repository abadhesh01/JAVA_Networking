import java.net.*;
public class server
{
  public static void main(String []args)throws Exception
  {
    
    java.util.Scanner  sc = new java.util.Scanner(System.in);   

    int server_port = 8001;
    int client_port = 8999;

    InetAddress ia = InetAddress.getLocalHost();
    System.out.println("Server Host Name: "+ia.getHostName());
    System.out.println("Server Host Address: "+ia.getHostAddress());

    DatagramSocket ds = new DatagramSocket(server_port);
    DatagramPacket dp = null;
    byte buffer[] = new byte[100];

    String str = null;
    while(true)
    {
      
      //Receiving 	    
      System.out.println("Waiting for Client's response.");	    
      dp = new DatagramPacket(buffer,buffer.length);	    
      ds.receive(dp);
      str = new String(dp.getData(),0,dp.getLength());
      System.out.println("Message received.");
      System.out.println("Client: "+str);
      
      //Sending 
      System.out.print("Enter your message: ");
      str = sc.nextLine();
      buffer = str.getBytes();	 	
      dp = new DatagramPacket(buffer,buffer.length,ia,client_port);
      ds.send(dp);
      
      //Termination Criteria.
      if(str.equalsIgnoreCase("exit"))
      {
         ds.close();
         break;
      }
    
    }
  }
}
