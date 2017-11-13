import java.io.*;
import java.net.*;
import java.util.*;
public class ClientString
{
	PrintStream out = null;
	BufferedReader in = null;
	BufferedReader br = null;
	String str;
	String op="";
	public ClientString()throws IOException
	{
		//connecting to the server on port 7000, localhost to be replaced by server ip address
		Socket s = new Socket("localhost", 7000);
		out = new PrintStream(s.getOutputStream());
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc=new Scanner(System.in);
		while (s!=null)
		{
			System.out.println("--------------------------------");
			System.out.println("\n\nSelect the operation you want to perform: \n1. Concatenate.\n2. Convert to UPPER CASE.\n3. Convert to lower case.\n4.Exit");
			op=br.readLine();
			System.out.println("\nEnter the String: ");
			str=sc.next();
			
			out.println("2");	//output to ServerSocket to specify request from String Operations client
			out.println(op);	//output to ServerSocket to specify operation
			out.println(str);	//output to ServerSocket to specify source string
			System.out.println("Request sent to server.");
			System.out.println(in.readLine());
		}
	}
	public static void main(String args[])throws IOException
	{
		ClientString ec = new ClientString();
		System.out.println("Exiting...");
	}
}