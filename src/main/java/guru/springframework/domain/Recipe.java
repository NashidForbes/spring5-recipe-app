/*
* StarUML Autogen
*/
package guru.springframework.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


// ----------- << imports@AAAAAAF3mi343CvC9nM= >>
// ----------- >>

@Data
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
	// ----------- << class.extras@AAAAAAF3mi343CvC9nM= >>
// ----------- >>
}