package com.roc.utils;

import java.io.ByteArrayInputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
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

import javax.crypto.Cipher;

/**
 * @author ROC
 * @version 创建时间：May 18, 2011 2:44:51 PM 
 * 			类说明 RSA加密解实实现。 支持加密字节长度超过117。
 *          支持加密中文与英文混合。
 */
public class RSAUtils2 {

	/**
	 * 算法名称
	 */
	private final static String RSA = "RSA";

	/**
	 * 加密后的字节分隔长度
	 */
	private final static int encryptSepLength = 256;

	/**
	 * 明文字节分隔长度
	 */
	private final static int plainSepLneght = 100;

	private static final int KEY_SIZE = 1024;

	/**
	 * 生成公钥私钥对
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static KeyPair genKeyPair() {
		KeyPairGenerator kpg = null;
		try {
			kpg = KeyPairGenerator.getInstance(RSA);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		SecureRandom secureRandom = new SecureRandom();
		kpg.initialize(KEY_SIZE, secureRandom);
		return kpg.generateKeyPair();
	}

	/**
	 * 把字节数组的publicKey变成PublicKey对象
	 * 
	 * @param publicKey
	 *            字节数组publicKey
	 * @return PublicKey
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	public static PublicKey toPublicKey(byte[] publicKey)
			throws InvalidKeySpecException, NoSuchAlgorithmException {
		X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(publicKey);
		// PKCS8EncodedKeySpec encodedKeySpec = new
		// PKCS8EncodedKeySpec(publicKey);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PublicKey pk = keyFactory.generatePublic(encodedKeySpec);
		return pk;
	}

	/**
	 * 把字节数组的privateKey变成PrivateKey对象
	 * 
	 * @param privateKey
	 *            字节数组privateKey
	 * @return PrivateKey
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PrivateKey toPrivateKey(byte[] privateKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		// X509EncodedKeySpec encodedKeySpec = new
		// X509EncodedKeySpec(privateKey);
		PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(privateKey);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PrivateKey pk = keyFactory.generatePrivate(encodedKeySpec);
		return pk;
	}

	/**
	 * 用公钥加密
	 * @param text 需要加密的内容
	 * @param publicKey 公钥
	 * @return byte[]
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] text, PublicKey publicKey)
			throws Exception {
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(text);
	}
	
	/**
	 * 用私钥加密
	 * @param text 需要加密的内容
	 * @param privateKey 私钥
	 * @return byte[]
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] text, PrivateKey privateKey)
			throws Exception {
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(text);
	}

	/**
	 * 用公钥加密
	 * @param text 需要加密的内容
	 * @param publicKey 公钥
	 * @return String
	 */
	public final static String encrypt(String text, PublicKey publicKey) {
		StringBuffer sbf = new StringBuffer(200);
		try {
			text = URLEncoder.encode(text, "UTF-8");// 用这个的原因是为了支持汉字、汉字和英文混排,解密方法中同理
			byte[] plainByte = text.getBytes();
			//分段加密
			ByteArrayInputStream in = new ByteArrayInputStream(plainByte);
			byte[] readByte = new byte[plainSepLneght];
			int n = 0;
			while ((n = in.read(readByte)) > 0) {
				if (n >= plainSepLneght) {
					sbf.append(byte2hex(encrypt(readByte, publicKey)));
				} else {
					byte[] tt = new byte[n];
					for (int i = 0; i < n; i++) {
						tt[i] = readByte[i];
					}
					sbf.append(byte2hex(encrypt(tt, publicKey)));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sbf.toString();
	}
	
	/**
	 * 用私钥加密
	 * @param text 需要加密的内容
	 * @param privateKey 私钥
	 * @return String
	 */
	public final static String encrypt(String text, PrivateKey privateKey) {
		StringBuffer sbf = new StringBuffer(200);
		try {
			text = URLEncoder.encode(text, "UTF-8");// 用这个的原因是为了支持汉字、汉字和英文混排,解密方法中同理
			byte[] plainByte = text.getBytes();
			//分段加密
			ByteArrayInputStream in = new ByteArrayInputStream(plainByte);
			byte[] readByte = new byte[plainSepLneght];
			int n = 0;
			while ((n = in.read(readByte)) > 0) {
				if (n >= plainSepLneght) {
					sbf.append(byte2hex(encrypt(readByte, privateKey)));
				} else {
					byte[] tt = new byte[n];
					for (int i = 0; i < n; i++) {
						tt[i] = readByte[i];
					}
					sbf.append(byte2hex(encrypt(tt, privateKey)));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sbf.toString();
	}

	/**
	 * 用私钥解密
	 * @param data 已用公钥加密过的数据
	 * @param privateKey 私钥
	 * @return String
	 */
	public final static String decrypt(String data, PrivateKey privateKey) {
		String decryptStr = "";
		StringBuffer sb = new StringBuffer(100);
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(
					data.getBytes());
			// 此处之所以是 256，而不是128的原因是因为有一个16进行的转换，所以由128变为了256
			byte[] readByte = new byte[256];
			int n = 0;
			while ((n = in.read(readByte)) > 0) {
				if (n >= encryptSepLength) {
					sb.append(new String(decrypt(hex2byte(readByte), privateKey)));
				} else {

				}
			}
			decryptStr = URLDecoder.decode(sb.toString(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptStr;
	}
	
	/**
	 * 用公钥解密
	 * @param data 已用私钥加密过的内容
	 * @param publicKey 公钥
	 * @return String
	 */
	public final static String decrypt(String data, PublicKey publicKey) {
		String decryptStr = "";
		StringBuffer sb = new StringBuffer(100);
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(
					data.getBytes());
			// 此处之所以是 256，而不是128的原因是因为有一个16进行的转换，所以由128变为了256
			byte[] readByte = new byte[256];
			int n = 0;
			while ((n = in.read(readByte)) > 0) {
				if (n >= encryptSepLength) {
					sb.append(new String(decrypt(hex2byte(readByte), publicKey)));
				} else {

				}
			}
			decryptStr = URLDecoder.decode(sb.toString(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptStr;
	}

	/**
	 * 用私钥解密
	 * @param encryptData 已用公钥加密过的内容
	 * @param privateKey 私钥
	 * @return byte[]
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] encryptData, PrivateKey privateKey)
			throws Exception {
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(encryptData);
	}
	
	/**
	 * 
	 * @param encryptData
	 * @param publicKey
	 * @return byte[]
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] encryptData, PublicKey publicKey)
			throws Exception {
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(encryptData);
	}

	/**
	 * 字节内容转成hash字符串
	 * @param b 字节内容
	 * @return String
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			if (stmp.length() == 1)
				hs += ("0" + stmp);
			else
				hs += stmp;
		}
		return hs.toUpperCase();
	}

	/**
	 * HASH字符数组转成字节数组
	 * @param b HASH字符数组
	 * @return 字节数组
	 */
	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");

		byte[] b2 = new byte[b.length / 2];

		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	public static void main(String[] args) {
		KeyPair keyPair = genKeyPair();

		String content = "阿里城花样百出地花样百出209323094－120934－021934－2lkjdflgkjsdlfjsldkjfslkdjflskjdflkjsdlfjkslkdjflskdjf jdlcjfoewurjowijflkjglkjsdlfkjglskdjflskdjflksjdfjwioapefciqawoejfpwoeirgujtoeihrjgo/bijs/dgbkijdenfb阿里城花样百出地花样百出209323094－120934－021934－2lkjdflgkjsdlfjsldkjfslkdjflskjdflkjsdlfjkslkdjflskdjf jdlcjfoewurjowijflkjglkjsdlfkjglskdjflskdjflksjdfjwioapefciqawoejfpwoeirgujtoeihrjgo/bijs/dgbkijdenfb阿里城花样百出地花样百出209323094－120934－021934－2lkjdflgkjsdlfjsldkjfslkdjflskjdflkjsdlfjkslkdjflskdjf jdlcjfoewurjowijflkjglkjsdlfkjglskdjflskdjflksjdfjwioapefciqawoejfpwoeirgujtoeihrjgo/bijs/dgbkijdenfb阿里城花样百出地花样百出209323094－120934－021934－2lkjdflgkjsdlfjsldkjfslkdjflskjdflkjsdlfjkslkdjflskdjf jdlcjfoewurjowijflkjglkjsdlfkjglskdjflskdjflksjdfjwioapefciqawoejfpwoeirgujtoeihrjgo/bijs/dgbkijdenfb阿里城花样百出地花样百出209323094－120934－021934－2lkjdflgkjsdlfjsldkjfslkdjflskjdflkjsdlfjkslkdjflskdjf jdlcjfoewurjowijflkjglkjsdlfkjglskdjflskdjflksjdfjwioapefciqawoejfpwoeirgujtoeihrjgo/bijs/dgbkijdenfb阿里城花样百出地花样百出209323094－120934－021934－2lkjdflgkjsdlfjsldkjfslkdjflskjdflkjsdlfjkslkdjflskdjf jdlcjfoewurjowijflkjglkjsdlfkjglskdjflskdjflksjdfjwioapefciqawoejfpwoeirgujtoeihrjgo/bijs/dgbkijdenfb阿里城花样百出地花样百出209323094－120934－021934－2lkjdflgkjsdlfjsldkjfslkdjflskjdflkjsdlfjkslkdjflskdjf jdlcjfoewurjowijflkjglkjsdlfkjglskdjflskdjflksjdfjwioapefciqawoejfpwoeirgujtoeihrjgo/bijs/dgbkijdenfb阿里城花样百出地花样百出209323094－120934－021934－2lkjdflgkjsdlfjsldkjfslkdjflskjdflkjsdlfjkslkdjflskdjf jdlcjfoewurjowijflkjglkjsdlfkjglskdjflskdjflksjdfjwioapefciqawoejfpwoeirgujtoeihrjgo/bijs/dgbkijdenfb阿里城花样百出地花样百出209323094－120934－021934－2lkjdflgkjsdlfjsldkjfslkdjflskjdflkjsdlfjkslkdjflskdjf jdlcjfoewurjowijflkjglkjsdlfkjglskdjflskdjflksjdfjwioapefciqawoejfpwoeirgujtoeihrjgo/bijs/dgbkijdenfb阿里城花样百出地花样百出209323094－120934－021934－2lkjdflgkjsdlfjsldkjfslkdjflskjdflkjsdlfjkslkdjflskdjf jdlcjfoewurjowijflkjglkjsdlfkjglskdjflskdjflksjdfjwioapefciqawoejfpwoeirgujtoeihrjgo/bijs/dgbkijdenfb阿里城花样百出地花样百出209323094－120934－021934－2lkjdflgkjsdlfjsldkjfslkdjflskjdflkjsdlfjkslkdjflskdjf jdlcjfoewurjowijflkjglkjsdlfkjglskdjflskdjflksjdfjwioapefciqawoejfpwoeirgujtoeihrjgo/bijs/dgbkijdenfb阿里城花样百出地花样百出209323094－120934－021934－2lkjdflgkjsdlfjsldkjfslkdjflskjdflkjsdlfjkslkdjflskdjf jdlcjfoewurjowijflkjglkjsdlfkjglskdjflskdjflksjdfjwioapefciqawoejfpwoeirgujtoeihrjgo/bijs/dgbkijdenfb阿里城花样百出地花样百出209323094－120934－021934－2lkjdflgkjsdlfjsldkjfslkdjflskjdflkjsdlfjkslkdjflskdjf jdlcjfoewurjowijflkjglkjsdlfkjglskdjflskdjflksjdfjwioapefciqawoejfpwoeirgujtoeihrjgo/bijs/dgbkijdenfb阿里城花样百出地花样百出209323094－120934－021934－2lkjdflgkjsdlfjsldkjfslkdjflskjdflkjsdlfjkslkdjflskdjf jdlcjfoewurjowijflkjglkjsdlfkjglskdjflskdjflksjdfjwioapefciqawoejfpwoeirgujtoeihrjgo/bijs/dgbkijdenfb阿里城花样百出地花样百出209323094－120934－021934－2lkjdflgkjsdlfjsldkjfslkdjflskjdflkjsdlfjkslkdjflskdjf jdlcjfoewurjowijflkjglkjsdlfkjglskdjflskdjflksjdfjwioapefciqawoejfpwoeirgujtoeihrjgo/bijs/dgbkijdenfb阿里城花样百出地花样百出209323094－120934－021934－2lkjdflgkjsdlfjsldkjfslkdjflskjdflkjsdlfjkslkdjflskdjf jdlcjfoewurjowijflkjglkjsdlfkjglskdjflskdjflksjdfjwioapefciqawoejfpwoeirgujtoeihrjgo/bijs/dgbkijdenfb阿里城花样百出地花样百出209323094－120934－021934－2lkjdflgkjsdlfjsldkjfslkdjflskjdflkjsdlfjkslkdjflskdjf jdlcjfoewurjowijflkjglkjsdlfkjglskdjflskdjflksjdfjwioapefciqawoejfpwoeirgujtoeihrjgo/bijs/dgbkijdenfb阿里城花样百出地花样百出209323094－120934－021934－2lkjdflgkjsdlfjsldkjfslkdjflskjdflkjsdlfjkslkdjflskdjf jdlcjfoewurjowijflkjglkjsdlfkjglskdjflskdjflksjdfjwioapefciqawoejfpwoeirgujtoeihrjgo/bijs/dgbkijdenfb阿里城花样百出地花样百出209323094－120934－021934－2lkjdflgkjsdlfjsldkjfslkdjflskjdflkjsdlfjkslkdjflskdjf jdlcjfoewurjowijflkjglkjsdlfkjglskdjflskdjflksjdfjwioapefciqawoejfpwoeirgujtoeihrjgo/bijs/dgbkijdenfb阿里城花样百出地花样百出209323094－120934－021934－2lkjdflgkjsdlfjsldkjfslkdjflskjdflkjsdlfjkslkdjflskdjf jdlcjfoewurjowijflkjglkjsdlfkjglskdjflskdjflksjdfjwioapefciqawoejfpwoeirgujtoeihrjgo/bijs/dgbkijdenfb阿里城花样百出地花样百出209323094－120934－021934－2lkjdflgkjsdlfjsldkjfslkdjflskjdflkjsdlfjkslkdjflskdjf jdlcjfoewurjowijflkjglkjsdlfkjglskdjflskdjflksjdfjwioapefciqawoejfpwoeirgujtoeihrjgo/bijs/dgbkijdenfb阿里城花样百出地花样百出209323094－120934－021934－2lkjdflgkjsdlfjsldkjfslkdjflskjdflkjsdlfjkslkdjflskdjf jdlcjfoewurjowijflkjglkjsdlfkjglskdjflskdjflksjdfjwioapefciqawoejfpwoeirgujtoeihrjgo/bijs/dgbkijdenfb";

		String encryptContent = encrypt(content, keyPair.getPublic());
		String decryptContent = decrypt(encryptContent, keyPair.getPrivate());

		System.out.println(decryptContent);
	}

}
