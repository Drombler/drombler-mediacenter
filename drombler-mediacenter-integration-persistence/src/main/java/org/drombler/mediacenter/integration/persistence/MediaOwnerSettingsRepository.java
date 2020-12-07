package org.drombler.mediacenter.integration.persistence;

import org.drombler.identity.core.DromblerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MediaOwnerSettingsRepository extends JpaRepository<MediaOwnerSettingsEntity, Long> {
    List<MediaOwnerSettingsEntity> findAllByOwner(DromblerId owner);

}
