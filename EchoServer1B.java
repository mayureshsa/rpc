import java.io.*;
import java.util.*;
import java.net.*;

public class EchoServer1B implements Runnable 
{
	Socket socket=null;
	static ServerSocket ss;
	EchoServer1B (Socket newSocket)
	{
		this.socket = newSocket;
	}
	public static void main (String args[]) throws IOException
	{		
		ss=new ServerSocket(7000);
		System.out.println("Server Started");
		while (true)
		{
			//listening for connection and accepting it
			Socket s = ss.accept();
			EchoServer1B es = new EchoServer1B(s);
			Thread t = new Thread(es);
			t.start();
		}
	}
	public void run()
	{
		int n1, n2, res=0;
		String str="", str1="", restr="";
		Scanner sc=new Scanner(System.in);
		try
		{
			while(ss!=null)
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintStream out = new PrintStream(socket.getOutputStream());
				BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
				//obtaining inputs to differentiate request is from : 1- Calculator client, 2- String operation client
				String oper= in.readLine();
				
				switch(oper)
				{
					case "1": String op=in.readLine();	//obtaining operation from Calculator Client
								 String no1=in.readLine();		//obtaining first operand from calculator client
								 String no2=in.readLine();		//obtaining second operand from calculator client
								 n1=Integer.parseInt(no1);
								 n2=Integer.parseInt(no2);
								 System.out.println("Request received from calculator client.");
								 int opi=Integer.parseInt(op);
								 switch(opi)
								 {
									 case 1: res=n1+n2;
											break;
									 case 2: res=n1-n2;
											break;
									 case 3: res=n1*n2;
											break;
									 case 4: res=n1/n2;
											break;
									 case 5: res=n1%n2;
											break;
									case 6: break;	
								 }
								 str=Integer.toString(res);
								 out.println(str);
								 break;
					case "2": op=in.readLine();		//obtaining operation from String Operations Client
								opi=Integer.parseInt(op);		
								str=in.readLine();		//obtaining source string from String Operations client
								 switch(opi)
								 {
									case 1: System.out.println("Enter the string to concatenate: ");
											str1=sc.next();
											restr=str.concat(str1);
											break;
									case 2: restr=str.toUpperCase();
											break;
									case 3: restr=str.toLowerCase();
											break;
									case 4: break;
								}
								out.println(restr);
								break;
			
				}
			}
		}
		catch(Exception e)
		{ 
		}
	}
}
