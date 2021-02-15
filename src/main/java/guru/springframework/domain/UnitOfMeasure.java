/*
* StarUML Autogen
*/
package guru.springframework.domain;

import java.util.*;
import java.time.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;


// ----------- << imports@AAAAAAF3mkJS5zyJlCE= >>
// ----------- >>

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

	// ----------- << getId.annotations@AAAAAAF3mkJS5zyJlCE= >>
	// ----------- >>
	public Long getId(){
		return id;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	// ----------- << equals.annotations@AAAAAAF3mkJS5zyJlCE= >>
	// ----------- >>
	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) return true;
		if (getId() == null) return false;
		return obj instanceof UnitOfMeasure && (getId().equals(((UnitOfMeasure) obj).getId()));
	}

	// ----------- << hashCode.annotations@AAAAAAF3mkJS5zyJlCE= >>
	// ----------- >>
	@Override
	public int hashCode() {
		return 421;
	}

// ----------- << class.extras@AAAAAAF3mkJS5zyJlCE= >>
// ----------- >>
}