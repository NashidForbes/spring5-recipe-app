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

	@ElementCollection
	// ----------- << attribute.annotations@AAAAAAF3mjzmCTsVE5A= >>
	// ----------- >>
	private Set<String> departmentName = new HashSet<>();

	// ----------- << getId.annotations@AAAAAAF3mjyVLTsRaMk= >>
	// ----------- >>
	public Long getId(){
		return id;
	}

	public Set<String> getDepartmentName() {
		return departmentName;
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