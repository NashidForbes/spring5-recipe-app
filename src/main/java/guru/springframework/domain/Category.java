/*
* StarUML Autogen
*/
package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Set;
// ----------- << imports@AAAAAAF3mjyVLTsRaMk= >>
// ----------- >>

@Getter
@Setter
@Document
// ----------- << class.annotations@AAAAAAF3mjyVLTsRaMk= >>
// ----------- >>
public class Category implements Serializable {
	@Id
	// ----------- << id.annotations@AAAAAAF3mjyVLTsRaMk= >>
	// ----------- >>
	private String id;

	// ----------- << version.annotations@AAAAAAF3mjyVLTsRaMk= >>
	// ----------- >>
	//private Long version;

	// ----------- << attribute.annotations@AAAAAAF3mjzmCTsVE5A= >>
	// ----------- >>
	private String departmentName;


/*	@JoinTable(name = "category_recipes", joinColumns = {@JoinColumn(name =
			"category_id")}, inverseJoinColumns = {@JoinColumn(name = "recipe_id")})*/
	// ----------- << attribute.annotations@AAAAAAF3ozs6oyR5BEI= >>
    // *****FOR ABOVE ANNOTATION****
	// to avoid generating redundant join sql tables when doing
	// bi-directional
	// many to many
    // relationships.  Use the "@ManyToMany(MappedBy = <other class join field>)".
	// ----------- >>
	private Set<Recipe> recipes;
	// ----------- << class.extras@AAAAAAF3mjyVLTsRaMk= >>
// ----------- >>
}