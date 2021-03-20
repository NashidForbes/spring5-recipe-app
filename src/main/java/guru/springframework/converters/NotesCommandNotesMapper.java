package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/*Using MapStruct to avoid manual type conversion from example:
https://github.com/springframeworkguru/spring5-recipe-app/tree/creating-command-obj/src/main/java
/guru/springframework/converters*/


@Mapper
public interface NotesCommandNotesMapper {

    NotesCommandNotesMapper INSTANCE = Mappers.getMapper(NotesCommandNotesMapper.class);

    Notes NotesCommandToNotes(NotesCommand notesCommand);

    NotesCommand NotesToNotesCommand(Notes notes);
}
