import java.io.*;
import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class KeyPairGen {
 public static void main(String args[]){
    int keySize = Integer.parseInt(args[1]);
  	try{
    KeyPairGenerator kpg = KeyPairGenerator.getInstance(args[0]);
	kpg.initialize(keySize);
    KeyPair keys = kpg.genKeyPair();
    PrivateKey priKey = keys.getPrivate();
    PublicKey pubKey = keys.getPublic();
	FileOutputStream privateFile = new FileOutputStream(args[2]);
	ObjectOutputStream PrivateKey  = new ObjectOutputStream(privateFile);
	FileOutputStream publicFile = new FileOutputStream(args[3]);
	ObjectOutputStream PublicKey = new ObjectOutputStream(publicFile);

	PrivateKey.writeObject(priKey);
	PrivateKey.close();
    PublicKey.writeObject(pubKey);
	PublicKey.close();


	}
	catch (NoSuchAlgorithmException e){
		System.err.println(e.getMessage());
		}
	catch (IOException e){
		System.err.println(e.getMessage());
		}
 }
}
