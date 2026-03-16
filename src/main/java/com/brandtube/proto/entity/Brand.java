package com.brandtube.proto.entity;

import com.brandtube.proto.entity.base.UserProfileBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "brands")
@Data
@SuperBuilder
@AllArgsConstructor
public class Brand extends UserProfileBaseEntity {

}
