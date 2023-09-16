package com.laioffer.twitch;

import com.laioffer.twitch.db.FavoriteRecordRepository;
import com.laioffer.twitch.db.ItemRepository;
import com.laioffer.twitch.db.entity.FavoriteRecordEntity;
import com.laioffer.twitch.db.entity.ItemEntity;
import com.laioffer.twitch.db.entity.UserEntity;
import com.laioffer.twitch.favorite.DuplicateFavoriteException;
import com.laioffer.twitch.favorite.FavoriteService;
import com.laioffer.twitch.model.ItemType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FavoriteServiceTests {

    @Mock private ItemRepository itemRepository;
    @Mock private FavoriteRecordRepository favoriteRecordRepository;

    @Captor ArgumentCaptor<FavoriteRecordEntity> favoriteRecordArgumentCaptor;

    private FavoriteService favoriteService;

    @BeforeEach
    public void setup() {
        favoriteService = new FavoriteService(itemRepository, favoriteRecordRepository);
    }

    @Test
    public void whenItemNotExist_setFavoriteItem_shouldSaveItem() throws DuplicateFavoriteException {
        UserEntity user = new UserEntity(1L, "user", "foo", "bar", "123456");
        ItemEntity item = new ItemEntity(null, "twitchId", "title", "url", "thumb", "broadcaster", "gameid", ItemType.VIDEO);
        ItemEntity persisted = new ItemEntity(1L, "twitchId", "title", "url", "thumb", "broadcaster", "gameid", ItemType.VIDEO);
        Mockito.when(itemRepository.findByTwitchId("twitchId")).thenReturn(null);
        Mockito.when(itemRepository.save(item)).thenReturn(persisted);

        favoriteService.setFavoriteItem(user, item);

        Mockito.verify(itemRepository).save(item);
    }

    @Test
    public void whenItemExist_setFavoriteItem_shouldNotSaveItem() throws DuplicateFavoriteException {
        UserEntity user = new UserEntity(1L, "user", "foo", "bar", "123456");
        ItemEntity item = new ItemEntity(null, "twitchId", "title", "url", "thumb", "broadcaster", "gameid", ItemType.VIDEO);
        ItemEntity persisted = new ItemEntity(1L, "twitchId", "title", "url", "thumb", "broadcaster", "gameid", ItemType.VIDEO);
        Mockito.when(itemRepository.findByTwitchId("twitchId")).thenReturn(persisted);

        favoriteService.setFavoriteItem(user, item);

        Mockito.verify(itemRepository, Mockito.never()).save(item);
    }

    @Test
    public void setFavoriteItem_shouldCreateFavoriteRecord() throws DuplicateFavoriteException {
        UserEntity user = new UserEntity(1L, "user", "foo", "bar", "123456");
        ItemEntity item = new ItemEntity(null, "twitchId", "title", "url", "thumb", "broadcaster", "gameid", ItemType.VIDEO);
        ItemEntity persisted = new ItemEntity(1L, "twitchId", "title", "url", "thumb", "broadcaster", "gameid", ItemType.VIDEO);
        Mockito.when(itemRepository.findByTwitchId("twitchId")).thenReturn(persisted);

        favoriteService.setFavoriteItem(user, item);

        Mockito.verify(favoriteRecordRepository).save(favoriteRecordArgumentCaptor.capture());
        FavoriteRecordEntity favorite = favoriteRecordArgumentCaptor.getValue();

        Assertions.assertEquals(1L, favorite.itemId());
        Assertions.assertEquals(1L, favorite.userId());
    }

    @Test
    public void whenItemNotExist_unsetFavoriteItem_shouldNotDeleteFavoriteRecord() {
        UserEntity user = new UserEntity(1L, "user", "foo", "bar", "123456");
        Mockito.when(itemRepository.findByTwitchId("twitchId")).thenReturn(null);

        favoriteService.unsetFavoriteItem(user, "twitchId");

        Mockito.verifyNoInteractions(favoriteRecordRepository);
    }

    @Test
    public void whenItemExist_unsetFavoriteItem_shouldDeleteFavoriteRecord() {
        UserEntity user = new UserEntity(1L, "user", "foo", "bar", "123456");
        ItemEntity persisted = new ItemEntity(1L, "twitchId", "title", "url", "thumb", "broadcaster", "gameid", ItemType.VIDEO);
        Mockito.when(itemRepository.findByTwitchId("twitchId")).thenReturn(persisted);

        favoriteService.unsetFavoriteItem(user, "twitchId");

        Mockito.verify(favoriteRecordRepository).delete(1L, 1L);
    }
}

