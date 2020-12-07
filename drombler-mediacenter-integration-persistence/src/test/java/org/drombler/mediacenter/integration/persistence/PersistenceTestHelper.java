package org.drombler.mediacenter.integration.persistence;

import org.drombler.identity.core.DromblerUserId;
import org.drombler.media.core.MediaCategoryType;
import org.drombler.media.core.MediaStorageType;

import java.nio.file.Paths;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.UUID;

import static java.util.Arrays.asList;

public final class PersistenceTestHelper {
    public static final String TEST_PRIVATE_USER_1_NAME = "testuser1";
    public static final String TEST_PRIVATE_USER_2_NAME = "testuser2";
    public static final String TEST_PRIVATE_USER_3_NAME = "testuser3";
    public static final String TEST_PRIVATE_USER_4_NAME = "testuser4";
    public static final DromblerUserId TEST_PRIVATE_USER_1 = new DromblerUserId(TEST_PRIVATE_USER_1_NAME);
    public static final DromblerUserId TEST_PRIVATE_USER_2 = new DromblerUserId(TEST_PRIVATE_USER_2_NAME);
    public static final DromblerUserId TEST_PRIVATE_USER_3 = new DromblerUserId(TEST_PRIVATE_USER_3_NAME);
    public static final DromblerUserId TEST_PRIVATE_USER_4 = new DromblerUserId(TEST_PRIVATE_USER_4_NAME);

    private PersistenceTestHelper() {
    }

    public static MediaStorageEntity createMediaStorageEntity(int nameId) {
        MediaStorageEntity mediaStorageEntity = new MediaStorageEntity();
        mediaStorageEntity.setMediaStorageId(UUID.randomUUID());
        mediaStorageEntity.setName("My Media Storage " + nameId);
        mediaStorageEntity.setDirectoryPath(Paths.get(mediaStorageEntity.getName()));
        mediaStorageEntity.setSupportedStorageTypes(EnumSet.of(MediaStorageType.SHARED_EVENTS));
        mediaStorageEntity.setOwners(new HashSet<>(asList(TEST_PRIVATE_USER_1, TEST_PRIVATE_USER_2)));
        mediaStorageEntity.setSupportedMediaCategoryTypes(EnumSet.of(MediaCategoryType.PHOTO));
        return mediaStorageEntity;
    }

//    public static final List<PhotoEntity> createEventEntities(int num) {
//        return IntStream.range(1, num + 1)
//                .mapToObj(PersistenceTestHelper::createEventEntity)
//                .collect(Collectors.toList());
//    }

//    public static final PhotoEntity createEventEntity(int nameId) {
//        PhotoEntity photoEntity = new PhotoEntity();
//        photoEntity.setEventId(UUID.randomUUID());
//        photoEntity.setName("My Test Event " + nameId);
//        photoEntity.setPreferredDirName("My-Test-Event-" + nameId);
//        photoEntity.setStartDate(LocalDate.of(2020, 10, 4));
//        photoEntity.setEndDate(LocalDate.of(2020, 10, 4));
//        photoEntity.setOwners(new HashSet<>(asList(TEST_PRIVATE_USER_1, TEST_PRIVATE_USER_2)));
//        photoEntity.setOrganizers(new HashSet<>(asList(TEST_PRIVATE_USER_3)));
//        photoEntity.setParticipants(new HashSet<>(asList(TEST_PRIVATE_USER_1, TEST_PRIVATE_USER_2, TEST_PRIVATE_USER_3, TEST_PRIVATE_USER_4)));
//        return photoEntity;
//    }
}
