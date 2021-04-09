/*
* StarUML Autogen
*/
package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
// ----------- << imports@AAAAAAF3mkKjjjyZK7o= >>
// ----------- >>

@Getter
@Setter
// ----------- << class.annotations@AAAAAAF3mkKjjjyZK7o= >>
// ----------- >>
public class Notes implements Serializable {
	@Id
	// ----------- << id.annotations@AAAAAAF3mkKjjjyZK7o= >>
	// ----------- >>
	private String id;

/*	@Version
	// ----------- << version.annotations@AAAAAAF3mkKjjjyZK7o= >>
	// ----------- >>
	private Long version;*/


	// ----------- << attribute.annotations@AAAAAAF3mmP6fVRQ3Tc= >>
	// for "large object" data
	//@Lob
	// ----------- >>
	private String notes;

/*	//@NotNull
	@OneToOne(mappedBy = "notes")
	// ----------- << attribute.annotations@AAAAAAF3mlRU/kDONtQ= >>
	// ----------- >>
	private Recipe recipe;
	// ----------- << class.extras@AAAAAAF3mkKjjjyZK7o= >>*/
// ----------- >>
}