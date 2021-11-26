package nl.fhict.s6.libraryrest.authentication.http;

import nl.fhict.s6.libraryrest.datamodels.BasePermission;
import nl.fhict.s6.libraryrest.helper.LongHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.ArrayList;

public class PermissionHttpHeader extends HttpServletRequestWrapper {
    private BasePermission basePermission;
    public PermissionHttpHeader(HttpServletRequest request) {
        super(request);
        System.out.println("user_id: "+request.getHeader("User-Id"));
        Long userId = new LongHelper().parseLongOrDefaultValue(request.getHeader("User-Id"),-1L);
        String subject = request.getHeader("Subject") == null ? "": request.getHeader("Subject");
        basePermission = new BasePermission(userId,subject,new ArrayList<>());
    }

    public BasePermission getBasePermission() {
        return basePermission;
    }
}
