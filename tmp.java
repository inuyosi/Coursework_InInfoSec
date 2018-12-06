import java.io.*;
import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class SKencrypt implements Serializable{
 public static void main(String args[]){
  	try{
	System.out.println("a");
	FileInputStream inFile = new FileInputStream(args[0]);
	FileInputStream keyFile = new FileInputStream(args[1]);
	SecretKey skey = (SecretKey) keyFile;
	byte[] buf = new byte[inFile.available()];
	byte[] gbuf = new byte[inFile.available()];
	int len;
	Cipher cipher = Cipher.getInstance(args[3]);
	cipher.init(Cipher.ENCRYPT_MODE,skey);
	FileOutputStream outFile = new FileOutputStream(args[2]);
	ObjectOutputStream encrypted = new ObjectOutputStream(outFile);
	while(( len = inFile.read(buf)) != -1){
		gbuf = cipher.update(buf,0,len);
		encrypted.write(gbuf);
	}
	cipher.doFinal();
	inFile.close();
	keyFile.close();
	encrypted.close();
	}
	catch (NoSuchAlgorithmException e){
		System.err.println(e.getMessage());
		}
	catch (IOException e){
		System.err.println(e.getMessage());
		}
	catch (NoSuchPaddingException e){
		System.err.println(e.getMessage());
		}
	catch (InvalidKeyException e){
		System.err.println(e.getMessage());
		}
	catch (IllegalBlockSizeException e){
		System.err.println(e.getMessage());
		}
	catch (BadPaddingException e) {
		System.err.println(e.getMessage());
		}
 }
}
