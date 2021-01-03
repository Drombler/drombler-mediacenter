package org.drombler.mediacenter.business;

import org.drombler.media.core.MediaStorage;
import org.drombler.mediacenter.model.MediaCenterException;

import java.util.List;
import java.util.UUID;

public interface MediaStorageService {
     List<MediaStorage> getMediaStorages();

     MediaStorage getMediaStorage(UUID mediaStorageId) throws MediaCenterException;

     MediaStorage addMediaStorage(MediaStorage mediaStorage);

     MediaStorage deleteMediaStorage(UUID mediaStorageId);
}
