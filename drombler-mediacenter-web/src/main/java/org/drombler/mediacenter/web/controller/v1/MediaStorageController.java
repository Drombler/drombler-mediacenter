package org.drombler.mediacenter.web.controller.v1;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.drombler.media.core.MediaStorage;
import org.drombler.mediacenter.business.MediaStorageService;
import org.drombler.mediacenter.model.MediaCenterException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.drombler.mediacenter.web.controller.RestControllerUtils.V1_PATH;

@Api(tags = {"MediaStorageController V1"})
@RestController("MediaStorageControllerV1")
@RequestMapping(path = V1_PATH + "/me/mediastorages")
@RequiredArgsConstructor
public class MediaStorageController {

    private final MediaStorageService mediaStorageService;

    public List<MediaStorage> getMediaStorages() {
        return mediaStorageService.getMediaStorages();
    }

    public MediaStorage getMediaStorage(UUID mediaStorageId) throws MediaCenterException {
        return mediaStorageService.getMediaStorage(mediaStorageId);
    }
}
