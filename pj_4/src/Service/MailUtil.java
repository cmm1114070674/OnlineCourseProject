package Service;

 
import java.util.Date;
import java.util.Properties;
 
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class MailUtil {
    public static String myEmailAccount = "registerMail1@163.com";
    public static String myEmailPassword = "zaqw1234";
 
    public static String myEmailSMTPHost = "smtp.163.com";
 
    public static String receiveMailAccount;
 
    public static void sendActiveMail(String receiveMailAccount,String mailActiveCode) throws Exception {
        Properties props = new Properties();                   
        props.setProperty("mail.transport.protocol", "smtp");   
        props.setProperty("mail.smtp.host", myEmailSMTPHost);  
        props.setProperty("mail.smtp.auth", "true");            
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);                                
        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount, mailActiveCode);
        Transport transport = session.getTransport();
        transport.connect(myEmailAccount, myEmailPassword);
 
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
 
    public static MimeMessage createMimeMessage(Session session, String sendMail, 
    		String receiveMail,String mailActiveCode) throws Exception {
        MimeMessage message = new MimeMessage(session);
 
        message.setFrom(new InternetAddress(sendMail, "course site", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "user", "UTF-8"));
 
        message.setSubject("activeMail", "UTF-8");
        String activeUrl="http://localhost:8080/pj_4/activeMail?activeCode="+mailActiveCode;
        message.setContent("active <a href='http://127.0.0.1:8080/pj_4/activeMail?activeCode="+mailActiveCode+"' rel='external' target='_blank'>"
        		+activeUrl+"</a>", "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
 
        return message;
    }
    
    public static void main(String[] args) {
    	   String receiveMailAccount1 = "935516651@qq.com";
    	try {
			sendActiveMail(receiveMailAccount1,"asd");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
 
}
