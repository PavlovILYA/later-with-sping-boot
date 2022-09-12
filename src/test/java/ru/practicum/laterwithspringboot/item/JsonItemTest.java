package ru.practicum.laterwithspringboot.item;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.practicum.laterwithspringboot.item.model.ItemCreateDto;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JsonItemTest {

    @Autowired
    private JacksonTester<ItemCreateDto> jacksonTester;

    @Test
    public void checkCreateDto () throws IOException {
        ItemCreateDto itemCreateDto = new ItemCreateDto("https://ya.ru", "Yandex - search system");

        JsonContent<ItemCreateDto> result = jacksonTester.write(itemCreateDto);

        assertThat(result).extractingJsonPathStringValue("$.url").isEqualTo(itemCreateDto.getUrl());
        assertThat(result).extractingJsonPathStringValue("$.description").isEqualTo(itemCreateDto.getDescription());
    }
}
