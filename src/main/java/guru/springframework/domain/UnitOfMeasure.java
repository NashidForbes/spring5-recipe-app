/*
* StarUML Autogen
*/
package guru.springframework.domain;

import lombok.*;

import java.util.*;
import java.time.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;


// ----------- << imports@AAAAAAF3mkJS5zyJlCE= >>
// ----------- >>

@Data
@Entity
// ----------- << class.annotations@AAAAAAF3mkJS5zyJlCE= >>
// ----------- >>
public class UnitOfMeasure implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// ----------- << id.annotations@AAAAAAF3mkJS5zyJlCE= >>
	// ----------- >>
	private Long id;

	@Version
	// ----------- << version.annotations@AAAAAAF3mkJS5zyJlCE= >>
	// ----------- >>
	private Long version;

	@NotNull
	@Column(nullable = false)
	// ----------- << attribute.annotations@AAAAAAF3mmNgqlL9irs= >>
	// ----------- >>
	private String units;
	// ----------- << class.extras@AAAAAAF3mkJS5zyJlCE= >>
// ----------- >>
}