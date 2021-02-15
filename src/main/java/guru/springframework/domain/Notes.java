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


// ----------- << imports@AAAAAAF3mkKjjjyZK7o= >>
// ----------- >>

@Entity
@JsonIgnoreType
// ----------- << class.annotations@AAAAAAF3mkKjjjyZK7o= >>
// ----------- >>
public class Notes implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// ----------- << id.annotations@AAAAAAF3mkKjjjyZK7o= >>
	// ----------- >>
	private Long id;

	@Version
	// ----------- << version.annotations@AAAAAAF3mkKjjjyZK7o= >>
	// ----------- >>
	private Long version;

	@NotNull
	@Column(nullable = false)
	// ----------- << attribute.annotations@AAAAAAF3mmP6fVRQ3Tc= >>
	// for "large object" data
	@Lob
	// ----------- >>
	private String notes;

	@NotNull
	@OneToOne(mappedBy = "notes")
	// ----------- << attribute.annotations@AAAAAAF3mlRU/kDONtQ= >>
	// ----------- >>
	private Recipe recipe;

	// ----------- << getId.annotations@AAAAAAF3mkKjjjyZK7o= >>
	// ----------- >>
	public Long getId(){
		return id;
	}

	public String getNotes() {
		return notes;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	// ----------- << equals.annotations@AAAAAAF3mkKjjjyZK7o= >>
	// ----------- >>
	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) return true;
		if (getId() == null) return false;
		return obj instanceof Notes && (getId().equals(((Notes) obj).getId()));
	}

	// ----------- << hashCode.annotations@AAAAAAF3mkKjjjyZK7o= >>
	// ----------- >>
	@Override
	public int hashCode() {
		return 165;
	}

// ----------- << class.extras@AAAAAAF3mkKjjjyZK7o= >>
// ----------- >>
}