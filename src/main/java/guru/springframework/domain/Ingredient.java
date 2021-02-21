/*
* StarUML Autogen
*/
package guru.springframework.domain;

import java.math.BigDecimal;
import java.util.*;
import java.time.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
// ----------- << imports@AAAAAAF3mkHRmjxf0IE= >>
// ----------- >>

@Data
@EqualsAndHashCode(exclude = {"recipe"})
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
	@ManyToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "recipe_id")
	// ----------- << attribute.annotations@AAAAAAF3n4qvvK2zOvk= >>
	// ----------- >>
	private Recipe recipe;

	//@NotNull
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	//@JoinColumn(name = "unitOfMeasure_id")
	// ----------- << attribute.annotations@AAAAAAF3n+nmfNXQJ9g= >>
	// ----------- >>
	private UnitOfMeasure unitOfMeasure;

	public Ingredient( String description,
					   BigDecimal amount,
					   Recipe recipe,
					   UnitOfMeasure unitOfMeasure) {
		this.description = description;
		this.amount = amount;
		this.recipe = recipe;
		this.unitOfMeasure = unitOfMeasure;
	}

	public Ingredient() {

	}
	// ----------- << class.extras@AAAAAAF3mkHRmjxf0IE= >>
// ----------- >>
}