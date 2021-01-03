package org.drombler.mediacenter.business.impl;

import lombok.RequiredArgsConstructor;
import org.drombler.commons.spring.transaction.stereotype.TransactionalService;
import org.drombler.media.core.MediaCategoryManager;
import org.drombler.media.core.MediaStorage;
import org.drombler.media.photo.core.MediaCenterErrorCode;
import org.drombler.mediacenter.business.MediaStorageService;
import org.drombler.mediacenter.business.impl.converter.MediaStorageConverter;
import org.drombler.mediacenter.integration.persistence.MediaStorageEntity;
import org.drombler.mediacenter.integration.persistence.MediaStorageRepository;
import org.drombler.mediacenter.model.MediaCenterException;

import java.util.List;
import java.util.UUID;

@TransactionalService
@RequiredArgsConstructor
public class MediaStorageServiceImpl implements MediaStorageService {
    private final MediaStorageRepository mediaStorageRepository;
    private final MediaCategoryManager mediaCategoryManager;

    @Override
    public List<MediaStorage> getMediaStorages() {
        return null;
    }

    @Override
    public MediaStorage getMediaStorage(UUID mediaStorageId) throws MediaCenterException {
        MediaStorageEntity mediaStorageEntity = mediaStorageRepository.findByMediaStorageId(mediaStorageId)
                .orElseThrow(() -> new MediaCenterException(MediaCenterErrorCode.MEDIACENTER_UNKNOWN_MEDIA_STORAGE, "Could not fin media storage for id: " + mediaStorageId));
        MediaStorageConverter converter = new MediaStorageConverter(mediaCategoryManager);
        return converter.convertMediaStorage(mediaStorageEntity);
    }

    @Override
    public MediaStorage addMediaStorage(MediaStorage mediaStorage) {
        return null;
    }

    @Override
    public MediaStorage deleteMediaStorage(UUID mediaStorageId) {
        return null;
    }
}
