document.addEventListener("DOMContentLoaded", () => {
    // გვერდის დაცვა
    AuthService.requireAuth();

    // შექმენით IoBoxManager ინსტანსი
    const ioBoxManager = new IoBoxManager(
        "io-box",
        "inputs", 
        "output",
        "executeBtn",
        "clearBtn",
        "closeBtn"
    );

    // კონფიგურაცია
    const actionsConfig = {
        getAllRoles: {
            inputs: [],
            execute: executeGetAllRoles
        },
        getByRoleName: {
            inputs: [
                {
                    id: "roleNameInput",
                    type: "text",
                    label: "Role Name",
                    placeholder: "Enter Role Name"
                }
            ],
            execute: executeGetRoleByName
        },
        createNewRole: {
            
            inputs: [ 
            {
                id: "RoleNameInputeReg",
                type: "text",
                label: "Role Name",
                placeholder: "Enter Role Name"
            },
            {
                id: "RoleDescriptionReg",
                type: "text",
                label: "Role Description",
                placeholder: "Enter Role Description"
            }
        ],
        execute: executeCreateRole
    },
        deleteRoleByName: {
            inputs: [
                {
                    id: "RoleNameInput",
                    type: "text",
                    label: "Role Name",
                    placeholder: "Enter Role Name"
                }
            ],
            execute: executeDeleteRoleByName
        }
    };

    // დააყენეთ კონფიგურაცია
    ioBoxManager.setActionsConfig(actionsConfig);

    // დააკავშირეთ ღილაკები
    ioBoxManager.attachModuleButtons(".module-button");

    // ================= API FUNCTIONS =================

    async function executeGetAllRoles() {
        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth("/api/role", { method: "GET" });
            const data = await response.json();

            ioBoxManager.output.innerHTML = "";
            renderTable(data);
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeGetRoleByName() {
        const name = document.getElementById("roleNameInput")?.value.trim();
        if (!name) { alert("Please enter Role Name"); return; }

        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth(`/api/role/${name}`, { method: "GET" });
            const data = await response.json();

            ioBoxManager.output.innerHTML = "";
            renderTable(data);
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeDeleteRoleByName() {
        const name = document.getElementById("RoleNameInput")?.value.trim();
        if (!name) { alert("Please enter Role Name"); return; }
        if (!confirm(`Are you sure you want to delete user with Name ${name}?`)) return;

        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth(`/api/role/delete/${name}`, { method: "DELETE" });
            if (response.ok) {
                const data = await response.json();
                ioBoxManager.showSuccess("Success!");
                ioBoxManager.renderJSON(data);
            } else {
                throw new Error("Delete failed");
            }
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeCreateRole() {
    const roleName = document.getElementById("RoleNameInputeReg")?.value.trim();
    const roleDescription = document.getElementById("RoleDescriptionReg")?.value.trim();

    if (!roleName) {
        alert("Please enter Role Name");
        return;
    }

    const payload = {
        roleName: roleName,
        roleDescription: roleDescription
    };

    ioBoxManager.showLoading();

    try {
        const response = await AuthService.fetchWithAuth("/api/role/create", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(payload)
        });

        if (!response.ok) {
            throw new Error("Create role failed");
        }

        const data = await response.json();

        ioBoxManager.showSuccess("Role created successfully!");
        ioBoxManager.renderJSON(data);

    } catch (err) {
        ioBoxManager.showError(err.message);
    }
}

});