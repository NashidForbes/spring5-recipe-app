package guru.springframework.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() throws Exception {
        category = new Category();
    }

    @Test
    public void getId() {
        String id = "41";
        category.setId(id);
        assertEquals(id, category.getId());
    }

    @Test
    public void getVersion() {
    }

    @Test
    public void getDepartmentName() {
    }

    @Test
    public void getRecipes() {
    }
}