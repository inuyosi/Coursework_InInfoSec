import java.io.*;
import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class SKdecrypt implements Serializable{
 public static void main(String args[]){
  	try{
	FileInputStream inFile = new FileInputStream(args[0]);
	FileInputStream keyFile = new FileInputStream(args[1]);
	ObjectInputStream skeyFile = new ObjectInputStream(keyFile);
	SecretKey skey = (SecretKey) skeyFile.readObject();
	String algorithm = skey.getAlgorithm();
	byte[] buf = new byte[2048];
	byte[] gbuf = new byte[2048];
	byte[] vbuf;
	int len;
	FileInputStream ivFile = new FileInputStream(args[4]);
	len = ivFile.read(buf);
	IvParameterSpec	ivdata = new IvParameterSpec(buf,0,len);
	ivFile.close();
	Cipher cipher = Cipher.getInstance(algorithm+"/"+args[3]+"/PKCS5Padding");
	cipher.init(Cipher.DECRYPT_MODE,skey,ivdata);
	FileOutputStream decrypted = new FileOutputStream(args[2]);

	while(( len = inFile.read(buf)) != -1){
		gbuf = cipher.update(buf,0,len);
		decrypted.write(gbuf);
	}
	gbuf = cipher.doFinal();
	if(gbuf != null){
	decrypted.write(gbuf);
	}
	inFile.close();
	keyFile.close();
	decrypted.close();
	}
	catch (Exception e){
		System.err.println(e.getMessage());
		}
 }
}
