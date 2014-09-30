package com.roc.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * RSA非对称加密解密
 * 如果是公钥加密,需要用私钥解密
 * 如果是私钥加密,需要用公钥解密
 * @author roc
 *
 */
public class RSAUtils {
	
	//public static final String CHARSET = "utf-8";
	/**
     * 加密算法RSA
     */
	private static final String ALGORITHM = "RSA";
	private static final int KEY_SIZE = 1024;
	/**
     * RSA最大加密明文大小
     */
	private static final int MAX_ENCRYPT_BLOCK = 117;
	/**
     * RSA最大解密密文大小
     */
	private static final int MAX_DECRYPT_BLOCK = 128;
	
	/**
	 * 生成公钥私钥对
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static KeyPair genKeyPair() throws NoSuchAlgorithmException{
		KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
		SecureRandom secureRandom = new SecureRandom();
		kpg.initialize(KEY_SIZE, secureRandom);
		return kpg.generateKeyPair();
	}
	
	/**
	 * 公钥加密
	 * @param content 需要加密的内容
	 * @param publicKey 加密所用公钥
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] encrypt(String content, PublicKey publicKey){
		if(null == content){
			return null;
		}
		byte[] result;
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			//return cipher.doFinal(content.getBytes(CHARSET));
			
			byte[] data = content.getBytes();
			int length = data.length;
			int offset = 0;
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] cache = null;
			int i = 0;
			while(length - offset > 0){
				if(length - offset > MAX_ENCRYPT_BLOCK){
					cache = cipher.doFinal(data, offset, MAX_ENCRYPT_BLOCK);
				}else{
					cache = cipher.doFinal(data, offset, length - offset);
				}
				outputStream.write(cache, 0, cache.length);
				++i;
				offset = i * MAX_ENCRYPT_BLOCK;
			}
			result = outputStream.toByteArray();
			outputStream.flush();
			outputStream.close();
			return result;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 私钥加密
	 * @param content 需要加密的内容
	 * @param publicKey 加密所用私钥
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] encrypt(String content, PrivateKey privateKey){
		if(null == content){
			return null;
		}
		try {
			byte[] data = content.getBytes();
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			
			int inputLen = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段加密
			while (inputLen - offSet > 0) {
			    if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
			        cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			    } else {
			        cache = cipher.doFinal(data, offSet, inputLen - offSet);
			    }
			    out.write(cache, 0, cache.length);
			    i++;
			    offSet = i * MAX_ENCRYPT_BLOCK;
			}
			byte[] encryptedData = out.toByteArray();
			out.flush();
			out.close();
			return encryptedData;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 私钥解密
	 * @param content 需要解密的内容
	 * @param privateKey 解密所用到的私钥
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException 
	 */
	public static byte[] decrypt(byte[] content, PrivateKey privateKey){
		if(null == content){
			return null;
		}
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			
			int inputLen = content.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache = null;
			int i = 0;
			// 对数据分段加密
			while (inputLen - offSet > 0) {
			    if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
			        cache = cipher.doFinal(content, offSet, MAX_DECRYPT_BLOCK);
			    } else {
			        cache = cipher.doFinal(content, offSet, inputLen - offSet);
			    }
			    out.write(cache, 0, cache.length);
			    ++i;
			    offSet = i * MAX_DECRYPT_BLOCK;
			}
			byte[] encryptedData = out.toByteArray();
			out.flush();
			out.close();
			return encryptedData;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
}
	/**
	 * 公钥解密
	 * @param content 需要解密的内容
	 * @param privateKey 解密所用到的公钥
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static byte[] decrypt(byte[] content, PublicKey publicKey){
		if(null == content){
			return null;
		}
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			
			 int inputLen = content.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (inputLen - offSet > 0) {
			    if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
			        cache = cipher.doFinal(content, offSet, MAX_DECRYPT_BLOCK);
			    } else {
			        cache = cipher.doFinal(content, offSet, inputLen - offSet);
			    }
			    out.write(cache, 0, cache.length);
			    i++;
			    offSet = i * MAX_DECRYPT_BLOCK;
			}
			byte[] decryptedData = out.toByteArray();
			out.flush();
			out.close();
			return decryptedData;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 把字节数组的publicKey变成PublicKey对象
	 * @param publicKey 字节数组publicKey
	 * @return PublicKey
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	public static PublicKey toPublicKey(byte[] publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException{
		X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(publicKey);
		//PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(publicKey);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		PublicKey pk = keyFactory.generatePublic(encodedKeySpec);
		return pk;
	}
	
	/**
	 把字节数组的privateKey变成PrivateKey对象
	 * @param privateKey 字节数组privateKey
	 * @return PrivateKey
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PrivateKey toPrivateKey(byte[] privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException{
		//X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(privateKey);
		PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(privateKey);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		PrivateKey pk = keyFactory.generatePrivate(encodedKeySpec);
		return pk;
	}
	
	public static void main(String[]args) throws NoSuchAlgorithmException{
			KeyPair keyPair = genKeyPair();
			//1.
			test1(keyPair);
			//2.
			test2(keyPair);
	}
	
	private static void test1(KeyPair keyPair){
			byte[] encryptText = encrypt("hello world", keyPair.getPublic());
			byte[] decryptText = decrypt(encryptText, keyPair.getPrivate());
			try {
				System.out.println(new String(decryptText,"utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private static void test2(KeyPair keyPair){
		String base64PublicKey = Base64Utils.encode(keyPair.getPublic().getEncoded());
		String base64PrivateKey = Base64Utils.encode(keyPair.getPrivate().getEncoded());
		System.out.println("PublicKey: " + base64PublicKey);
		System.out.println("PrivateKey: " + base64PrivateKey);
		try {
			String data = "{\"key\":\"中华人民共121fdd各国\",\"value\":\"dadfd\"}";
			String content = "hello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello worldhello world";
			byte[] encryptText = encrypt(data+content, toPublicKey(Base64Utils.decode(base64PublicKey)));
//			String urlEncodeText = URLEncodeDecodeUtils.encode(new String(encryptText));
//			String urldecodeText = URLEncodeDecodeUtils.decode(urlEncodeText);
			
			byte[] decryptText = decrypt(encryptText, toPrivateKey(Base64Utils.decode(base64PrivateKey)));
			System.out.println(new String(decryptText));
		}  catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
