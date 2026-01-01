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
        // Full Flow
        getScFullFlow: {
            inputs: [],
            execute: executeGetScFullFlow
        },

        // Department Actions
        getAllScDepartments: {
            inputs: [],
            execute: executeGetAllScDepartments
        },
        getScDepartmentById: {
            inputs: [
                {
                    id: "departmentIdInput",
                    type: "text",
                    label: "Department ID",
                    placeholder: "Enter Department ID"
                }
            ],
            execute: executeGetScDepartmentById
        },
        getScDepartmentByName: {
            inputs: [
                {
                    id: "departmentNameInput",
                    type: "text",
                    label: "Department Name",
                    placeholder: "Enter Department Name"
                }
            ],
            execute: executeGetScDepartmentByName
        },
        createScDepartment: {
            inputs: [
                {
                    id: "newDepartmentName",
                    type: "text",
                    label: "Department Name",
                    placeholder: "Enter Department Name"
                },
                {
                    id: "newDepartmentDescription",
                    type: "textarea",
                    label: "Department Description",
                    placeholder: "Enter Department Description"
                }
            ],
            execute: executeCreateScDepartment
        },
        deleteScDepartment: {
            inputs: [
                {
                    id: "deleteDepartmentId",
                    type: "text",
                    label: "Department ID",
                    placeholder: "Enter Department ID"
                }
            ],
            execute: executeDeleteScDepartment
        },

        // Category Actions
        getAllScCategories: {
            inputs: [],
            execute: executeGetAllScCategories
        },
        getScCategoryById: {
            inputs: [
                {
                    id: "categoryIdInput",
                    type: "text",
                    label: "Category ID",
                    placeholder: "Enter Category ID"
                }
            ],
            execute: executeGetScCategoryById
        },
        getScCategoryByName: {
            inputs: [
                {
                    id: "categoryNameInput",
                    type: "text",
                    label: "Category Name",
                    placeholder: "Enter Category Name"
                }
            ],
            execute: executeGetScCategoryByName
        },
        createScCategory: {
            inputs: [
                {
                    id: "newCategoryName",
                    type: "text",
                    label: "Category Name",
                    placeholder: "Enter Category Name"
                },
                {
                    id: "newCategoryDescription",
                    type: "textarea",
                    label: "Category Description",
                    placeholder: "Enter Category Description"
                },
                {
                    id: "categoryDepartmentId",
                    type: "text",
                    label: "Department ID",
                    placeholder: "Enter Department ID"
                }
            ],
            execute: executeCreateScCategory
        },
        deleteScCategory: {
            inputs: [
                {
                    id: "deleteCategoryId",
                    type: "text",
                    label: "Category ID",
                    placeholder: "Enter Category ID"
                }
            ],
            execute: executeDeleteScCategory
        },

        // SubCategory Actions
        getAllScSubCategories: {
            inputs: [],
            execute: executeGetAllScSubCategories
        },
        getScSubCategoryById: {
            inputs: [
                {
                    id: "subCategoryIdInput",
                    type: "text",
                    label: "SubCategory ID",
                    placeholder: "Enter SubCategory ID"
                }
            ],
            execute: executeGetScSubCategoryById
        },
        getScSubCategoryByName: {
            inputs: [
                {
                    id: "subCategoryNameInput",
                    type: "text",
                    label: "SubCategory Name",
                    placeholder: "Enter SubCategory Name"
                }
            ],
            execute: executeGetScSubCategoryByName
        },
        createScSubCategory: {
            inputs: [
                {
                    id: "newSubCategoryName",
                    type: "text",
                    label: "SubCategory Name",
                    placeholder: "Enter SubCategory Name"
                },
                {
                    id: "newSubCategoryDescription",
                    type: "textarea",
                    label: "SubCategory Description",
                    placeholder: "Enter SubCategory Description"
                },
                {
                    id: "subCategoryCategoryId",
                    type: "text",
                    label: "Category ID",
                    placeholder: "Enter Category ID"
                }
            ],
            execute: executeCreateScSubCategory
        },
        deleteScSubCategory: {
            inputs: [
                {
                    id: "deleteSubCategoryId",
                    type: "text",
                    label: "SubCategory ID",
                    placeholder: "Enter SubCategory ID"
                }
            ],
            execute: executeDeleteScSubCategory
        },

        // Services Actions
        getAllScServices: {
            inputs: [],
            execute: executeGetAllScServices
        },
        getScServicesById: {
            inputs: [
                {
                    id: "servicesIdInput",
                    type: "text",
                    label: "Service ID",
                    placeholder: "Enter Service ID"
                }
            ],
            execute: executeGetScServicesById
        },
        getScServicesByName: {
            inputs: [
                {
                    id: "servicesNameInput",
                    type: "text",
                    label: "Service Name",
                    placeholder: "Enter Service Name"
                }
            ],
            execute: executeGetScServicesByName
        },
        createScServices: {
            inputs: [
                {
                    id: "newServicesName",
                    type: "text",
                    label: "Service Name",
                    placeholder: "Enter Service Name"
                },
                {
                    id: "newServicesDescription",
                    type: "textarea",
                    label: "Service Description",
                    placeholder: "Enter Service Description"
                },
                {
                    id: "servicesDepartmentId",
                    type: "text",
                    label: "Department ID",
                    placeholder: "Enter Department ID"
                },
                {
                    id: "servicesCategoryId",
                    type: "text",
                    label: "Category ID",
                    placeholder: "Enter Category ID"
                },
                {
                    id: "servicesSubCategoryId",
                    type: "text",
                    label: "SubCategory ID",
                    placeholder: "Enter SubCategory ID"
                },
                {
                    id: "servicesStatus",
                    type: "text",
                    label: "Status",
                    placeholder: "Enter Status"
                },
                {
                    id: "servicesServiceType",
                    type: "text",
                    label: "Service Type",
                    placeholder: "Enter Service Type"
                },
                {
                    id: "servicesAssigneeGroupId",
                    type: "text",
                    label: "Assignee Group ID",
                    placeholder: "Enter Assignee Group ID"
                },
                {
                    id: "servicesResponseTimeType",
                    type: "text",
                    label: "Response Time Type",
                    placeholder: "Enter Response Time Type"
                },
                {
                    id: "servicesResponseTimeValue",
                    type: "text",
                    label: "Response Time Value",
                    placeholder: "Enter Response Time Value"
                },
                {
                    id: "servicesResolutionTimeType",
                    type: "text",
                    label: "Resolution Time Type",
                    placeholder: "Enter Resolution Time Type"
                },
                {
                    id: "servicesResolutionValue",
                    type: "text",
                    label: "Resolution Value",
                    placeholder: "Enter Resolution Value"
                }
            ],
            execute: executeCreateScServices
        },
        deleteScServices: {
            inputs: [
                {
                    id: "deleteServicesId",
                    type: "text",
                    label: "Service ID",
                    placeholder: "Enter Service ID"
                }
            ],
            execute: executeDeleteScServices
        }
    };

    // დააყენეთ კონფიგურაცია
    ioBoxManager.setActionsConfig(actionsConfig);

    // დააკავშირეთ ღილაკები
    ioBoxManager.attachModuleButtons(".module-button");

    // ================= API FUNCTIONS =================

    // Full Flow
    async function executeGetScFullFlow() {
        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth("/api/servicesCatalog/scFullFlow", { method: "GET" });
            const data = await response.json();

            ioBoxManager.output.innerHTML = "";
            renderTable(data);
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    // Department Functions
    async function executeGetAllScDepartments() {
        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth("/api/servicesCatalog/scDepartment", { method: "GET" });
            const data = await response.json();

            ioBoxManager.output.innerHTML = "";
            renderTable(data);
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeGetScDepartmentById() {
        const id = document.getElementById("departmentIdInput")?.value.trim();
        if (!id) {
            alert("Please enter Department ID");
            return;
        }

        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth(`/api/servicesCatalog/scDepartment/${id}`, { method: "GET" });
            const data = await response.json();

            ioBoxManager.output.innerHTML = "";
            renderTable(data);
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeGetScDepartmentByName() {
        const name = document.getElementById("departmentNameInput")?.value.trim();
        if (!name) {
            alert("Please enter Department Name");
            return;
        }

        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth(`/api/servicesCatalog/scDepartment/name/${name}`, { method: "GET" });
            const data = await response.json();

            ioBoxManager.output.innerHTML = "";
            renderTable(data);
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeCreateScDepartment() {
        const name = document.getElementById("newDepartmentName")?.value.trim();
        const description = document.getElementById("newDepartmentDescription")?.value.trim();

        if (!name) {
            alert("Please enter Department Name");
            return;
        }

        const payload = {
            departmentName: name,
            description: description
        };

        ioBoxManager.showLoading();

        try {
            const response = await AuthService.fetchWithAuth("/api/servicesCatalog/scDepartment", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(payload)
            });

            if (!response.ok) {
                throw new Error("Create Department failed");
            }

            const data = await response.json();
            ioBoxManager.showSuccess("Department created successfully!");
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeDeleteScDepartment() {
        const id = document.getElementById("deleteDepartmentId")?.value.trim();

        if (!id) {
            alert("Please Enter Department ID");
            return;
        }

        if (!confirm(`Are You Sure You Want To Delete Department With ID ${id}?`)) {
            return;
        }

        ioBoxManager.showLoading();

        try {
            const response = await AuthService.fetchWithAuth(`/api/servicesCatalog/scDepartment/delete/${id}`, { method: "DELETE" });
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

    // Category Functions
    async function executeGetAllScCategories() {
        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth("/api/servicesCatalog/scCategory", { method: "GET" });
            const data = await response.json();

            ioBoxManager.output.innerHTML = "";
            renderTable(data);
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeGetScCategoryById() {
        const id = document.getElementById("categoryIdInput")?.value.trim();
        if (!id) {
            alert("Please enter Category ID");
            return;
        }

        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth(`/api/servicesCatalog/scCategory/${id}`, { method: "GET" });
            const data = await response.json();

            ioBoxManager.output.innerHTML = "";
            renderTable(data);
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeGetScCategoryByName() {
        const name = document.getElementById("categoryNameInput")?.value.trim();
        if (!name) {
            alert("Please enter Category Name");
            return;
        }

        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth(`/api/servicesCatalog/scCategory/name/${name}`, { method: "GET" });
            const data = await response.json();

            ioBoxManager.output.innerHTML = "";
            renderTable(data);
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeCreateScCategory() {
        const name = document.getElementById("newCategoryName")?.value.trim();
        const description = document.getElementById("newCategoryDescription")?.value.trim();
        const departmentId = document.getElementById("categoryDepartmentId")?.value.trim();

        if (!name) {
            alert("Please enter Category Name");
            return;
        }

        if (!departmentId) {
            alert("Please enter Department ID");
            return;
        }

        const payload = {
            scCategoryName: name,
            description: description,
            scDepartmentId: parseInt(departmentId)
        };

        ioBoxManager.showLoading();

        try {
            const response = await AuthService.fetchWithAuth("/api/servicesCatalog/scCategory", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(payload)
            });

            if (!response.ok) {
                throw new Error("Create Category failed");
            }

            const data = await response.json();
            ioBoxManager.showSuccess("Category created successfully!");
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeDeleteScCategory() {
        const id = document.getElementById("deleteCategoryId")?.value.trim();

        if (!id) {
            alert("Please Enter Category ID");
            return;
        }

        if (!confirm(`Are You Sure You Want To Delete Category With ID ${id}?`)) {
            return;
        }

        ioBoxManager.showLoading();

        try {
            const response = await AuthService.fetchWithAuth(`/api/servicesCatalog/scCategory/delete/${id}`, { method: "DELETE" });
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

    // SubCategory Functions
    async function executeGetAllScSubCategories() {
        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth("/api/servicesCatalog/scSubCategory", { method: "GET" });
            const data = await response.json();

            ioBoxManager.output.innerHTML = "";
            renderTable(data);
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeGetScSubCategoryById() {
        const id = document.getElementById("subCategoryIdInput")?.value.trim();
        if (!id) {
            alert("Please enter SubCategory ID");
            return;
        }

        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth(`/api/servicesCatalog/scSubCategory/${id}`, { method: "GET" });
            const data = await response.json();

            ioBoxManager.output.innerHTML = "";
            renderTable(data);
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeGetScSubCategoryByName() {
        const name = document.getElementById("subCategoryNameInput")?.value.trim();
        if (!name) {
            alert("Please enter SubCategory Name");
            return;
        }

        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth(`/api/servicesCatalog/scSubCategory/name/${name}`, { method: "GET" });
            const data = await response.json();

            ioBoxManager.output.innerHTML = "";
            renderTable(data);
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeCreateScSubCategory() {
        const name = document.getElementById("newSubCategoryName")?.value.trim();
        const description = document.getElementById("newSubCategoryDescription")?.value.trim();
        const categoryId = document.getElementById("subCategoryCategoryId")?.value.trim();

        if (!name) {
            alert("Please enter SubCategory Name");
            return;
        }

        if (!categoryId) {
            alert("Please enter Category ID");
            return;
        }

        const payload = {
            scSubCategoryName: name,
            description: description,
            scCategoryId: parseInt(categoryId)
        };

        ioBoxManager.showLoading();

        try {
            const response = await AuthService.fetchWithAuth("/api/servicesCatalog/scSubCategory", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(payload)
            });

            if (!response.ok) {
                throw new Error("Create SubCategory failed");
            }

            const data = await response.json();
            ioBoxManager.showSuccess("SubCategory created successfully!");
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeDeleteScSubCategory() {
        const id = document.getElementById("deleteSubCategoryId")?.value.trim();

        if (!id) {
            alert("Please Enter SubCategory ID");
            return;
        }

        if (!confirm(`Are You Sure You Want To Delete SubCategory With ID ${id}?`)) {
            return;
        }

        ioBoxManager.showLoading();

        try {
            const response = await AuthService.fetchWithAuth(`/api/servicesCatalog/scSubCategory/delete/${id}`, { method: "DELETE" });
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

    // Services Functions
    async function executeGetAllScServices() {
        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth("/api/servicesCatalog/scServices", { method: "GET" });
            const data = await response.json();

            ioBoxManager.output.innerHTML = "";
            renderTable(data);
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeGetScServicesById() {
        const id = document.getElementById("servicesIdInput")?.value.trim();
        if (!id) {
            alert("Please enter Service ID");
            return;
        }

        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth(`/api/servicesCatalog/scServices/${id}`, { method: "GET" });
            const data = await response.json();

            ioBoxManager.output.innerHTML = "";
            renderTable(data);
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeGetScServicesByName() {
        const name = document.getElementById("servicesNameInput")?.value.trim();
        if (!name) {
            alert("Please enter Service Name");
            return;
        }

        ioBoxManager.showLoading();
        try {
            const response = await AuthService.fetchWithAuth(`/api/servicesCatalog/scServices/name/${name}`, { method: "GET" });
            const data = await response.json();

            ioBoxManager.output.innerHTML = "";
            renderTable(data);
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeCreateScServices() {
        const name = document.getElementById("newServicesName")?.value.trim();
        const description = document.getElementById("newServicesDescription")?.value.trim();
        const departmentId = document.getElementById("servicesDepartmentId")?.value.trim();
        const categoryId = document.getElementById("servicesCategoryId")?.value.trim();
        const subCategoryId = document.getElementById("servicesSubCategoryId")?.value.trim();
        const status = document.getElementById("servicesStatus")?.value.trim();
        const serviceType = document.getElementById("servicesServiceType")?.value.trim();
        const assigneeGroupId = document.getElementById("servicesAssigneeGroupId")?.value.trim();
        const responseTimeType = document.getElementById("servicesResponseTimeType")?.value.trim();
        const responseTimeValue = document.getElementById("servicesResponseTimeValue")?.value.trim();
        const resolutionTimeType = document.getElementById("servicesResolutionTimeType")?.value.trim();
        const resolutionValue = document.getElementById("servicesResolutionValue")?.value.trim();

        if (!name) {
            alert("Please enter Service Name");
            return;
        }

        const payload = {
            servicesName: name,
            description: description,
            scDepartmentId: departmentId ? parseInt(departmentId) : null,
            scCategoryId: categoryId ? parseInt(categoryId) : null,
            scSubCategoryId: subCategoryId ? parseInt(subCategoryId) : null,
            status: status || null,
            serviceType: serviceType || null,
            assigneeGroupId: assigneeGroupId ? parseInt(assigneeGroupId) : null,
            responseTimeType: responseTimeType || null,
            responseTimeValue: responseTimeValue ? parseInt(responseTimeValue) : null,
            resolutionTimeType: resolutionTimeType || null,
            resolutionValue: resolutionValue ? parseInt(resolutionValue) : null
        };

        ioBoxManager.showLoading();

        try {
            const response = await AuthService.fetchWithAuth("/api/servicesCatalog/scServices", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(payload)
            });

            if (!response.ok) {
                throw new Error("Create Service failed");
            }

            const data = await response.json();
            ioBoxManager.showSuccess("Service created successfully!");
            ioBoxManager.renderJSON(data);
        } catch (err) {
            ioBoxManager.showError(err.message);
        }
    }

    async function executeDeleteScServices() {
        const id = document.getElementById("deleteServicesId")?.value.trim();

        if (!id) {
            alert("Please Enter Service ID");
            return;
        }

        if (!confirm(`Are You Sure You Want To Delete Service With ID ${id}?`)) {
            return;
        }

        ioBoxManager.showLoading();

        try {
            const response = await AuthService.fetchWithAuth(`/api/servicesCatalog/scServices/delete/${id}`, { method: "DELETE" });
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

});