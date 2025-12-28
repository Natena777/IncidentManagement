function renderTable(data) {

    // --- null / undefined ---
    if (!data) {
        output.innerHTML += "<p>No data found</p>";
        return;
    }

    // --- Object → Array ---
    const rows = Array.isArray(data) ? data : [data];

    if (rows.length === 0) {
        output.innerHTML += "<p>No data found</p>";
        return;
    }

    let table = document.createElement("table");
    table.style.width = "100%";
    table.style.borderCollapse = "collapse";

    // ---- HEADER ----
    let thead = document.createElement("thead");
    let headerRow = document.createElement("tr");

    Object.keys(rows[0]).forEach(key => {
        let th = document.createElement("th");
        th.innerText = key;
        th.style.border = "1px solid #ccc";
        th.style.padding = "8px";
        th.style.background = "#f4f4f4";
        headerRow.appendChild(th);
    });

    thead.appendChild(headerRow);
    table.appendChild(thead);

    // ---- BODY ----
    let tbody = document.createElement("tbody");

    rows.forEach(row => {
        let tr = document.createElement("tr");

        Object.values(row).forEach(value => {
            let td = document.createElement("td");
            td.innerText = value ?? "";   // null-safe
            td.style.border = "1px solid #ccc";
            td.style.padding = "8px";
            tr.appendChild(td);
        });

        tbody.appendChild(tr);
    });

    table.appendChild(tbody);

    output.appendChild(table);
}
