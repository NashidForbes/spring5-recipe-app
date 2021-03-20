package guru.springframework.commands;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class CategoryCommand {
    private Long id;
    private String departmentName;
}
