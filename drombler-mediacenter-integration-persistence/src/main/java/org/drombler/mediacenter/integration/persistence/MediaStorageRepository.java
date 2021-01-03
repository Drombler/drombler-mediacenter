package org.drombler.mediacenter.integration.persistence;

import org.drombler.identity.core.DromblerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Component
public interface MediaStorageRepository extends JpaRepository<MediaStorageEntity, Long> {

    Optional<MediaStorageEntity> findByMediaStorageId(UUID mediaStorageId);

    List<MediaStorageEntity> findAllByOwnersContaining(DromblerId dromblerId);

//    List<VendorEntity> findAllByUsername(String username);


}
