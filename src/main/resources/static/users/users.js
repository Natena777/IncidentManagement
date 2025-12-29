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

    // კონფიგი თითოეული მოქმედებისთვის
    const actionsConfig = {
        getAll: {
            inputs: [],
            execute: executeGetAllUsers
        },
        getById: {
            inputs: [
                { 
                    id: "userIdInput", 
                    type: "text",
                    label: "User ID",
                    placeholder: "Enter User ID" 
                }
            ],
            execute: executeGetUserById
        },
        getByEmail: {
            inputs: [
                { 
                    id: "userEmailInput", 
                    type: "email",
                    label: "User Email",
                    placeholder: "Enter User Email" 
                }
            ],
            execute: executeGetUserByEmail
        },
        deleteById: {
            inputs: [
                { 
                    id: "userIdInput", 
                    type: "text",
                    label: "User ID",
                    placeholder: "Enter User ID to Delete" 
                }
            ],
            execute: executeDeleteUser
        },
        AddUserRoles: {
            inputs: [
                { 
                    id: 'userSelect', 
                    type: 'select',
                    label: 'Select User',
                    placeholder: '-- Choose User --',
                    options: [] // ცარიელი, დინამიურად შეივსება
                },
                { 
                    id: 'roleSelect', 
                    type: 'select',
                    label: 'Select Role',
                    placeholder: '-- Choose Role --',
                    options: [] // ცარიელი, დინამიურად შეივსება
                }
            ],
            execute: executeAddUserRoles,
            onShow: loadUsersAndRoles // როცა ბოქსი გაიხსნება
        }
    };

    // დააყენეთ კონფიგურაცია
    ioBoxManager.setActionsConfig(actionsConfig);

    // დააკავშირეთ ღილაკები
    ioBoxManager.attachModuleButtons(".module-button");

    // ================= HELPER FUNCTIONS =================

    // დინამიურად ჩატვირთოს იუზერები და როლები
    async function loadUsersAndRoles() {
        try {
            // ჩატვირთე იუზერები
            const usersResponse = await AuthService.fetchWithAuth("/api/users", { method: "GET" });
            const users = await usersResponse.json();

            // ჩატვირთე როლები
            const rolesResponse = await AuthService.fetchWithAuth("/api/role", { method: "GET" });
            const roles = await rolesResponse.json();

            // შეავსე user select
            const userSelect = document.getElementById('userSelect');
            if (userSelect && Array.isArray(users)) {
                userSelect.innerHTML = '<option value="" disabled selected>-- Choose User --</option>';
                users.forEach(user => {
                    const option = document.createElement('option');
                    option.value = user.id;
                    option.textContent = `${user.name} (${user.email})`;
                    userSelect.appendChild(option);
                });
            }

            // შეავსე role select
            const roleSelect = document.getElementById('roleSelect');
            if (roleSelect && Array.isArray(roles)) {
                roleSelect.innerHTML = '<option value="" disabled selected>-- Choose Role --</option>';
                roles.forEach(role => {
                    const option = document.createElement('option');
                    option.value = role.id || role.name;
                    option.textContent = role.name;
                    roleSelect.appendChild(option);
                });
            }
        } catch (err) {
            console.error("Error loading users and roles:", err);
            alert("Failed to load users and roles");
        }
    }

    // ================= API FUNCTIONS =================

    async function executeGetAllUsers() {
        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth("/api/users", { method: "GET" });
            const data = await response.json();

            ioBoxManager.output.innerHTML = "";
            renderTable(data);
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeGetUserById() {
        const id = document.getElementById("userIdInput")?.value.trim();
        if (!id) { 
            alert("Please enter User ID"); 
            return; 
        }

        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth(`/api/users/id/${id}`, { method: "GET" });
            const data = await response.json();

            ioBoxManager.output.innerHTML = "";
            renderTable(data);
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeGetUserByEmail() {
        const email = document.getElementById("userEmailInput")?.value.trim();
        if (!email) { 
            alert("Please enter User Email"); 
            return; 
        }

        ioBoxManager.showLoading();
        try {
            const encodedEmail = encodeURIComponent(email);
            const response = await AuthService.fetchWithAuth(`/api/users/email/${encodedEmail}`, { method: "GET" });
            const data = await response.json();

            ioBoxManager.output.innerHTML = "";
            renderTable(data);
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeDeleteUser() {
        const id = document.getElementById("userIdInput")?.value.trim();
        if (!id) { 
            alert("Please enter User ID"); 
            return; 
        }
        if (!confirm(`Are you sure you want to delete user with ID ${id}?`)) return;

        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth(`/api/users/delete/${id}`, { method: "DELETE" });
            if (response.ok) {
                const data = await response.json();
                ioBoxManager.showSuccess("User deleted successfully!");
                ioBoxManager.renderJSON(data);
            } else {
                throw new Error("Delete failed");
            }
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeAddUserRoles() {
        const userId = document.getElementById("userSelect")?.value;
        const roleId = document.getElementById("roleSelect")?.value;
        const mainRoleCheckbox = document.getElementById("mainRoleCheckbox");
        
        const isMainRole = mainRoleCheckbox ? mainRoleCheckbox.checked : false;


        if (!userId || !roleId) {
            alert("Please select both User and Role");
            return;
        }

        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth("/api/userRole/create", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    userId: userId,
                    roleId: roleId,
                    mainRole: isMainRole
                })
            });

            if (response.ok) {
                const data = await response.json();
                ioBoxManager.showSuccess("Role assigned successfully!");
                ioBoxManager.renderJSON(data);
            } else {
                throw new Error("Failed to assign role");
            }
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }
});