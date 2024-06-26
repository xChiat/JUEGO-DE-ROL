package org.InfinityCreations.utils;

import java.util.Random;

public class PasswordRecovery {

    public static int generateVerificationCode() {
        Random random = new Random();
        return 1000 + random.nextInt(9000); // Genera un número aleatorio de 4 cifras
    }

    public static void sendVerificationCode(String email, int code) {
        String subject = "Código de verificación";
        String body = "Su código de verificación es: " + code;
        EmailSender.sendEmail(email, subject, body);
    }
}
