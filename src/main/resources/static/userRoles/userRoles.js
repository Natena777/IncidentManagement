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

    let currentAction = null;

    // კონფიგი თითოეული მოქმედებისთვის
    const actionsConfig = {
        getAllUserRoles: {
            inputs: [],
            execute: executeGetAllUserRoles
        },
        getUserRolesByUserID: {
            inputs: [
                {
                    id: "userIdInput",
                    type: "text",
                    label: "User ID",
                    placeholder: "Enter User ID"
                }
            ],
            execute: executeGetUserRolesByUserID
        },
        getUserRolesByRoleID:{
            inputs: [
                {
                    id: "roleIdInput",
                    type: "text",
                    label: "Role ID",
                    placeholder: "Enter Role ID"
                }
            ],
            execute: executeGetUserRolesByRoleID
        }
    };

    // დააყენეთ კონფიგურაცია
    ioBoxManager.setActionsConfig(actionsConfig);

    // დააკავშირეთ ღილაკები
    ioBoxManager.attachModuleButtons(".module-button");

    // ================= API FUNCTIONS =================

    async function executeGetAllUserRoles() {
        output.innerHTML = "<p>Loading...</p>";
        try {
            const response = await AuthService.fetchWithAuth("/api/userRole", { method: "GET" });
            const data = await response.json();

            output.innerHTML = "";
            renderTable(data);

            const pre = document.createElement("pre");
            pre.style.marginTop = "20px";
            pre.style.background = "#f8f8f8";
            pre.style.padding = "15px";
            pre.style.borderRadius = "8px";
            pre.style.border = "1px solid #ddd";
            pre.textContent = JSON.stringify(data, null, 2);
            output.appendChild(pre);
        } catch (err) {
            output.innerHTML = `<p style="color:red;font-weight:bold;">Error: ${err.message}</p>`;
        }
    }

    async function executeGetUserRolesByUserID() {
        const userid = document.getElementById("userIdInput")?.value.trim();
        if (!userid) { alert("Please enter User ID"); return; }

        output.innerHTML = "<p>Loading Roles...</p>";
        try {
            const response = await AuthService.fetchWithAuth(`/api/userRole/${userid}`, { method: "GET" });
            const data = await response.json();

            output.innerHTML = "";
            renderTable(data);

            const pre = document.createElement("pre");
            pre.style.marginTop = "20px";
            pre.style.background = "#f8f8f8";
            pre.style.padding = "15px";
            pre.style.borderRadius = "8px";
            pre.style.border = "1px solid #ddd";
            pre.textContent = JSON.stringify(data, null, 2);
            output.appendChild(pre);
        } catch (err) {
            output.innerHTML = `<p style="color:red;font-weight:bold;">Error: ${err.message}</p>`;
        }
    }

    async function executeGetUserRolesByRoleID() {
        const roleID = document.getElementById("roleIdInput")?.value.trim();
        if (!roleID) { alert("Please enter Role ID"); return; }

        output.innerHTML = "<p>Loading Users...</p>";
        try {
            const response = await AuthService.fetchWithAuth(`/api/userRole/id/${roleID}`, { method: "GET" });
            const data = await response.json();

            output.innerHTML = "";
            renderTable(data);

            const pre = document.createElement("pre");
            pre.style.marginTop = "20px";
            pre.style.background = "#f8f8f8";
            pre.style.padding = "15px";
            pre.style.borderRadius = "8px";
            pre.style.border = "1px solid #ddd";
            pre.textContent = JSON.stringify(data, null, 2);
            output.appendChild(pre);
        } catch (err) {
            output.innerHTML = `<p style="color:red;font-weight:bold;">Error: ${err.message}</p>`;
        }
    }

});
