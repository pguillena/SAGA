package reclutamiento.app.api_proceso.mongodb.adapter;

import org.springframework.stereotype.Component;
import reclutamiento.app.api_proceso.command.dto.message.ProcesoComiteMessage;
import reclutamiento.app.api_proceso.entities.ProcesoComiteEntity;
import reclutamiento.app.api_proceso.mongodb.command.OrganoCreateCommand;
import reclutamiento.app.api_proceso.mongodb.command.ProcesoComiteCreateCommand;
import reclutamiento.app.api_proceso.mongodb.document.OrganoDocument;
import reclutamiento.app.api_proceso.mongodb.document.ProcesoComiteDocument;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProcesoComiteAdapterImpl implements  ProcesoComiteAdapter {
    @Override
    public List<ProcesoComiteCreateCommand> toListCommand(List<ProcesoComiteMessage> procesoMessages) {
        return procesoMessages.stream()
                .map(comite -> ProcesoComiteCreateCommand.builder()
                        .id(comite.getId())
                        .organo(OrganoCreateCommand.builder()
                                .id(comite.getOrgano().getId())
                                .codigo(comite.getOrgano().getCodigo())
                                .nombre(comite.getOrgano().getNombre())
                                .sigla(comite.getOrgano().getSigla())
                                .build())
                        .tipo(comite.getTipo())
                        .nombre(comite.getNombre())
                        .email(comite.getEmail())
                        .build())
                .toList();
    }

    @Override
    public List<ProcesoComiteDocument> toListDocument(List<ProcesoComiteCreateCommand> commands) {
        return commands.stream()
                .map(command -> ProcesoComiteDocument.builder()
                        .idProcesoComite(command.id())
                        .organo(OrganoDocument.builder()
                                .id(command.organo().id())
                                .codigo(command.organo().codigo())
                                .nombre(command.organo().nombre())
                                .sigla(command.organo().sigla())
                                .build())
                        .tipo(command.tipo())
                        .nombre(command.nombre())
                        .email(command.email())
                        .build())
                .toList();
    }

    @Override
    public List<ProcesoComiteCreateCommand> entityToListCommand(List<ProcesoComiteEntity> listaProcesoComiteEntity) {
        return listaProcesoComiteEntity.stream()
                .map(entity -> ProcesoComiteCreateCommand.builder()
                        .id(entity.getId())
                        .organo(OrganoCreateCommand.builder()
                                .id(entity.getOrgano().getId())
                                .codigo(entity.getOrgano().getCodigo())
                                .nombre(entity.getOrgano().getNombre())
                                .sigla(entity.getOrgano().getSigla())
                                .build())
                        .tipo(entity.getTipo())
                        .nombre(entity.getNombre())
                        .email(entity.getEmail())
                        .build())
                .toList();
    }

}