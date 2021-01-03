package org.drombler.mediacenter.business.impl.converter;

import lombok.RequiredArgsConstructor;
import org.drombler.media.core.MediaCategory;
import org.drombler.media.core.MediaCategoryManager;
import org.drombler.media.core.MediaStorage;
import org.drombler.media.core.protocol.json.MediaCategoryType;
import org.drombler.mediacenter.integration.persistence.MediaStorageEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MediaStorageConverter {
    private final MediaCategoryManager mediaCategoryManager;

    public MediaStorage convertMediaStorage(MediaStorageEntity entity) {
        MediaStorage mediaStorage = new MediaStorage(entity.getMediaStorageId(), entity.getName(),
                entity.getDirectoryPath(), entity.getSupportedStorageContentTypes(), entity.isLegacyEventDirNames(),
                getMediaCategories(entity.getSupportedMediaCategoryTypes()), entity.getOwners());
        return mediaStorage;
    }

    private List<MediaCategory> getMediaCategories(Set<MediaCategoryType> supportedMediaCategoryTypes) {
        return supportedMediaCategoryTypes.stream()
                .map(mediaCategoryManager::getMediaCategory)
                .collect(Collectors.toList());
    }
}
