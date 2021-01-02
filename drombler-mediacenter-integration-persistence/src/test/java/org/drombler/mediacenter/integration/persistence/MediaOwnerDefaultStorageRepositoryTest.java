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
import static org.drombler.media.core.protocol.json.MediaCategoryType.PHOTO;
import static org.drombler.media.core.protocol.json.MediaCategoryType.VIDEO;
import static org.drombler.mediacenter.integration.persistence.PersistenceTestHelper.TEST_PRIVATE_USER_1;
import static org.drombler.mediacenter.integration.persistence.PersistenceTestHelper.TEST_PRIVATE_USER_1_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = "org.drombler.mediacenter.integration.persistence")
class MediaOwnerDefaultStorageRepositoryTest {
    @Autowired
    private MediaOwnerDefaultStorageRepository testee;

    @Autowired
    private MediaStorageRepository mediaStorageRepository;

    @Test
    @WithMockUser(username = TEST_PRIVATE_USER_1_NAME)
    public void findAllByOwner() {
        MediaStorageEntity photoMediaStorageEntity = PersistenceTestHelper.createMediaStorageEntity(1);
        photoMediaStorageEntity = mediaStorageRepository.saveAndFlush(photoMediaStorageEntity);
        MediaStorageEntity videoMediaStorageEntity = PersistenceTestHelper.createMediaStorageEntity(2);
        videoMediaStorageEntity = mediaStorageRepository.saveAndFlush(videoMediaStorageEntity);

        MediaOwnerDefaultStorageEntity mediaOwnerDefaultStorageEntityPhoto = PersistenceTestHelper.createMediaOwnerDefaultStorageEntity(TEST_PRIVATE_USER_1, PHOTO, photoMediaStorageEntity);
        mediaOwnerDefaultStorageEntityPhoto = testee.saveAndFlush(mediaOwnerDefaultStorageEntityPhoto);
        MediaOwnerDefaultStorageEntity mediaOwnerDefaultStorageEntityVideo = PersistenceTestHelper.createMediaOwnerDefaultStorageEntity(TEST_PRIVATE_USER_1, VIDEO, videoMediaStorageEntity);
        mediaOwnerDefaultStorageEntityVideo = testee.saveAndFlush(mediaOwnerDefaultStorageEntityVideo);

        List<MediaOwnerDefaultStorageEntity> result = testee.findAllByOwner(TEST_PRIVATE_USER_1);

        assertThat(result).hasSize(2);
    }

    @Test
    @WithMockUser(username = TEST_PRIVATE_USER_1_NAME)
    public void findById() {
        MediaStorageEntity photoMediaStorageEntity = PersistenceTestHelper.createMediaStorageEntity(1);
        photoMediaStorageEntity = mediaStorageRepository.saveAndFlush(photoMediaStorageEntity);

        MediaOwnerDefaultStorageEntity mediaOwnerDefaultStorageEntityPhoto = PersistenceTestHelper.createMediaOwnerDefaultStorageEntity(TEST_PRIVATE_USER_1, PHOTO, photoMediaStorageEntity);
        mediaOwnerDefaultStorageEntityPhoto = testee.saveAndFlush(mediaOwnerDefaultStorageEntityPhoto);

        Optional<MediaOwnerDefaultStorageEntity> result = testee.findById(mediaOwnerDefaultStorageEntityPhoto.getId());

        assertThat(result).isPresent();
        MediaOwnerDefaultStorageEntity foundMediaOwnerDefaultStorageEntity = result.get();
        assertNotNull(foundMediaOwnerDefaultStorageEntity.getCreatedAt());
        assertEquals(PersistenceTestHelper.TEST_PRIVATE_USER_1_NAME, foundMediaOwnerDefaultStorageEntity.getCreatedBy());
        assertNotNull(foundMediaOwnerDefaultStorageEntity.getLastModifiedAt());
        assertEquals(PersistenceTestHelper.TEST_PRIVATE_USER_1_NAME, foundMediaOwnerDefaultStorageEntity.getLastModifiedBy());

        assertEquals(mediaOwnerDefaultStorageEntityPhoto.getOwner(), foundMediaOwnerDefaultStorageEntity.getOwner());
        assertEquals(mediaOwnerDefaultStorageEntityPhoto.getSupportedMediaCategoryType(), mediaOwnerDefaultStorageEntityPhoto.getSupportedMediaCategoryType());
        assertEquals(mediaOwnerDefaultStorageEntityPhoto.getDefaultStorage(), mediaOwnerDefaultStorageEntityPhoto.getDefaultStorage());
    }
}