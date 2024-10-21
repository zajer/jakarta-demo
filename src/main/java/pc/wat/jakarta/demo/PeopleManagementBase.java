
package pc.wat.jakarta.demo;

import jakarta.inject.Inject;
import pc.wat.jakarta.demo.data.DataBean;

public class PeopleManagementBase extends CommonServlet {
    protected static final String FIRST_NAME_PARAM_NAME = "fname";
    protected static final String SECOND_NAME_PARAM_NAME = "sname";
    protected static final String PERSON_IDENTIFICATION_NUMBER_PARAM_NAME = "pid";
    protected static final String FRIENDS_IDENTIFICATION_NUMBERS_PARAM_NAME = "fids";
    protected static final String CITY_PARAM_NAME = "city";
    protected static final String STREET_NAME_PARAM_NAME = "street";
    
    @Inject
    protected DataBean dataAccessor;
}
