/*
* StarUML Autogen
*/
package guru.springframework.domain;

import java.math.BigDecimal;
import java.util.*;
import java.time.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.*;


// ----------- << imports@AAAAAAF3mkHRmjxf0IE= >>
// ----------- >>

@Entity
@JsonIgnoreType
// ----------- << class.annotations@AAAAAAF3mkHRmjxf0IE= >>
// ----------- >>
public class Ingredient implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// ----------- << id.annotations@AAAAAAF3mkHRmjxf0IE= >>
	// ----------- >>
	private Long id;

	@Version
	// ----------- << version.annotations@AAAAAAF3mkHRmjxf0IE= >>
	// ----------- >>
	private Long version;

	@NotNull
	@Column(nullable = false)
	// ----------- << attribute.annotations@AAAAAAF3mlcl00jfzyw= >>
	// ----------- >>
	private String description;

	@NotNull
	@Column(nullable = false)
	// ----------- << attribute.annotations@AAAAAAF3mleXIEpRW6g= >>
	// ----------- >>
	private BigDecimal amount;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "recipe_id")
	// ----------- << attribute.annotations@AAAAAAF3n4qvvK2zOvk= >>
	// ----------- >>
	private Recipe recipe;

	@NotNull
	@OneToOne
	@JoinColumn(name = "unitOfMeasure_id")
	// ----------- << attribute.annotations@AAAAAAF3n+nmfNXQJ9g= >>
	// ----------- >>
	private UnitOfMeasure unitOfMeasure;

	// ----------- << getId.annotations@AAAAAAF3mkHRmjxf0IE= >>
	// ----------- >>
	public Long getId(){
		return id;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	// ----------- << equals.annotations@AAAAAAF3mkHRmjxf0IE= >>
	// ----------- >>
	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) return true;
		if (getId() == null) return false;
		return obj instanceof Ingredient && (getId().equals(((Ingredient) obj).getId()));
	}

	// ----------- << hashCode.annotations@AAAAAAF3mkHRmjxf0IE= >>
	// ----------- >>
	@Override
	public int hashCode() {
		return 429;
	}

// ----------- << class.extras@AAAAAAF3mkHRmjxf0IE= >>
// ----------- >>
}