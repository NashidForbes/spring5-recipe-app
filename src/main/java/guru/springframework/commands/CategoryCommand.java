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
    private String id;
    private String departmentName;
}
