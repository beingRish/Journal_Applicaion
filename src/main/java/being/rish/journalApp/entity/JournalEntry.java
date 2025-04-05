package being.rish.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

// Lombok:
// Lombok is a popular library in the Java ecosystem, often used in Spring Boot Applications.
// It aims to reduce the boilerplate code that developers have to write, such as getters, setters, constructors, and more.
// Lombok achieves this by generating this code automatically during compilation, based on annotations you add to your Java classes.

// Lombok generates bytecode for methods like getters, setters, constructors, equals(), hashCode(), and toString(), as specified by thee annotations used in your code.
// This generated code is added to the compiled class files(.class files).

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
}
