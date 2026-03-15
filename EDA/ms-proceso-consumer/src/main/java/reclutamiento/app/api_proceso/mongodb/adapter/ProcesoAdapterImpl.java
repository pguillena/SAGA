package reclutamiento.app.api_proceso.mongodb.adapter;

import org.springframework.stereotype.Component;
import reclutamiento.app.api_proceso.mongodb.command.OrganoCreateCommand;
import reclutamiento.app.api_proceso.mongodb.command.ProcesoComiteCreateCommand;
import reclutamiento.app.api_proceso.mongodb.command.ProcesoCreateCommand;
import reclutamiento.app.api_proceso.mongodb.document.OrganoDocument;
import reclutamiento.app.api_proceso.mongodb.document.ProcesoComiteDocument;
import reclutamiento.app.api_proceso.mongodb.document.ProcesoDocument;
import reclutamiento.app.api_proceso.rabbitmq.message.ProcesoMessage;

import java.util.stream.Collectors;

@Component
public class ProcesoAdapterImpl implements  ProcesoAdapter {
    @Override
    public ProcesoCreateCommand toCommand(ProcesoMessage procesoMessage) {
        return ProcesoCreateCommand
                .builder()
                .id(procesoMessage.getId())
                .nombre(procesoMessage.getNombre())
                .resumen(procesoMessage.getResumen())
                .titulo(procesoMessage.getTitulo())
                .cantidad(procesoMessage.getCantidad())
                .fechaInicio(procesoMessage.getFechaInicio())
                .fechaFin(procesoMessage.getFechaFin())
                .estado(procesoMessage.getEstado())
                .organo(OrganoCreateCommand
                        .builder()
                        .id(procesoMessage.getOrgano().getId())
                        .codigo(procesoMessage.getOrgano().getCodigo())
                        .nombre(procesoMessage.getOrgano().getNombre())
                        .sigla(procesoMessage.getOrgano().getSigla())
                        .build())
                .listaProcesoComite(
                        procesoMessage.getListaProcesoComite().stream()
                                .map(comite -> ProcesoComiteCreateCommand
                                        .builder()
                                        .id(comite.getId())
                                        .organo(OrganoCreateCommand
                                                .builder()
                                                .id(comite.getOrgano().getId())
                                                .codigo(comite.getOrgano().getCodigo())
                                                .nombre(comite.getOrgano().getNombre())
                                                .sigla(comite.getOrgano().getSigla())
                                                .build())
                                        .tipo(comite.getTipo())
                                        .nombre(comite.getNombre())
                                        .email(comite.getEmail())
                                        .build())
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public ProcesoDocument toDocument(ProcesoCreateCommand command) {
        return ProcesoDocument
                .builder()
                .idProceso(command.id())
                .nombre(command.nombre())
                .resumen(command.resumen())
                .titulo(command.titulo())
                .cantidad(command.cantidad())
                .fechaInicio(command.fechaInicio())
                .fechaFin(command.fechaFin())
                .estado(command.estado())
                .organo(OrganoDocument
                        .builder()
                        .id(command.organo().id())
                        .codigo(command.organo().codigo())
                        .nombre(command.organo().nombre())
                        .sigla(command.organo().sigla())
                        .build())
                .listaProcesoComite(
                        command.listaProcesoComite().stream()
                                .map(comite -> ProcesoComiteDocument
                                        .builder()
                                        .id(comite.id())
                                        .organo(OrganoDocument
                                                .builder()
                                                .id(comite.organo().id())
                                                .codigo(comite.organo().codigo())
                                                .nombre(comite.organo().nombre())
                                                .sigla(comite.organo().sigla())
                                                .build())
                                        .tipo(comite.tipo())
                                        .nombre(comite.nombre())
                                        .email(comite.email())
                                        .build())
                                .collect(Collectors.toList())
                )
                .build();
    }
}
