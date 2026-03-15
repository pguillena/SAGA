package reclutamiento.app.api_proceso.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reclutamiento.app.api_proceso.command.dto.message.ProcesoComiteMessage;
import reclutamiento.app.api_proceso.entities.ProcesoComiteEntity;
import reclutamiento.app.api_proceso.entities.ProcesoEntity;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(ProcesoEntity procesoEntity) {
        for (ProcesoComiteEntity comiteMessage : procesoEntity.getListaProcesoComite()) {


            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("notificaciones@pcm.gob.pe");
            message.setTo(comiteMessage.getEmail());
            message.setSubject("Notificación sobre el proceso: " + procesoEntity.getNombre());
            message.setText(
                    "Estimado(a) " + comiteMessage.getNombre() + ",\n\n" +
                            "Se le notifica sobre el proceso: " + procesoEntity.getNombre() + "\n" +
                            "Resumen: " + procesoEntity.getResumen() + "\n" +
                            "Fecha de inicio: " + procesoEntity.getFechaInicio() + "\n" +
                            "Fecha de fin: " + procesoEntity.getFechaFin() + "\n\n" +
                            "Por favor, revise los detalles del proceso.\n\n" +
                            "Saludos,\n" +
                            "Equipo de Reclutamiento"
            );
            // Enviar el correo
            emailSender.send(message);
        }
    }
}