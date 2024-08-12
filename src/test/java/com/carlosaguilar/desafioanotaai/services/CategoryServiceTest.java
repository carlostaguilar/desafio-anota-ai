package com.carlosaguilar.desafioanotaai.services;

import com.carlosaguilar.desafioanotaai.domain.aws.dto.MessageDTO;
import com.carlosaguilar.desafioanotaai.domain.category.Category;
import com.carlosaguilar.desafioanotaai.domain.category.dto.CategoryDTO;
import com.carlosaguilar.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.carlosaguilar.desafioanotaai.repositories.CategoryRepository;
import com.carlosaguilar.desafioanotaai.service.CategoryService;
import com.carlosaguilar.desafioanotaai.service.aws.AwsSnsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.carlosaguilar.desafioanotaai.utils.MockCategories.CATEGORY_ID;
import static com.carlosaguilar.desafioanotaai.utils.MockCategories.DESCRIPTION;
import static com.carlosaguilar.desafioanotaai.utils.MockCategories.OWNER_ID;
import static com.carlosaguilar.desafioanotaai.utils.MockCategories.TITLE;
import static com.carlosaguilar.desafioanotaai.utils.MockCategories.mockCategoryEntity;
import static com.carlosaguilar.desafioanotaai.utils.MockCategories.mockCategoryList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;
    @Mock
    private AwsSnsService snsService;

    @InjectMocks
    private CategoryService service;


    @Test
    void insertValidData() {
        final var input = new CategoryDTO("title", "description", "ownerId");
        final var category = new Category(input);
        doReturn(category).when(repository).save(any(Category.class));

        final var actual = service.insert(input);

        assertEquals(category, actual);
        then(snsService).should().publish(new MessageDTO(category.toString()));
    }

    @Test
    @DisplayName("should delete Category when exists")
    void deleteSuccess() {
        final var category = mockCategoryEntity();
        given(repository.findById(CATEGORY_ID)).willReturn(Optional.of(category));

        this.service.delete(CATEGORY_ID);

        then(repository).should().delete(category);
    }

    @Test
    @DisplayName("should throw exception when Category not exists")
    void deleteError() {
        given(repository.findById(CATEGORY_ID)).willReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> this.service.delete(CATEGORY_ID));
    }

    @Test
    @DisplayName("should return a Category List on getAll")
    void getAllSuccess() {
        given(repository.findAll()).willReturn(mockCategoryList());

        final var result = this.service.getAll();

        assertNotNull(result);
        assertEquals(result.getFirst().getOwnerId(), OWNER_ID);
        assertEquals(result.getFirst().getTitle(), TITLE);
        assertEquals(result.getFirst().getDescription(), DESCRIPTION);
    }

    @Test
    void getById() {
    }
}