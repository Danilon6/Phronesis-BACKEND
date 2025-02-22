package it.epicode.phronesis.businesslayer.services.interfaces.email;

import it.epicode.phronesis.datalayer.entities.User;
import it.epicode.phronesis.presentationlayer.api.exceptions.sendingEmail.EmailSendingException;
import it.epicode.phronesis.presentationlayer.api.exceptions.sendingEmail.UnsupportedEmailEncodingException;

public interface MailService {

    void sendMail(User user, String token) throws EmailSendingException, UnsupportedEmailEncodingException;
    void sendMailNewVerificationLink(User user, String token) throws EmailSendingException, UnsupportedEmailEncodingException;
    void notifyEmailChange(User user, String oldEmail, String newEmail) throws EmailSendingException, UnsupportedEmailEncodingException;
    void sendUnbannedEmail(User user) throws EmailSendingException, UnsupportedEmailEncodingException;
    void sendBannedEmail(User user, String reason) throws EmailSendingException, UnsupportedEmailEncodingException;
}
