/*
* StarUML Autogen
*/
package guru.springframework.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


// ----------- << imports@AAAAAAF3mi343CvC9nM= >>
// ----------- >>

@Entity
@JsonIgnoreType
// ----------- << class.annotations@AAAAAAF3mi343CvC9nM= >>
// ----------- >>
public class Recipe implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// ----------- << id.annotations@AAAAAAF3mi343CvC9nM= >>
	// ----------- >>
	private Long id;

	@Version
	// ----------- << version.annotations@AAAAAAF3mi343CvC9nM= >>
	// ----------- >>
	private Long version;

	@NotNull
	@Column(nullable = false)
	// ----------- << attribute.annotations@AAAAAAF3mjS/GDq+erw >>
	// ----------- >>
	private String name;

	@NotNull
	@Column(nullable = false)
	// ----------- << attribute.annotations@AAAAAAF3mjS/GDq+8fs= >>
	// ----------- >>
	private Integer prepTime;

	@NotNull
	@Column(nullable = false)
	// ----------- << attribute.annotations@AAAAAAF3mjeRKDrGSjU= >>
	// ----------- >>
	private Integer cookTime;

	@NotNull
	@Column(nullable = false)
	// ----------- << attribute.annotations@AAAAAAF3mjfahDrOA1s= >>
	// ----------- >>
	private Integer servings;

	@NotNull
	@Column(nullable = false)
	// ----------- << attribute.annotations@AAAAAAF3mjk3oTreLb0= >>
	// ----------- >>
	private String source;

	@NotNull
	@Column(nullable = false)
	// ----------- << attribute.annotations@AAAAAAF3mjmJkDroltA= >>
	// ----------- >>
	private String url;

	@NotNull
	@Column(nullable = false, length = 10480)
	// ----------- << attribute.annotations@AAAAAAF3mjn8pjryxtM= >>
	// ----------- >>
	private String directions;

	//@NotNull
	@Column(nullable = true)
	// ----------- << attribute.annotations@AAAAAAF3mjtB8TsGS1o= >>
	// for "large object" data
	@Lob
	// ----------- >>
	private Byte[] image;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "recipe_categories", joinColumns = {@JoinColumn(name =
			"recipe_id")}, inverseJoinColumns = {@JoinColumn(name = "category_id")})
	// ----------- << attribute.annotations@AAAAAAF3mkZbVj6xII4= >>
	// ----------- >>
	private Set<Category> categories = new HashSet<>();

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "notes_id")
	// ----------- << attribute.annotations@AAAAAAF3mlRU/kDPkoU= >>
	// ----------- >>
	private Notes notes;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	//@JoinColumn(name = "recipe_id")
	// ----------- << attribute.annotations@AAAAAAF3mlWNbEQ+w6Y= >>
	// ----------- >>
	private Set<Ingredient> ingredients = new HashSet<>();

	// ----------- << attribute.annotations@AAAAAAF3mmf/b161DUk= >>
	@Enumerated(value = EnumType.STRING)
	// ----------- >>
	private Difficulty difficulty;

	// ----------- << getId.annotations@AAAAAAF3mi343CvC9nM= >>
	// ----------- >>
	public Long getId(){
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getPrepTime() {
		return prepTime;
	}

	public Integer getCookTime() {
		return cookTime;
	}

	public Integer getServings() {
		return servings;
	}

	public String getSource() {
		return source;
	}

	public String getUrl() {
		return url;
	}

	public String getDirections() {
		return directions;
	}

	public Byte[] getImage() {
		return image;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public Notes getNotes() {
		return notes;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrepTime(Integer prepTime) {
		this.prepTime = prepTime;
	}

	public void setCookTime(Integer cookTime) {
		this.cookTime = cookTime;
	}

	public void setServings(Integer servings) {
		this.servings = servings;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public void setNotes(Notes notes) {
		this.notes = notes;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	// ----------- << equals.annotations@AAAAAAF3mi343CvC9nM= >>
	// ----------- >>
	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) return true;
		if (getId() == null) return false;
		return obj instanceof Recipe && (getId().equals(((Recipe) obj).getId()));
	}

	// ----------- << hashCode.annotations@AAAAAAF3mi343CvC9nM= >>
	// ----------- >>
	@Override
	public int hashCode() {
		return 416;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	// ----------- << class.extras@AAAAAAF3mi343CvC9nM= >>
// ----------- >>
}