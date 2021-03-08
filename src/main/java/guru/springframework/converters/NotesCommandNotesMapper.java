package guru.springframework.converters;

import guru.springframework.command.NotesCommand;
import guru.springframework.domain.Notes;
import org.mapstruct.Mapper;

/*Using MapStruct to avoid manual type conversion from example:
https://github.com/springframeworkguru/spring5-recipe-app/tree/creating-command-obj/src/main/java
/guru/springframework/converters*/


@Mapper
public interface NotesCommandNotesMapper {
    Notes NotesCommandToNotes(NotesCommand notesCommand);

    NotesCommand NotesToNotesCommand(Notes notes);
}