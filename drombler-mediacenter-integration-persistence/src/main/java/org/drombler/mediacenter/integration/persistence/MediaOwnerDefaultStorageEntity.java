package org.drombler.mediacenter.integration.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.drombler.commons.spring.jpa.AbstractAuditableEntity;
import org.drombler.identity.core.DromblerId;
import org.drombler.media.core.MediaCategoryType;
import org.drombler.mediacenter.integration.persistence.impl.DromblerIdConverter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "mediaowner_defaultstorage")
public class MediaOwnerDefaultStorageEntity extends AbstractAuditableEntity {
    /**
     * The technical id of this entity (surrogate PK).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    @Column(updatable = false)
    @Convert(converter = DromblerIdConverter.class)
    private DromblerId owner;

    @NotNull
    @Column(name = "mediacategory_type")
    @Enumerated(EnumType.STRING)
    private MediaCategoryType supportedMediaCategoryType;

    @ManyToOne
    @JoinColumn
    private MediaStorageEntity defaultStorage;

}
