document.addEventListener("DOMContentLoaded", () => {
    // გვერდის დაცვა
    AuthService.requireAuth();

    const ioBox = document.getElementById("io-box");
    const inputsDiv = document.getElementById("inputs");
    const output = document.getElementById("output");
    const executeBtn = document.getElementById("executeBtn");
    const clearBtn = document.getElementById("clearBtn");
    const closeBtn = document.getElementById("closeBtn");

    let currentAction = null;

    // კონფიგი თითოეული მოქმედებისთვის
    const actionsConfig = {
        getAllRoles: {
            inputs: [],
            execute: executeGetAllRoles
        },
        getByRoleName: {
            inputs: [{ id: "roleNameInput", placeholder: "Role Name" }],
            execute: executeGetRoleByName
        },
        deleteRoleByName: {
            inputs: [{ id: "RoleNameInput", placeholder: "Role Name" }],
            execute: executeDeleteRoleByName
        }
    };

    // Module button clicks
    document.querySelectorAll(".module-button").forEach(btn => {
        btn.addEventListener("click", () => {
            const actionKey = btn.dataset.action;
            console.log("Action Key: ", actionKey )
            showIoBox(actionKey);
        });
    });

    // Clear button
    clearBtn.addEventListener("click", () => {
        output.innerHTML = "";
    });

    // Close button
    closeBtn.addEventListener("click", () => {
        ioBox.style.display = "none";
        output.innerHTML = "";
    });

    function showIoBox(actionKey) {
        currentAction = actionsConfig[actionKey];
        if (!currentAction) {
            console.error("უცნობი მოქმედება: " + actionKey);
            return;
        }

        ioBox.style.display = "block";
        inputsDiv.innerHTML = "";

        currentAction.inputs.forEach(inp => {
            const input = document.createElement("input");
            input.type = "text";
            input.id = inp.id;
            input.placeholder = inp.placeholder;
            input.style.width = "100%";
            input.style.padding = "12px";
            input.style.marginBottom = "12px";
            input.style.borderRadius = "8px";
            input.style.border = "1px solid #ccc";
            input.style.fontSize = "15px";
            inputsDiv.appendChild(input);
        });

        executeBtn.onclick = currentAction.execute;
        output.innerHTML = "";
    }

    // ================= API FUNCTIONS =================

    async function executeGetAllRoles() {
        output.innerHTML = "<p>Loading...</p>";
        try {
            const response = await AuthService.fetchWithAuth("/api/role", { method: "GET" });
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


    async function executeGetRoleByName() {
        const name = document.getElementById("roleNameInput")?.value.trim();
        if (!name) { alert("Please enter Role Name"); return; }

        output.innerHTML = "<p>Loading...</p>";
        try {
            const response = await AuthService.fetchWithAuth(`/api/role/${name}`, { method: "GET" });
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

    async function executeDeleteRoleByName() {
        const name = document.getElementById("RoleNameInput")?.value.trim();
        if (!name) { alert("Please enter Role Name"); return; }
        if (!confirm(`Are you sure you want to delete user with Name ${name}?`)) return;

        output.innerHTML = "<p>Deleting...</p>";
        try {
            const response = await AuthService.fetchWithAuth(`/api/role/delete/${name}`, { method: "DELETE" });
            if (response.ok) {
                const data = await response.json();
                output.innerHTML = `<p style="color:green;font-weight:bold;">Success!</p>`;
                const pre = document.createElement("pre");
                pre.textContent = JSON.stringify(data, null, 2);
                pre.style.marginTop = "10px";
                output.appendChild(pre);
            } else {
                throw new Error("Delete failed");
            }
        } catch (err) {
            output.innerHTML = `<p style="color:red;font-weight:bold;">Error: ${err.message}</p>`;
        }
    }
});
