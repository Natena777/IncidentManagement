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
                    id: "groupIdInput",
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
        output.innerHTML = "<p>Loading...</p>";
        try {
            const response = await AuthService.fetchWithAuth("/api/assigneeGroup", { method: "GET" });
            const data = await response.json();
            output.innerHTML = ConverToTable.convert(data);

        } catch (error) {
            output.innerHTML = `<p class="error">Error: ${error.message}</p>`;
        }
    }

    async function executeGetAssigneeGroupsByID() {
        output.innerHTML = "<p>Loading...</p>";
        try {
            const assigneeGroupId = document.getElementById("assigneeGroupIdInput").value;
            const response = await AuthService.fetchWithAuth(`/api/assigneeGroup/${assigneeGroupId}`, { method: "GET" });
            const data = await response.json();
            output.innerHTML = ConverToTable.convert(data);

        } catch (error) {
            output.innerHTML = `<p class="error">Error: ${error.message}</p>`;
        }
    }
    
    async function executeCreateAssigneeGroup() {
        output.innerHTML = "<p>Loading...</p>";
        try {
            const roleId = document.getElementById("roleIdInput").value;
            const response = await AuthService.fetchWithAuth(`/api/assigneeGroup`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ roleId })
            });
            const data = await response.json();
            output.innerHTML = ConverToTable.convert(data);
            
        }
        catch (error) {
            output.innerHTML = `<p class="error">Error: ${error.message}</p>`;
        }


    }


});