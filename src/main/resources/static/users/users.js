// users.js

document.addEventListener("DOMContentLoaded", () => {
    // გვერდის დაცვა
    AuthService.requireAuth();

    const ioBox = document.getElementById("io-box");
    const inputsDiv = document.getElementById("inputs");
    const output = document.getElementById("output");
    const executeBtn = document.getElementById("executeBtn");

    let currentAction = null; // რომ executeBtn-მა იცოდეს რომელი ფუნქცია გაუშვას

    // კონფიგი თითოეული მოქმედებისთვის
    const actionsConfig = {
        getAll: {
            inputs: [],
            execute: executeGetAllUsers
        },
        getById: {
            inputs: [{ id: "userIdInput", placeholder: "User ID" }],
            execute: executeGetUserById
        },
        getByEmail: {
            inputs: [{ id: "userEmailInput", placeholder: "User Email" }],
            execute: executeGetUserByEmail
        },
        deleteById: {
            inputs: [{ id: "userIdInput", placeholder: "User ID" }],
            execute: executeDeleteUser
        }
    };

    // გლობალური ფუნქციები — HTML-დან onclick-ებით იძახება
    window.showIoBox = function(actionKey) {
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

        // Execute ღილაკზე მივანიჭოთ შესაბამისი ფუნქცია
        executeBtn.onclick = currentAction.execute;

        clearOutput();
    };

    window.hideIoBox = function() {
        ioBox.style.display = "none";
        clearOutput();
    };

    function clearOutput() {
        output.innerHTML = "";
    }

    // =============== API ფუნქციები ===============

    async function executeGetAllUsers() {
        output.innerHTML = "<p>Loading...</p>";

        try {
            const response = await AuthService.fetchWithAuth("/api/users",
                {method: "GET"});
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
            output.innerHTML = `<p style="color: red; font-weight: bold;">Error: ${err.message}</p>`;
        }
    }

    async function executeGetUserById() {
        const id = document.getElementById("userIdInput")?.value.trim();
        if (!id) {
            alert("Please enter User ID");
            return;
        }

        output.innerHTML = "<p>Loading...</p>";

        try {
            const response = await AuthService.fetchWithAuth(`/api/users/id/${id}`,
                {method: "GET"});
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
            output.innerHTML = `<p style="color: red; font-weight: bold;">Error: ${err.message}</p>`;
        }
    }

    async function executeGetUserByEmail() {
        const email = document.getElementById("userEmailInput")?.value.trim();
        if (!email) {
            alert("Please enter User Email");
            return;
        }

        output.innerHTML = "<p>Loading...</p>";

        try {
            const encodedEmail = encodeURIComponent(email);
            const response = await AuthService.fetchWithAuth(`/api/users/email/${encodedEmail}`,
                {method: "GET"});
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
            output.innerHTML = `<p style="color: red; font-weight: bold;">Error: ${err.message}</p>`;
        }
    }

    async function executeDeleteUser() {
        const id = document.getElementById("userIdInput")?.value.trim();
        if (!id) {
            alert("Please enter User ID");
            return;
        }

        if (!confirm(`Are you sure you want to delete user with ID ${id}?`)) {
            return;
        }

        output.innerHTML = "<p>Deleting...</p>";

        try {
            const response = await AuthService.fetchWithAuth(`/api/users/delete/${id}`, {
                method: "DELETE"
            });

            if (response.ok) {
                const data = await response.json();
                output.innerHTML = `<p style="color: green; font-weight: bold;">Success!</p>`;
                const pre = document.createElement("pre");
                pre.textContent = JSON.stringify(data, null, 2);
                pre.style.marginTop = "10px";
                output.appendChild(pre);
            } else {
                throw new Error("Delete failed");
            }
        } catch (err) {
            output.innerHTML = `<p style="color: red; font-weight: bold;">Error: ${err.message}</p>`;
        }
    }
});