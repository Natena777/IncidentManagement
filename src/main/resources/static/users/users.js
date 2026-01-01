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
                    id: "userSelectDel",
                    type: "select",
                    label: "Select User",
                    placeholder: "Choose User For Delete"
                }
            ],
            execute: executeDeleteUser,
            onShow: loadUsersForDelete
        },
        AddUserRoles: {
            inputs: [
                {
                    id: 'userSelect',
                    type: 'select',
                    label: 'Select User',
                    placeholder: '-- Choose User --',
                    options: []
                },
                {
                    id: 'roleSelect',
                    type: 'select',
                    label: 'Select Role',
                    placeholder: '-- Choose Role --',
                    options: []
                },
                {
                    id: 'mainRoleCheckbox',
                    type: 'checkbox',
                    label: 'Main Role',
                    checked: false
                }
            ],
            execute: executeAddUserRoles,
            onShow: loadUsersAndRoles
        },
        deleteUserRole: {
            inputs: [
                {
                    id: 'userSelectDelRole',
                    type: 'select',
                    label: 'Select User',
                    placeholder: '-- Choose User --',
                    options: []
                },
                {
                    id: 'roleSelectDelRole',
                    type: 'select',
                    label: 'Select Role',
                    placeholder: '-- Choose Role --',
                    options: []
                }
            ],
            execute: executeDeleteUserRole,
            onShow: loadUsersAndRolesForDelete
        }
    };

    // დააყენეთ კონფიგურაცია
    ioBoxManager.setActionsConfig(actionsConfig);

    // დააკავშირეთ ღილაკები
    ioBoxManager.attachModuleButtons(".module-button");

    // ================= HELPER FUNCTIONS =================

    //Users and Roles Filtration  For Delete
    async function loadUsersAndRolesForDelete() {
        try {
            // Get User Data
            const usersResponse = await AuthService.fetchWithAuth("/api/users", { method: "GET" });
            const users = await usersResponse.json();

            // Get Role Data
            const rolesResponse = await AuthService.fetchWithAuth("/api/role", { method: "GET" });
            const roles = await rolesResponse.json();

            // Get Elements From Action Config
            const userSelect = document.getElementById('userSelectDelRole');
            const roleSelect = document.getElementById('roleSelectDelRole');

            if (!userSelect || !roleSelect) {
                console.error("Selects not found!");
                return;
            }

            // Fill All System Users
            if (Array.isArray(users)) {
                userSelect.innerHTML = '<option value="">-- Choose User --</option>';
                users.forEach(user => {
                    const option = document.createElement('option');
                    option.value = user.id;
                    option.textContent = `${user.firstName} (${user.email})`;
                    userSelect.appendChild(option);
                });
            }

            // Fill Role Select With All Roles
            if (Array.isArray(roles)) {
                roleSelect.innerHTML = '<option value="">-- Choose Role --</option>';
                roles.forEach(role => {
                    const option = document.createElement('option');
                    option.value = role.id;
                    option.textContent = role.name;
                    roleSelect.appendChild(option);
                });
            }

            //Get Role Data When Select User
            userSelect.addEventListener("change", async (e) => {
                const userId = e.target.value;

                if (userId && !roleSelect.value) {
                    try {
                        //Call User Roles
                        const response = await AuthService.fetchWithAuth(`/api/userRole/${userId}/roles`,
                            {method: "GET"});

                        if (!response.ok){
                            throw new Error("Failed to fetch user roles");
                        }
                        //Save Respnose of User Roles Details
                        const data = await response.json();

                        //Clear Options Value
                        roleSelect.innerHTML = '<option value="">-- Choose Role --</option>';

                        //Handle Single Objects and Array List too and Save in roles.
                        const roles = Array.isArray(data) ? data : [data];

                        //Fetch and add Each Role Name in Options
                        for (const roleDetails of roles){
                            try{
                                //Get Role Detail From Java
                                const responseRoleDetail = await AuthService.fetchWithAuth(`/api/role/${roleDetails.roleName}`,
                                {method: "GET"});
                                    
                                if (!responseRoleDetail.ok){
                                    throw new Error("Failed to fetch Role ID");
                                }
                                //Save Role Data From Java
                                const roleData = await responseRoleDetail.json();
                                
                                //Create Option Element and Set Data
                                const option = document.createElement("option");
                                option.value = roleData.id;
                                option.textContent = roleDetails.roleName;
                                roleSelect.appendChild(option);

                            } catch (roleError) {
                                console.error(`Error loading role ${roleDetails.roleName}:`, roleError);
                            }
                        }
                    } catch (error) {
                        console.error("Error loading user roles:", error);
                        alert("Failed to load roles");
                    }

                }
            })

            roleSelect.addEventListener("change", async (e)=>{
                const roleId = e.target.value

                if (roleId && !userSelect.value){
                    try{
                        //Call User Roles
                        const response = await AuthService.fetchWithAuth(`/api/userRole/${roleId}/users`,
                            {method: "GET"});
                        if (!response.ok){
                            throw new Error("Failed to fetch users in role");
                        }
                        //Save Respnose of User Roles Details
                        const data = await response.json();

                        //Clear Options Value
                        userSelect.innerHTML = '<option value="">-- Choose User --</option>';

                        //Handle Single Objects and Array List too and Save in users.
                        const users = Array.isArray(data) ? data : [data];

                        //Fetch and add Each Role Name in Options
                        for (const userDetails of users){
                            try{
                                //Get User Detail From Java
                                const responseUserDetail = await AuthService.fetchWithAuth(`/api/users/id/${userDetails.userId}`,
                                    {method: "GET"});

                                if (!responseUserDetail.ok){
                                    throw new Error("Failed to fetch User ID");
                                }
                                //Save Role Data From Java
                                const userData = await responseUserDetail.json();

                                //Create Option Element and Set Data
                                const option = document.createElement("option");
                                option.value = userData.id;
                                option.textContent = userDetails.userName;
                                userSelect.appendChild(option);

                            } catch (usererror) {
                                console.error(`Error loading role ${userDetails.userName}:`, usererror);
                            }
                        }


                    }catch (error) {
                        console.error("Error loading role Users", error)
                        alert("Failed to load Users")

                    }
                }

            })

        } catch (err) {
            console.error("Error loading users and roles for delete:", err);
            alert("Failed to load users and roles");
        }
    }

    // იუზერების ჩატვირთვა წაშლისთვის
    async function loadUsersForDelete() {
        try {
            const usersResponse = await AuthService.fetchWithAuth("/api/users", { method: "GET" });
            const users = await usersResponse.json();

            const userSelectDel = document.getElementById('userSelectDel');
            if (userSelectDel && Array.isArray(users)) {
                userSelectDel.innerHTML = '<option value="">-- Choose User --</option>';
                users.forEach(user => {
                    const option = document.createElement('option');
                    option.value = user.id;
                    option.textContent = `${user.firstName} (${user.email})`;
                    userSelectDel.appendChild(option);
                });
            }
        } catch (err) {
            console.error("Error loading users:", err);
            alert("Failed to load users");
        }
    }

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
                    option.textContent = `${user.firstName} (${user.email})`;
                    userSelect.appendChild(option);
                });
            }

            // შეავსე role select
            const roleSelect = document.getElementById('roleSelect');
            if (roleSelect && Array.isArray(roles)) {
                roleSelect.innerHTML = '<option value="" disabled selected>-- Choose Role --</option>';
                roles.forEach(role => {
                    const option = document.createElement('option');
                    option.value = role.id;
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
        const userID = document.getElementById("userSelectDel")?.value.trim();
        if (!userID) {
            alert("Please Choose User");
            return;
        }
        if (!confirm(`Are you sure you want to delete user with ID ${userID}?`)) return;

        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth(`/api/users/delete/${userID}`, { method: "DELETE" });
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

    // Execute delete function
    async function executeDeleteUserRole() {
        const userId = document.getElementById("userSelectDelRole")?.value;
        const roleId = document.getElementById("roleSelectDelRole")?.value;

        // CHECK: ორივე უნდა იყოს არჩეული
        if (!userId || !roleId) {
            alert("Please select both User and Role");
            return;
        }

        ioBoxManager.showLoading();

        try {
            // 1. იპოვე user-role ID
            const idResponse = await AuthService.fetchWithAuth(
                `/api/userRole/getID?p_user=${userId}&p_role=${roleId}`,
                { method: "GET" }
            );

            if (!idResponse.ok) {
                throw new Error("Failed to find User-Role ID");
            }

            const userRoleId = await idResponse.json();

            if (!userRoleId) {
                throw new Error("User-Role binding not found");
            }

            // 2. დადასტურება
            if (!confirm(`Are you sure you want to delete this User-Role binding? (ID: ${userRoleId})`)) {
                ioBoxManager.output.innerHTML = "";
                return;
            }

            // 3. წაშალე user-role
            const deleteResponse = await AuthService.fetchWithAuth(
                `/api/userRole/delete/${userRoleId}`,
                { method: "DELETE" }
            );

            if (deleteResponse.ok) {
                const data = await deleteResponse.json();
                ioBoxManager.showSuccess("User-Role deleted successfully!");
                ioBoxManager.renderJSON(data);

                // გაასუფთავე სელექტები
                document.getElementById("userSelectDelRole").value = "";
                document.getElementById("roleSelectDelRole").value = "";

                // თავიდან ჩატვირთე სელექტები
                await loadUsersAndRolesForDelete();
            } else {
                const errorData = await deleteResponse.json();
                throw new Error(errorData.message || "Delete failed");
            }
        } catch (err) {
            console.error("Delete error:", err);
            ioBoxManager.showError(err.message);
        }
    }

});