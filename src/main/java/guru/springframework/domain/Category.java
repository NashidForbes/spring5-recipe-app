/*
* StarUML Autogen
*/
package guru.springframework.domain;

import java.util.*;
import java.time.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
// ----------- << imports@AAAAAAF3mjyVLTsRaMk= >>
// ----------- >>

@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
@JsonIgnoreType
// ----------- << class.annotations@AAAAAAF3mjyVLTsRaMk= >>
// ----------- >>
public class Category implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// ----------- << id.annotations@AAAAAAF3mjyVLTsRaMk= >>
	// ----------- >>
	private Long id;

	@Version
	// ----------- << version.annotations@AAAAAAF3mjyVLTsRaMk= >>
	// ----------- >>
	private Long version;

	@NotNull
	@Column(nullable = false)
	// ----------- << attribute.annotations@AAAAAAF3mjzmCTsVE5A= >>
	// ----------- >>
	private String departmentName;

	@ManyToMany(mappedBy="categories", cascade = CascadeType.PERSIST)
/*	@JoinTable(name = "category_recipes", joinColumns = {@JoinColumn(name =
			"category_id")}, inverseJoinColumns = {@JoinColumn(name = "recipe_id")})*/
	// ----------- << attribute.annotations@AAAAAAF3ozs6oyR5BEI= >>
    // *****FOR ABOVE ANNOTATION****
	// to avoid generating redundant join sql tables when doing
	// bi-directional
	// many to many
    // relationships.  Use the "@ManyToMany(MappedBy = <other class join field>)".
	// ----------- >>
	private Set<Recipe> recipes = new HashSet<>();
	// ----------- << class.extras@AAAAAAF3mjyVLTsRaMk= >>
// ----------- >>
}