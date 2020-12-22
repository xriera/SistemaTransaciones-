package ec.edu.ups.sistematransaciones.vista;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailClient implements Runnable{

	static void sendMail(String destino,String Asunto,String CuerpoMail) throws Exception {
		String senderEmail = "sistematransaccional@gmail.com";// change with your sender email
		String senderPassword = "Sistema@transaccional";// change with your sender password

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");// Outgoing server requires authentication
		props.put("mail.smtp.starttls.enable", "true");// TLS must be activated
		props.put("mail.smtp.host", "smtp.gmail.com"); // Outgoing server (SMTP) - change it to your SMTP server
		props.put("mail.smtp.port", "587");// Outgoing port
		//props.put("m", arg1)

//				mail.smtp.host=smtp.gmail.com, 
//				mail.smtp.port=25,
//				mail.smtp.auth=true 
//				mail.smtp.starttls.enable=true
				
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		});
		Message message = prepareEmailMessage(session, senderEmail, destino,Asunto,CuerpoMail);
	
		Transport.send(message);
		System.out.println("se envio el mensaje");
	}

	private static Message prepareEmailMessage(Session session, String senderEmail, String destino,String Asunto,String CuerpoMail) {
		try {
			Message message = new MimeMessage(session);
			// message.setContent(html, "text/html; charset=utf-8");
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));
			message.setSubject(Asunto);
			message.setText(CuerpoMail);
			return message;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	String destino;
	String Asunto;
	String CuerpoMail;
	
	
	public EmailClient(String destino,String Asunto,String CuerpoMail) {
		this.destino=destino;
		this.Asunto=Asunto;
		this.CuerpoMail=CuerpoMail;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			EmailClient.sendMail(destino,Asunto,CuerpoMail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) throws MessagingException {
//		try {
//			String destino="xavi-javi11@hotmail.com";
//			String Asunto=" Inicio de Sesion Exitoso";
//			String CuerpoMail="Hola mundo ";
//			EmailClient.sendMail(destino,Asunto,CuerpoMail);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}