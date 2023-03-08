package de.justinklein.stattrackerspringbe.util;

import java.util.Collection;
import java.util.stream.Collectors;

public interface DataMapper<EntityType, DtoType> {

  DtoType mapEntityToDto(EntityType entity);
  EntityType mapDtoToEntity(DtoType dto);

  default Collection<DtoType> mapEntitiesToDtos(Collection<EntityType> entities) {
    return entities.stream().map(this::mapEntityToDto).collect(Collectors.toList());
  }
  default Collection<EntityType> mapDtosToEntities(Collection<DtoType> dtos) {
    return dtos.stream().map(this::mapDtoToEntity).collect(Collectors.toList());
  }
}
