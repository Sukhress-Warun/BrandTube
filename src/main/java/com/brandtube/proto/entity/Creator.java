package com.brandtube.proto.entity;

import com.brandtube.proto.entity.base.UserProfileBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "creators")
@Data
@SuperBuilder
@AllArgsConstructor
public class Creator extends UserProfileBaseEntity {

}
