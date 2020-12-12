package org.drombler.mediacenter.integration.persistence;

import org.drombler.identity.core.DromblerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MediaOwnerDefaultStorageRepository extends JpaRepository<MediaOwnerDefaultStorageEntity, Long> {
    List<MediaOwnerDefaultStorageEntity> findAllByOwner(DromblerId owner);

}
