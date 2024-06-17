package it.epicode.phronesis.businesslayer.services.interfaces;

public interface MailService {

    void sendMail(String emailTo, String Subject, String Body);
}
