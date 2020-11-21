package org.drombler.mediacenter.model;

import org.drombler.media.photo.core.MediaCenterErrorCode;
import org.softsmithy.lib.util.BusinessException;

/**
 * @author Florian
 */
public class PhotoCenterException extends BusinessException {
    private static final long serialVersionUID = 2423918971447441288L;

    private final MediaCenterErrorCode errorCode;

    public PhotoCenterException(MediaCenterErrorCode errorCode, String message) {
        super(formatMessage(errorCode, message));
        this.errorCode = errorCode;
    }

    public PhotoCenterException(MediaCenterErrorCode errorCode, String message, Throwable cause) {
        super(formatMessage(errorCode, message), cause);
        this.errorCode = errorCode;
    }

    private static String formatMessage(MediaCenterErrorCode errorCode, String message) {
        return errorCode + ": " + message;
    }

    public MediaCenterErrorCode getErrorCode() {
        return errorCode;
    }


}
