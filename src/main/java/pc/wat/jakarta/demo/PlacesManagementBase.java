
package pc.wat.jakarta.demo;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServlet;
import pc.wat.jakarta.demo.data.DataBean;


public class PlacesManagementBase extends HttpServlet {
    @Inject
    DataBean dataAccessor;
    
    protected static final String FIRST_NAME_PARAM_NAME = "city";
    protected static final String SECOND_NAME_PARAM_NAME = "street";
    
}
