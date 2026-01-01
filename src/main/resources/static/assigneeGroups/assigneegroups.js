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
        getAllAssigneeGroups: {
            inputs: [],
            execute: executeGetAllAssigneeGroups
        },
        getAssigneeGroupsByID: {
            inputs: [
                {
                    id: "assigneeGroupIdInput",
                    type: "text",
                    label: "Assignee Group ID",
                    placeholder: "Enter Assignee Group ID"
                }
            ],
            execute: executeGetAssigneeGroupsByID
        },
        createAssigneeGroup:{
            inputs: [
                {
                    id: "groupNameInput",
                    type: "text",
                    label: "Group Name",
                    placeholder: "Enter Group Name"
                },
                {
                    id: "groupDescriptionInput",
                    type: "text",
                    label: "Group Description",
                    placeholder: "Enter Group Description"
                }
            ],
            execute: executeCreateAssigneeGroup
        }
    };

    // დააყენეთ კონფიგურაცია
    ioBoxManager.setActionsConfig(actionsConfig);

    // დააკავშირეთ ღილაკები
    ioBoxManager.attachModuleButtons(".module-button");


    // ================= API FUNCTIONS =================

    async function executeGetAllAssigneeGroups() {
        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth("/api/assigneeGroup", { method: "GET" });
            const data = await response.json();

            ioBoxManager.output.innerHTML = "";
            renderTable(data);
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeGetAssigneeGroupsByID() {
        const id = document.getElementById("assigneeGroupIdInput")?.value.trim();
        if (!id) {
            alert("Please enter Assignee Group ID");
            return;
        }

        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth(`/api/assigneeGroup/id/${id}`, { method: "GET" });
            const data = await response.json();

            ioBoxManager.output.innerHTML = "";
            renderTable(data);
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeCreateAssigneeGroup() {
    const assigneeGroupName = document.getElementById("groupNameInput")?.value.trim();
    const assigneeGroupNameDescription = document.getElementById("groupDescriptionInput")?.value.trim();

    if (!roleName) {
        alert("Please enter Assignee Group Name");
        return;
    }

    const payload = {
        groupName: assigneeGroupName,
        groupDescription: assigneeGroupNameDescription
    };

    ioBoxManager.showLoading();

    try {
        const response = await AuthService.fetchWithAuth("/api/assigneeGroup/create", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(payload)
        });

        if (!response.ok) {
            throw new Error("Create Assignee Group failed");
        }

        const data = await response.json();

        ioBoxManager.showSuccess("Assignee Group created successfully!");
        ioBoxManager.renderJSON(data);

    } catch (err) {
        ioBoxManager.showError(err.message);
    }
}

});