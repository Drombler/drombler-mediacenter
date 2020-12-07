package org.drombler.mediacenter.integration.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = "org.drombler.mediacenter.integration.persistence")
public class MediaStorageRepositoryTest {

    @Autowired
    private MediaStorageRepository testee;

    @Test
    @WithMockUser(username = PersistenceTestHelper.TEST_PRIVATE_USER_1_NAME)
    public void findByMediaStorageId() {
        MediaStorageEntity mediaStorageEntity = PersistenceTestHelper.createMediaStorageEntity(1);
        mediaStorageEntity = testee.saveAndFlush(mediaStorageEntity);

        Optional<MediaStorageEntity> result = testee.findByMediaStorageId(mediaStorageEntity.getMediaStorageId());

        assertThat(result).isPresent();
        MediaStorageEntity foundMediaStorageEntity = result.get();
        assertNotNull(foundMediaStorageEntity.getCreatedAt());
        assertEquals(PersistenceTestHelper.TEST_PRIVATE_USER_1_NAME, foundMediaStorageEntity.getCreatedBy());
        assertNotNull(foundMediaStorageEntity.getLastModifiedAt());
        assertEquals(PersistenceTestHelper.TEST_PRIVATE_USER_1_NAME, foundMediaStorageEntity.getLastModifiedBy());

        assertThat(foundMediaStorageEntity.getName()).isEqualTo(mediaStorageEntity.getName());
        assertThat(foundMediaStorageEntity.getDirectoryPath()).isEqualTo(mediaStorageEntity.getDirectoryPath());

        assertThat(foundMediaStorageEntity.getSupportedStorageTypes()).isEqualTo(mediaStorageEntity.getSupportedStorageTypes());
        assertThat(foundMediaStorageEntity.getOwners()).isEqualTo(mediaStorageEntity.getOwners());
        assertThat(foundMediaStorageEntity.getSupportedMediaCategoryTypes()).isEqualTo(mediaStorageEntity.getSupportedMediaCategoryTypes());
    }
//
//    @Test
//    @WithMockUser(username = PersistenceTestHelper.TEST_PRIVATE_USER_1_NAME)
//    public void findAllByOwnersContaining() {
//        List<PhotoEntity> eventEntities = PersistenceTestHelper.createEventEntities(5);
//        eventEntities.get(0).setOwners(new HashSet<>(asList(PersistenceTestHelper.TEST_PRIVATE_USER_1)));
//        eventEntities.get(1).setOwners(new HashSet<>(asList(PersistenceTestHelper.TEST_PRIVATE_USER_1)));
//        eventEntities.get(2).setOwners(new HashSet<>(asList(PersistenceTestHelper.TEST_PRIVATE_USER_1, PersistenceTestHelper.TEST_PRIVATE_USER_2)));
//        eventEntities.get(3).setOwners(new HashSet<>(asList(PersistenceTestHelper.TEST_PRIVATE_USER_2, PersistenceTestHelper.TEST_PRIVATE_USER_1)));
//        eventEntities.get(4).setOwners(new HashSet<>(asList(PersistenceTestHelper.TEST_PRIVATE_USER_2)));
//        eventEntities = testee.saveAll(eventEntities);
//
//        List<PhotoEntity> result = testee.findAllByOwnersContaining(PersistenceTestHelper.TEST_PRIVATE_USER_1);
//
//        assertThat(result).containsAll(eventEntities.subList(0, 4));
//
//    }

}
