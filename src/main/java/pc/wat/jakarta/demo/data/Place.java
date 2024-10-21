
package pc.wat.jakarta.demo.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@IdClass(PlaceId.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Place {
    @Id String city;
    @Id String street;
    
    @JsonBackReference
    @OneToMany(mappedBy = "location")
    List<Person> peopleAtLocation;
}
