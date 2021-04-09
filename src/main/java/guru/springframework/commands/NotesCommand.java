package guru.springframework.commands;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class NotesCommand {
    private String id;
    private String notes;
}
