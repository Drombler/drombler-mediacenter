package org.drombler.mediacenter.integration.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.drombler.media.core.MediaCategoryType.PHOTO;
import static org.drombler.media.core.MediaCategoryType.VIDEO;
import static org.drombler.mediacenter.integration.persistence.PersistenceTestHelper.TEST_PRIVATE_USER_1;
import static org.drombler.mediacenter.integration.persistence.PersistenceTestHelper.TEST_PRIVATE_USER_1_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = "org.drombler.mediacenter.integration.persistence")
class MediaOwnerSettingsRepositoryTest {
    @Autowired
    private MediaOwnerSettingsRepository testee;

    @Autowired
    private MediaStorageRepository mediaStorageRepository;

    @Test
    @WithMockUser(username = TEST_PRIVATE_USER_1_NAME)
    public void findAllByOwner() {
        MediaStorageEntity photoMediaStorageEntity = PersistenceTestHelper.createMediaStorageEntity(1);
        photoMediaStorageEntity = mediaStorageRepository.saveAndFlush(photoMediaStorageEntity);
        MediaStorageEntity videoMediaStorageEntity = PersistenceTestHelper.createMediaStorageEntity(2);
        videoMediaStorageEntity = mediaStorageRepository.saveAndFlush(videoMediaStorageEntity);

        MediaOwnerSettingsEntity mediaOwnerSettingsEntityPhoto = PersistenceTestHelper.createMediaOwnerSettingsEntity(TEST_PRIVATE_USER_1, PHOTO, photoMediaStorageEntity);
        mediaOwnerSettingsEntityPhoto = testee.saveAndFlush(mediaOwnerSettingsEntityPhoto);
        MediaOwnerSettingsEntity mediaOwnerSettingsEntityVideo = PersistenceTestHelper.createMediaOwnerSettingsEntity(TEST_PRIVATE_USER_1, VIDEO, videoMediaStorageEntity);
        mediaOwnerSettingsEntityVideo = testee.saveAndFlush(mediaOwnerSettingsEntityVideo);

        List<MediaOwnerSettingsEntity> result = testee.findAllByOwner(TEST_PRIVATE_USER_1);

        assertThat(result).hasSize(2);
    }

    @Test
    @WithMockUser(username = TEST_PRIVATE_USER_1_NAME)
    public void findById() {
        MediaStorageEntity photoMediaStorageEntity = PersistenceTestHelper.createMediaStorageEntity(1);
        photoMediaStorageEntity = mediaStorageRepository.saveAndFlush(photoMediaStorageEntity);

        MediaOwnerSettingsEntity mediaOwnerSettingsEntityPhoto = PersistenceTestHelper.createMediaOwnerSettingsEntity(TEST_PRIVATE_USER_1, PHOTO, photoMediaStorageEntity);
        mediaOwnerSettingsEntityPhoto = testee.saveAndFlush(mediaOwnerSettingsEntityPhoto);

        Optional<MediaOwnerSettingsEntity> result = testee.findById(mediaOwnerSettingsEntityPhoto.getId());

        assertThat(result).isPresent();
        MediaOwnerSettingsEntity foundMediaOwnerSettingsEntity = result.get();
        assertNotNull(foundMediaOwnerSettingsEntity.getCreatedAt());
        assertEquals(PersistenceTestHelper.TEST_PRIVATE_USER_1_NAME, foundMediaOwnerSettingsEntity.getCreatedBy());
        assertNotNull(foundMediaOwnerSettingsEntity.getLastModifiedAt());
        assertEquals(PersistenceTestHelper.TEST_PRIVATE_USER_1_NAME, foundMediaOwnerSettingsEntity.getLastModifiedBy());

        assertEquals(mediaOwnerSettingsEntityPhoto.getOwner(), foundMediaOwnerSettingsEntity.getOwner());
        assertEquals(mediaOwnerSettingsEntityPhoto.getSupportedMediaCategoryType(), mediaOwnerSettingsEntityPhoto.getSupportedMediaCategoryType());
        assertEquals(mediaOwnerSettingsEntityPhoto.getDefaultStorage(), mediaOwnerSettingsEntityPhoto.getDefaultStorage());
    }
}