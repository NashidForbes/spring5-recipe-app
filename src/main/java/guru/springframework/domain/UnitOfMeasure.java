/*
* StarUML Autogen
*/
package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;


// ----------- << imports@AAAAAAF3mkJS5zyJlCE= >>
// ----------- >>

@Getter
@Setter
@Document
// ----------- << class.annotations@AAAAAAF3mkJS5zyJlCE= >>
// ----------- >>
public class UnitOfMeasure implements Serializable {
	@Id
	// ----------- << id.annotations@AAAAAAF3mkJS5zyJlCE= >>
	// ----------- >>
	private String id;

/*	@Version
	// ----------- << version.annotations@AAAAAAF3mkJS5zyJlCE= >>
	// ----------- >>
	private Long version;*/

	// ----------- << attribute.annotations@AAAAAAF3mmNgqlL9irs= >>
	// ----------- >>
	private String units;
	// ----------- << class.extras@AAAAAAF3mkJS5zyJlCE= >>
// ----------- >>
}