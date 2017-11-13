import java.io.*;
import java.util.*;
import java.net.*;

public class ClientCalcB
{
	PrintStream out = null;
	BufferedReader in = null;
	BufferedReader br = null;
	
	public ClientCalcB()throws IOException
	{
		Scanner sc=new Scanner(System.in);
		//connecting to the server on port 7000, localhost to be replaced by server ip address
		Socket s = new Socket("localhost", 7000);
		out = new PrintStream(s.getOutputStream());
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		br = new BufferedReader(new InputStreamReader(System.in));
		int n1, n2;
		String t1, t2;
	String op="";
		while (s!=null)
		{
			System.out.println("--------------------------------");
			//input for performing calculator operations 
			System.out.println("\n\nSelect the operation you want to perform: \n1. Addition.\n2. Subtraction.\n3. Multiplication.\n4. Division.\n5. Modulus.\n6. Exit.");
			op=br.readLine();
			System.out.println("\nEnter the operands: ");
			n1=sc.nextInt();
			n2=sc.nextInt();

			out.println("1");	//output to ServerSocket to specify request from Calculator client
			out.println(op);	//output to ServerSocket to send operation
			t1=Integer.toString(n1);
			out.println(t1);	//output to ServerSocket to send 1st operand
			t2=Integer.toString(n2);
			out.println(t2);	//output to ServerSocket to send 2nd operand
			System.out.println("Request sent to server.");
			System.out.println(in.readLine());
		}
	}
	public static void main(String args[])throws IOException
	{
		ClientCalcB ec = new ClientCalcB();
		System.out.println("Exiting...");
	}
}