package org.drombler.mediacenter.integration.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.drombler.commons.spring.jpa.AbstractAuditableEntity;
import org.drombler.identity.core.DromblerId;
import org.drombler.media.core.MediaCategoryType;
import org.drombler.mediacenter.integration.persistence.impl.DromblerIdConverter;
import org.drombler.mediacenter.integration.persistence.impl.PathConverter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.nio.file.Path;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "mediastorage")
public class MediaStorageEntity extends AbstractAuditableEntity {
    /**
     * The technical id of this entity (surrogate PK).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    @Column(name = "mediastorage_id", updatable = false)
    private UUID mediaStorageId;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Convert(converter = PathConverter.class)
    @Column
    private Path directoryPath;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "mediastorage_category", joinColumns = @JoinColumn(name = "mediastorage_id"))
    @Column(name = "mediacategory_type")
    @Enumerated(EnumType.STRING)
    private Set<MediaCategoryType> supportedMediaCategoryTypes;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "mediastorage_owner", joinColumns = @JoinColumn(name = "mediastorage_id"))
    @Column(name = "owner")
    @Convert(converter = DromblerIdConverter.class)
    private Set<DromblerId> owners;

    @NotNull
    @Column
    @Enumerated(EnumType.STRING)
    private Visibility visibility;
}
