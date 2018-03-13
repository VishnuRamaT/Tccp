import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{

private static Socket socket;
static int current=0;
public final static String FILE_TO_RECEIVED = "/home/sosdt010/Desktop/Testfile/text1.txt";
public final static int FILE_SIZE = 4939993 ;
public static void main(String[] args)
{
try
{

int port = 6777;
ServerSocket serverSocket = new ServerSocket(port);
System.out.println("Server Started and listening to the port 6777");

//Server is running always. This is done using this while(true) loop

while(true)
{
//Reading the message from the client

socket = serverSocket.accept();
InputStream is = socket.getInputStream();
FileOutputStream fos=new FileOutputStream(FILE_TO_RECEIVED );
BufferedOutputStream bos=new BufferedOutputStream(fos);
byte [] mybytearray = new byte [FILE_SIZE];
int bytesRead = is.read(mybytearray,0,mybytearray.length);
current = bytesRead;
do {
bytesRead =
is.read(mybytearray, current, (mybytearray.length-current));
if(bytesRead >= 0) current += bytesRead;
} while(bytesRead > -1);

bos.write(mybytearray, 0 , current);
//bos.close();

System.out.println("Message received from client is "+current);

//Sending the response back to the client.
OutputStream out = socket.getOutputStream();
File myFile=new File( FILE_TO_RECEIVED);
FileInputStream fis=new FileInputStream(myFile);
byte [] mybytearray1 = new byte [(int)myFile.length()];

int len ;
while ((len = fis.read(mybytearray1)) >= 0) {
out.write(len);
System.out.println("Message sent to the client is "+ current);
out.flush();
//fis.close();

}

}}
catch (Exception e)
{
e.printStackTrace();

}
finally
{
try
{
socket.close();

}
catch(Exception e){
e.printStackTrace();
}
}
}

}

