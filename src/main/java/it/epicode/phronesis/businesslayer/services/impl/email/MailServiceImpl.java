package it.epicode.phronesis.businesslayer.services.impl.email;

import it.epicode.phronesis.businesslayer.services.interfaces.email.MailService;
import it.epicode.phronesis.datalayer.entities.User;
import it.epicode.phronesis.presentationlayer.api.exceptions.sendingEmail.EmailSendingException;
import it.epicode.phronesis.presentationlayer.api.exceptions.sendingEmail.UnsupportedEmailEncodingException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(User user, String token) throws EmailSendingException, UnsupportedEmailEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(user.getEmail());
            helper.setFrom("phronesis_support@phronesis.com", "Phronesis");
            helper.setSubject("Welcome to Phronesis! 🌟");
            String emailContent =
                    "<head>" +
                            "<style>"
                            + "body { font-family: 'Tenor Sans', Arial, sans-serif; font-weight: 400; }"
                            + ".email-container { padding: 20px; }"
                            + "</style>"
                            + "</head>"
                            + "<body>"
                            + "<div class='email-container'>"
                            + "<div>"
                            + "<p style='font-size: 18px;'>Hello <span style='color: blue;'><strong>"
                            + user.getFirstName() + " " + user.getLastName()
                            + "</strong></span>, and welcome to 🌟 <strong><span style='font-family: \"Forum\", Arial, sans-serif;'>Phronesis</span></strong>! 🌟</p>"
                            + "<p style='font-size: 14px;'>We are thrilled to have you join our community of individuals dedicated to sharing wisdom and practical advice.</p>"
                            + "<p style='font-size: 14px;'>Your registration has been successful 🎉, and you are now part of a supportive network where you can share your experiences and grow together. ✨</p>"
                            + "<p style='font-size: 14px;'>Here at Phronesis, we believe in the power of shared wisdom to foster personal growth. On our platform, you can:</p>"
                            + "<ul>"
                            + "<li style='font-size: 14px;'>📝 Share your personal experiences to inspire and support others.</li>"
                            + "<li style='font-size: 14px;'>❤️ <strong>Engage with the Community</strong>: Like and favorite posts that resonate with you for easy access later.</li>"
                            + "<li style='font-size: 14px;'>🔒 <strong>Manage Your Profile</strong>: Update your information, upload a profile picture, and manage your posts and comments.</li>"
                            + "</ul>"
                            + "<p style='font-size: 14px;'>To get started, verify your profile through this <a href='" + token + "'>link</a>" + " then log in to your account and begin exploring the diverse experiences shared by our community members. Every story you read and share contributes to a more empathetic and understanding world. 🌍</p>"
                            + "<p style='font-size: 14px;'>❓ If you have any questions or need assistance, please don't hesitate to reach out to our support team via email at <a href='mailto:phronesis_support@phronesis.com'>phronesis_support@phronesis.com</a>.</p>"
                            + "<p style='font-size: 14px;'> If your link is expired request <a href='" + "http://localhost:8080/api/user/request-new-token?email=" + user.getEmail()+ "'>a new verification link</a></p>"
                            + "<p style='font-size: 14px;'>Happy Sharing! 💖</p>" + "<p style='font-size: 14px;'>🌟 The Phronesis Team 🌟</p>"
                            + "</div>"
                            + "</div>"
                            + "</body>"
                            + "</html>";

            helper.setText(emailContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new EmailSendingException(user.getEmail());
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedEmailEncodingException(user.getEmail());
        }
    }
    public void notifyEmailChange(User user, String oldEmail, String newEmail) throws EmailSendingException, UnsupportedEmailEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom("phronesis_support@phronesis.com", "Phronesis");
            helper.setTo(oldEmail);
            helper.setSubject("Cambio di indirizzo email associato al tuo account 🌟");

            String emailContent =
                    "<html>" +
                            "<head>" +
                            "<style>" +
                            "body { font-family: 'Tenor Sans', Arial, sans-serif; font-weight: 400; }" +
                            ".email-container { padding: 20px; }" +
                            "</style>" +
                            "</head>" +
                            "<body>" +
                            "<div class='email-container'>" +
                            "<div>" +
                            "<p style='font-size: 18px;'>Ciao <span style='color: blue;'><strong>" +
                            user.getFirstName() + " " + user.getLastName() +
                            "</strong></span>,</p>" +
                            "<p style='font-size: 14px;'>Questo è un avviso che l'indirizzo email associato al tuo account Phronesis è stato cambiato.</p>" +
                            "<p style='font-size: 14px;'>Il nuovo indirizzo email associato al tuo account è: <strong>" + newEmail + "</strong>.</p>" +
                            "<p style='font-size: 14px;'>Se non hai richiesto questo cambiamento, ti preghiamo di contattarci immediatamente.</p>" +
                            "<p style='font-size: 14px;'>Per ulteriori informazioni, puoi contattarci a <a href='mailto:phronesis_support@phronesis.com'>phronesis_support@phronesis.com</a>.</p>" +
                            "<p style='font-size: 14px;'>Grazie,</p>" +
                            "<p style='font-size: 14px;'>🌟 Il Team di Phronesis 🌟</p>" +
                            "</div>" +
                            "</div>" +
                            "</body>" +
                            "</html>";

            helper.setText(emailContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new EmailSendingException(oldEmail);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedEmailEncodingException(oldEmail);
        }
    }

    @Override
    public void sendMailNewVerificationLink(User user, String token) throws EmailSendingException, UnsupportedEmailEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(user.getEmail());
            helper.setFrom("phronesis_support@phronesis.com", "Phronesis");
            helper.setSubject("Nuovo link di validazione");

            String emailContent = "<html>"
                    + "<head>"
                    + "<style>"
                    + "body { font-family: 'Tenor Sans', Arial, sans-serif; font-weight: 400; }"
                    + ".email-container { padding: 20px; }"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div class='email-container'>"
                    + "<div>"
                    + "<p style='font-size: 18px;'>Ciao <span style='color: blue;'><strong>"
                    + user.getFirstName() + " " + user.getLastName()
                    + "</strong></span>,</p>"
                    + "<p style='font-size: 14px;'>Come richiesto, ti abbiamo inviato un nuovo link di autenticazione. Clicca sul link seguente per verificare il tuo account:</p>"
                    + "<p style='font-size: 14px;'><a href='" + token + "'>Verifica il tuo account</a></p>"
                    + "<p style='font-size: 14px;'>Se non hai richiesto questo link, per favore ignora questa email.</p>"
                    + "<p style='font-size: 14px;'>Se hai domande o necessiti assistenza, non esitare a contattarci a <a href='mailto:phronesis_support@phronesis.com'>phronesis_support@phronesis.com</a>.</p>"
                    + "<p style='font-size: 14px;'>Grazie,</p>"
                    + "<p style='font-size: 14px;'>🌟 Il Team di Phronesis 🌟</p>"
                    + "</div>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            helper.setText(emailContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new EmailSendingException(user.getEmail());
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedEmailEncodingException(user.getEmail());
        }
    }

    @Override
    public void sendBannedEmail(User user, String reason) throws EmailSendingException, UnsupportedEmailEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(user.getEmail());
            helper.setFrom("phronesis_support@phronesis.com", "Phronesis");
            helper.setSubject("Account Banned Notification");

            String emailContent = "<html>"
                    + "<head>"
                    + "<style>"
                    + "body { font-family: 'Tenor Sans', Arial, sans-serif; font-weight: 400; }"
                    + ".email-container { padding: 20px; }"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div class='email-container'>"
                    + "<div>"
                    + "<p style='font-size: 18px;'>Hello <span style='color: blue;'><strong>"
                    + user.getFirstName() + " " + user.getLastName()
                    + "</strong></span>,</p>"
                    + "<p style='font-size: 14px;'>We regret to inform you that your account on <strong>Phronesis</strong> has been banned due to the following reason:</p>"
                    + "<p style='font-size: 14px; color: red;'><strong>" + reason + "</strong></p>"
                    + "<p style='font-size: 14px;'>While banned, you will not be able to access your account or participate in any community activities. If you believe this ban is a mistake or would like to appeal, please contact our support team at <a href='mailto:phronesis_support@phronesis.com'>phronesis_support@phronesis.com</a>.</p>"
                    + "<p style='font-size: 14px;'>Thank you for your understanding,</p>"
                    + "<p style='font-size: 14px;'>🌟 The Phronesis Team 🌟</p>"
                    + "</div>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            helper.setText(emailContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new EmailSendingException(user.getEmail());
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedEmailEncodingException(user.getEmail());
        }
    }


    @Override
    public void sendUnbannedEmail(User user) throws EmailSendingException, UnsupportedEmailEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(user.getEmail());
            helper.setFrom("phronesis_support@phronesis.com", "Phronesis");
            helper.setSubject("Account Unbanned Notification");

            String emailContent = "<html>"
                    + "<head>"
                    + "<style>"
                    + "body { font-family: 'Tenor Sans', Arial, sans-serif; font-weight: 400; }"
                    + ".email-container { padding: 20px; }"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div class='email-container'>"
                    + "<div>"
                    + "<p style='font-size: 18px;'>Hello <span style='color: blue;'><strong>"
                    + user.getFirstName() + " " + user.getLastName()
                    + "</strong></span>,</p>"
                    + "<p style='font-size: 14px;'>We are pleased to inform you that your account on <strong>Phronesis</strong> has been unbanned and you can now resume participating in our community.</p>"
                    + "<p style='font-size: 14px;'>We value your contribution and hope you will continue to abide by our community guidelines. If you have any questions or need further assistance, please contact our support team at <a href='mailto:phronesis_support@phronesis.com'>phronesis_support@phronesis.com</a>.</p>"
                    + "<p style='font-size: 14px;'>Welcome back!</p>"
                    + "<p style='font-size: 14px;'>🌟 The Phronesis Team 🌟</p>"
                    + "</div>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            helper.setText(emailContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new EmailSendingException(user.getEmail());
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedEmailEncodingException(user.getEmail());
        }
    }


}
