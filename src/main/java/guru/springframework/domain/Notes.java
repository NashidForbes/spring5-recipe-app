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
// ----------- << imports@AAAAAAF3mkKjjjyZK7o= >>
// ----------- >>

@Data
@EqualsAndHashCode(exclude = {"recipe"})
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

	//@NotNull
	@Column(nullable = false)
	// ----------- << attribute.annotations@AAAAAAF3mmP6fVRQ3Tc= >>
	// for "large object" data
	@Lob
	// ----------- >>
	private String notes;

	//@NotNull
	@OneToOne(mappedBy = "notes")
	// ----------- << attribute.annotations@AAAAAAF3mlRU/kDONtQ= >>
	// ----------- >>
	private Recipe recipe;
	// ----------- << class.extras@AAAAAAF3mkKjjjyZK7o= >>
// ----------- >>
}