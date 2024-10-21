
package pc.wat.jakarta.demo.data;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@RequestScoped
@Named
public class Stats {
    
    @Inject
    DataBean dataAccessor;
    
    public long getNumberOfPeople() {
        return dataAccessor.countPeople();
    }
    
    public long getNumberOfPlaces(){
        return dataAccessor.countPlaces();
    }
    
    public double getAverageNumOfFriends(){
        var people = dataAccessor.getAllPeople();
        double numOfPeople = people.size();
        double numOfFriends = 0;
        for(Person person : people){
            numOfFriends += person.friends.size();
        }
        return numOfFriends/numOfPeople;
    }
    
}
