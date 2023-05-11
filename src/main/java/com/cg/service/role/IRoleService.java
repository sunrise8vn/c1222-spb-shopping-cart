package com.cg.service.role;

import com.cg.model.Role;
import com.cg.model.enums.ERole;
import com.cg.service.IGeneralService;

public interface IRoleService extends IGeneralService<Role, Long> {
    Role findByName(ERole name);
}
