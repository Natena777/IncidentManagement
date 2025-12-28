// /js/modules-config.js

// მოდულების უფლებები როლების მიხედვით
const MODULE_PERMISSIONS = {
    users: ['ADMIN', 'CREATOR'],           // CREATOR-საც უნდა შეეძლოს Users-ის მართვა
    roles: ['ADMIN'],                      // მხოლოდ ADMIN
    userroles: ['ADMIN'],                  // მხოლოდ ADMIN (როლების მინიჭება)
    'assignee-groups': ['ADMIN'],
    'case-statuses': ['ADMIN'],
    'service-catalog': ['ADMIN']
};

// მოდულების ვიზუალური ინფორმაცია (იგივე რაც HTML-ში გაქვს)
const MODULE_DETAILS = {
    users: { icon: '👥', title: 'Users', description: 'Manage system users and permissions' },
    roles: { icon: '🔑', title: 'Roles', description: 'Configure user roles and access levels' },
    userroles: { icon: '🔗', title: 'User Roles', description: 'Assign roles to users' },
    'assignee-groups': { icon: '👨‍👩‍👧‍👦', title: 'Assignee Groups', description: 'Manage assignment groups' },
    'case-statuses': { icon: '📊', title: 'Case Statuses', description: 'Configure case status workflows' },
    'service-catalog': { icon: '📋', title: 'Service Catalog', description: 'Manage service catalog items' }
};

//იუზერისგან როლის ამოსაღები ფუნცია
function getUserRoles() {
    const token = localStorage.getItem(CONFIG.APP.TOKEN_KEY); // <--- ეს საუკეთესოა!
    if (!token) {
        console.warn("Token არ არის localStorage-ში (key:", CONFIG.APP.TOKEN_KEY + ")");
        return [];
    }

    try {
        const payloadBase64 = token.split('.')[1];
        const payload = JSON.parse(atob(payloadBase64));

        console.log("%cJWT Payload:", "background: #4CAF50; color: white; padding: 5px;",
                    payload);

        const role = payload.role;

        if (role) {
            console.log("%cიუზერის როლი:", "color: lime; font-weight: bold; font-size: 16px;", role);
            return [role.toUpperCase()];
        } else {
            console.warn("payload-ში 'role' არ არის");
            return [];
        }

    } catch (error) {
        console.error("Decode error:", error);
        return [];
    }
}

// რომელი მოდულებია ნებადართული მიმდინარე მომხმარებლისთვის
function getAllowedModules() {
    const userRoles = getUserRoles();
    return Object.keys(MODULE_PERMISSIONS).filter(moduleName =>
        MODULE_PERMISSIONS[moduleName].some(role => userRoles.includes(role))
    );
}

// კონკრეტულ მოდულზე აქვს თუ არა წვდომა
function canAccessModule(moduleName) {
    const userRoles = getUserRoles();
    const required = MODULE_PERMISSIONS[moduleName];
    return required ? required.some(role => userRoles.includes(role)) : false;
}

window.ModuleConfig = {
    DETAILS: MODULE_DETAILS,
    getAllowedModules: getAllowedModules,
    canAccessModule: canAccessModule,
    goToModule: function(moduleName) {
        console.log("გადამისამართება მოდულზე:", moduleName); // ტესტისთვის

        const routes = {
            users: '/users/users.html',
            roles: '/roles/roles.html',
            userroles: '/userRoles/userroles.html',
            'assignee-groups': '/assigneeGroups/assignee-groups.html',
            'case-statuses': '/caseStatuses/case-statuses.html',
            'service-catalog': '/service_catalog/service-catalog.html'
        };

        const url = routes[moduleName];
        if (url) {
            window.location.href = url;
        } else {
            alert('გვერდი ვერ მოიძებნა: ' + moduleName);
            console.error("უცნობი მოდული:", moduleName);
        }
    }
};