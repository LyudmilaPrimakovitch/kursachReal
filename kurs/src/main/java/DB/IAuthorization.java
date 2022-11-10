package DB;

import StorOrg.Authorization;
import StorOrg.Role;

public interface IAuthorization {
    Role getRole(Authorization obj);
}
