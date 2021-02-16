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


// ----------- << imports@AAAAAAF3mjyVLTsRaMk= >>
// ----------- >>

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

	// ----------- << getId.annotations@AAAAAAF3mjyVLTsRaMk= >>
	// ----------- >>
	public Long getId(){
		return id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public Set<Recipe> getRecipes() {
		return recipes;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public void setRecipes(Set<Recipe> recipes) {
		this.recipes = recipes;
	}

	// ----------- << equals.annotations@AAAAAAF3mjyVLTsRaMk= >>
	// ----------- >>
	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) return true;
		if (getId() == null) return false;
		return obj instanceof Category && (getId().equals(((Category) obj).getId()));
	}

	// ----------- << hashCode.annotations@AAAAAAF3mjyVLTsRaMk= >>
	// ----------- >>
	@Override
	public int hashCode() {
		return 248;
	}

// ----------- << class.extras@AAAAAAF3mjyVLTsRaMk= >>
// ----------- >>
}