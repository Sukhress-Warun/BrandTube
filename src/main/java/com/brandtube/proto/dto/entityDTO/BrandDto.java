package com.brandtube.proto.dto.entityDTO;

import com.brandtube.proto.dto.entityDTO.base.UserProfileBaseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
public class BrandDto extends UserProfileBaseDTO {

}
