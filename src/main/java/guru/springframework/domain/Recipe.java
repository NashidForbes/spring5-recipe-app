/*
* StarUML Autogen
*/
package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


// ----------- << imports@AAAAAAF3mi343CvC9nM= >>
// ----------- >>

@Getter
@Setter
@Document
// ----------- << class.annotations@AAAAAAF3mi343CvC9nM= >>
// ----------- >>
public class Recipe implements Serializable {
	@Id
	// ----------- << id.annotations@AAAAAAF3mi343CvC9nM= >>
	// ----------- >>
	private String id;

/*	@Version
	// ----------- << version.annotations@AAAAAAF3mi343CvC9nM= >>
	// ----------- >>
	private Long version;*/

	// ----------- << attribute.annotations@AAAAAAF3mjS/GDq+erw >>
	// ----------- >>
	private String name;

	// ----------- << attribute.annotations@AAAAAAF3mjS/GDq+8fs= >>
	// ----------- >>
	private Integer prepTime;

	// ----------- << attribute.annotations@AAAAAAF3mjeRKDrGSjU= >>
	// ----------- >>
	private Integer cookTime;

	// ----------- << attribute.annotations@AAAAAAF3mjfahDrOA1s= >>
	// ----------- >>
	private Integer servings;

	// ----------- << attribute.annotations@AAAAAAF3mjk3oTreLb0= >>
	// ----------- >>
	private String source;

	// ----------- << attribute.annotations@AAAAAAF3mjmJkDroltA= >>
	// ----------- >>
	private String url;

	// ----------- << attribute.annotations@AAAAAAF3mjn8pjryxtM= >>
	// ----------- >>
	private String directions;

	// ----------- << attribute.annotations@AAAAAAF3mjtB8TsGS1o= >>
	// for "large object" data
	//@Lob
	// ----------- >>
	private Byte[] image;

    @DBRef
	// ----------- << attribute.annotations@AAAAAAF3mkZbVj6xII4= >>
	// ----------- >>
	private Set<Category> categories = new HashSet<>();

	// ----------- << attribute.annotations@AAAAAAF3mlRU/kDPkoU= >>
	// ----------- >>
	private Notes notes;

	// ----------- << attribute.annotations@AAAAAAF3mlWNbEQ+w6Y= >>
	// ----------- >>
	private Set<Ingredient> ingredients = new HashSet<>();

	// ----------- << attribute.annotations@AAAAAAF3mmf/b161DUk= >>
	// ----------- >>
	private Difficulty difficulty;

	public void setNotes(Notes notes) {
		if(notes != null){
			this.notes = notes;
		}
	}

	public Recipe addIngredient(Ingredient ingredient) {
		ingredient.setRecipe(this);
		this.ingredients.add(ingredient);
		return this;
	}
	// ----------- << class.extras@AAAAAAF3mi343CvC9nM= >>
// ----------- >>
}