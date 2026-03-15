package reclutamiento.app.api_proceso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reclutamiento.app.api_proceso.rabbitmq.message.ProcesoComiteMessage;
import reclutamiento.app.api_proceso.rabbitmq.message.ProcesoMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(ProcesoMessage procesoMessage) {
        for (ProcesoComiteMessage comiteMessage : procesoMessage.getListaProcesoComite()) {


            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("notificaciones@pcm.gob.pe");
            message.setTo(comiteMessage.getEmail());
            message.setSubject("Notificación sobre el proceso: " + procesoMessage.getNombre());
            message.setText(
                    "Estimado(a) " + comiteMessage.getNombre() + ",\n\n" +
                            "Se le notifica sobre el proceso: " + procesoMessage.getNombre() + "\n" +
                            "Resumen: " + procesoMessage.getResumen() + "\n" +
                            "Fecha de inicio: " + procesoMessage.getFechaInicio() + "\n" +
                            "Fecha de fin: " + procesoMessage.getFechaFin() + "\n\n" +
                            "Por favor, revise los detalles del proceso.\n\n" +
                            "Saludos,\n" +
                            "Equipo de Reclutamiento"
            );
            // Enviar el correo
            emailSender.send(message);
        }
    }
}