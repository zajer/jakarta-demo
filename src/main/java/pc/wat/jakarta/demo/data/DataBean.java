package pc.wat.jakarta.demo.data;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@RequestScoped
@Transactional
@Named
public class DataBean {
    @PersistenceContext
    EntityManager em;
    
    public void addPerson(Person newPerson){
        em.persist(newPerson);
    }
    
    public void deletePerson(Person existingPerson){
        
    }
    
    public List<Person> getAllPeople() {
        return em
                .createQuery("SELECT p FROM Person p", Person.class)
                .getResultList();
    }
    
    public List<Person> getPeopleByIds(List<Integer> peopleIds) {
        return peopleIds
                .stream()
                .map( personId -> em.find(Person.class, personId) )
                .toList();
    }
    
    public void addLocation(Place newPlace){
        em.persist(newPlace);
    }
    
    public void deleteLocation(Person existingPerson){
        
    }
    
    public List<Place> getAllPlaces(){
        return em
                .createQuery("SELECT p FROM Place p", Place.class)
                .getResultList();
    }
    
    public Place getPlace(PlaceId id){
        return em
                .createQuery("SELECT p FROM Place p WHERE p.city = :pci AND p.street = :pst ", Place.class)
                .setParameter("pci", id.city)
                .setParameter("pst", id.street)
                .getSingleResult();
    }
}
