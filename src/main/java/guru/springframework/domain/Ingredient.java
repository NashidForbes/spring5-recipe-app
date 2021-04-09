/*
 * StarUML Autogen
 */
package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
// ----------- << imports@AAAAAAF3mkHRmjxf0IE= >>
// ----------- >>

@Getter
@Setter
// @Data weird reported behaviour by John commenting out for now
// using Getters and Setters instead
// ----------- << class.annotations@AAAAAAF3mkHRmjxf0IE= >>
// ----------- >>
public class Ingredient implements Serializable {
    @Id
    // ----------- << id.annotations@AAAAAAF3mkHRmjxf0IE= >>
    // ----------- >>
    // use UUID.randomUUID.toString() to set ID
    // since Mongo is a document based database sublists in a document are not assigned
    // an id
    private String id = UUID.randomUUID().toString();

/*	@Version
	// ----------- << version.annotations@AAAAAAF3mkHRmjxf0IE= >>
	// ----------- >>
	private Long version;*/


    // ----------- << attribute.annotations@AAAAAAF3mlcl00jfzyw= >>
    // ----------- >>
    private String description;


    // ----------- << attribute.annotations@AAAAAAF3mleXIEpRW6g= >>
    // ----------- >>
    private BigDecimal amount;


    //@JoinColumn(name = "recipe_id")
    // ----------- << attribute.annotations@AAAAAAF3n4qvvK2zOvk= >>
    // ----------- >>
    private Recipe recipe;

    @DBRef
    //@JoinColumn(name = "unitOfMeasure_id")
    // ----------- << attribute.annotations@AAAAAAF3n+nmfNXQJ9g= >>
    // ----------- >>
    private UnitOfMeasure unitOfMeasure;

    public Ingredient(String description,
                      BigDecimal amount,
                      UnitOfMeasure unitOfMeasure) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
    }


    public Ingredient(String description,
                      BigDecimal amount,
                      Recipe recipe,
                      UnitOfMeasure unitOfMeasure) {
        this.description = description;
        this.amount = amount;
        //this.recipe = recipe;
        this.unitOfMeasure = unitOfMeasure;
    }

    public Ingredient() {
    }
    // ----------- << class.extras@AAAAAAF3mkHRmjxf0IE= >>
    // ----------- >>
}