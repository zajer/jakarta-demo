
package pc.wat.jakarta.demo.data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    String firstName;
    String lastName;
    @Id int identificationNumber;
    
    @ManyToMany
    List<Person> friends;
    
    @ManyToOne
    @NotNull
    Place location;
}
