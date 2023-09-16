package hello.itemService.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Item item = new Item("item", 10000, 10);

        // when
        Item saveItem = itemRepository.save(item);

        // then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(saveItem);
    }

    @Test
    void findAll() {
        // give
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 30000, 20);
        
        itemRepository.save(item1);
        itemRepository.save(item2);

        // when
        List<Item> result = itemRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void update() {
        // given
        Item item = new Item("item1", 10000, 10);
        Item saveItem = itemRepository.save(item);

        // when
        Item updateItem = new Item("newItem", 5000, 10);
        itemRepository.update(saveItem.getId(), updateItem);

        // then
        Item foundItem = itemRepository.findById(item.getId());

        assertThat(foundItem.getItemName()).isEqualTo(updateItem.getItemName());
        assertThat(foundItem.getPrice()).isEqualTo(updateItem.getPrice());
        assertThat(foundItem.getQuantity()).isEqualTo(updateItem.getQuantity());
    }
}