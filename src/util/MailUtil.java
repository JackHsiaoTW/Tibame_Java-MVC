package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import redis.clients.jedis.Jedis;

public class MailUtil {
	private String subject = "";
	private String txt = null;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		MailUtil aa = new MailUtil();
		HashMap<String, String> confMap = new HashMap<String, String>();
		confMap.put("configPath",
				"C:\\CEA102_Project\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\CEA102G3\\config.properties");
		confMap.put("activatePagePath",
				"C:\\CEA102_Project\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\CEA102G3\\front-end\\member\\ActivateMember.html");
		confMap.put("contextPath", "/CEA102G3");
		confMap.put("subject", "會員註冊確認信");
		confMap.put("toMail", "bubugp@gmail.com");
		confMap.put("account", "666");
		confMap.put("whichMail", "activateMail");
		aa.sendMail(confMap);
//		System.out.println("-------------");
//		System.out.println(new MailUtil().getActTemplate());
	}

	public void sendMail(HashMap<String, String> confMap) throws FileNotFoundException, IOException {
		String configPath = confMap.get("configPath");
		String activatePagePath = confMap.get("activatePagePath");
		String contextPath = confMap.get("contextPath");
//		String imgUrl = confMap.get("imgUrl");
		subject = confMap.get("subject");
		String toMail = confMap.get("toMail");
		String account = confMap.get("account");
		String whichMail = confMap.get("whichMail");
		String forgetPwdUrl = "http://localhost:8081" + confMap.get("forgetPwdUrl");

		txt = getActTemplate(activatePagePath).toString();
		String url = "";
		if ("activateMail".equals(whichMail)) {
			url = "http://localhost:8081" + contextPath + "/member/member.do?action=registerCheck&token="
					+ memberToken(toMail, false);
		} else if ("forgotPasswordMail".equals(whichMail)) {
			url = "http://localhost:8081" + contextPath + "/member/loginHandler.do?action=forgotPasswordCheck&token="
					+ memberToken(toMail, true);
		}

		String imgUrl = "http://localhost:8081" + contextPath + "/resources/img/logo.PNG";
		txt = txt.replaceAll("urlStr", url);
		txt = txt.replaceAll("accountStr", account);
		txt = txt.replaceAll("imgUrl", imgUrl);
		txt = txt.replaceAll("forgetPwdUrlStr", forgetPwdUrl);

		Properties mailInfo = new Properties();
		mailInfo.load(new FileInputStream(configPath));
		String userName = mailInfo.getProperty("mail.userName");
		String password = mailInfo.getProperty("mail.password");
//		System.out.println("userName:" + userName);

		// 設定連線為smtp
		mailInfo.setProperty("mail.transport.protocol", mailInfo.getProperty("mail.transport.protocol"));

		// host主機:smtp.gmail.com
		mailInfo.setProperty("mail.host", mailInfo.getProperty("mail.host"));

		// host port:465
		mailInfo.put("mail.smtp.port", mailInfo.getProperty("mail.smtp.port"));

		// 寄件者帳號需要驗證：是
		mailInfo.put("mail.smtp.auth", mailInfo.getProperty("mail.smtp.auth"));

		// 需要安全資料傳輸層 (SSL)：是
		mailInfo.put("mail.smtp.socketFactory.class", mailInfo.getProperty("mail.smtp.socketFactory.class"));

		// 安全資料傳輸層 (SSL) 通訊埠：465
		mailInfo.put("mail.smtp.socketFactory.port", mailInfo.getProperty("mail.smtp.socketFactory.port"));

		// 顯示連線資訊
		mailInfo.put("mail.debug", mailInfo.getProperty("mail.debug"));

		Auth auth = new Auth(userName, password);
		Session session = Session.getDefaultInstance(mailInfo, auth);
		// ---------------------------------------------------------Message郵件格式
		MimeMessage message = new MimeMessage(session);

		try {
			// class
			InternetAddress sender = new InternetAddress(userName);
			message.setSender(sender);

			// 收件者
			message.setRecipient(RecipientType.TO, new InternetAddress(toMail));

			// 標題
			message.setSubject(subject);

			// 內容/格式
			message.setContent(txt, "text/html;charset = UTF-8");

			// ---------------------------------------------------------Transport傳送Message
			Transport transport = session.getTransport();

			// transport將message送出
			transport.send(message);

			// 關閉Transport
			transport.close();

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public StringBuffer getActTemplate(String activatePagePath) {
		String str;
		StringBuffer sb = new StringBuffer();

		try {
			InputStream is = new FileInputStream(activatePagePath);
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			if (isr != null) {
				BufferedReader br = new BufferedReader(isr);
				while ((str = br.readLine()) != null) {
//					System.out.println(str);
					sb.append(str);
				}
				br.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb;
	}

	public String memberToken(String toMail, boolean isTimer) {
		Jedis jedis = null;
		String token = null;
		try {
			jedis = new Jedis("localhost", 6379);
			jedis.auth("123456");
//			System.out.println(jedis.ping());
			token = genAuthCode();
//			System.out.println("setMemberToken:" + token);
			jedis.set(token, toMail);
			if (isTimer) {
				jedis.expire(token, 3 * 60 * 60);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return token;
	}

	public String genAuthCode() {
		String authCode = "";
		char[] authCodeArray = new char[62];
		char digit = 48;
		char upper = 65;
		char lower = 97;

		// 將數字/大寫/小寫放入authCodeArray陣列中，利用unicode表順序
		for (int i = 0; i < authCodeArray.length; i++) {
			if (digit >= 48 && digit <= 57) {
				authCodeArray[i] = digit++;
			} else if (upper >= 65 && upper <= 90) {
				authCodeArray[i] = upper++;
			} else if (lower >= 97 && lower <= 122) {
				authCodeArray[i] = lower++;
			}
		}

		String[] atLeastOne = { "digit", "upper", "lower" };

		// 前三個元素，至少都保底一次數字/大寫/小寫
		for (int i = atLeastOne.length; i > 0; i--) {
			int r = (int) (Math.random() * i);
			// 第r個元素與最後一個交換，確保不重複
			String temp = atLeastOne[r];
			atLeastOne[r] = atLeastOne[i - 1];
			atLeastOne[i - 1] = temp;

			switch (atLeastOne[i - 1]) {
			case "digit":
				int randomDigit = (int) (Math.random() * 10);
				authCode = authCode + String.valueOf(authCodeArray[randomDigit]);
				break;
			case "upper":
				int randomUpper = (int) (Math.random() * 26) + 10;
				authCode = authCode + String.valueOf(authCodeArray[randomUpper]);
				break;
			case "lower":
				int randomLower = (int) (Math.random() * 26) + 36;
				authCode = authCode + String.valueOf(authCodeArray[randomLower]);
				break;
			}
		}
		// 印出保底完後剩下的元素個數
		for (int i = 0; i < 5; i++) {
			int randomAll = (int) (Math.random() * 62);
			authCode = authCode + String.valueOf(authCodeArray[randomAll]);
		}
		return authCode;
	}

}

class Auth extends Authenticator {
	private String userName;
	private String password;

	public Auth(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		PasswordAuthentication pa = new PasswordAuthentication(userName, password);
		return pa;
	}

}
