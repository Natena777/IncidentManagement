// === ტოკენის დიაგნოსტიკა ===
console.log("%cToken Check on Main Page", "background: #2196F3; color: white; font-size: 16px; padding: 10px;");

AuthService.requireAuth();

function renderModules() {
    if (!window.ModuleConfig) {
        console.error("ModuleConfig არ იტვირთება! შეამოწმეთ script-ების თანმიმდევრობა.");
        return;
    }

    const allowedModules = window.ModuleConfig.getAllowedModules();
    console.log("ნებადართული მოდულები:", allowedModules);

    const grid = document.querySelector('.modules-grid');
    grid.innerHTML = '';

    if (allowedModules.length === 0) {
        grid.innerHTML = '<p style="grid-column: 1/-1; text-align: center; color: white; font-size: 30px; padding: 60px;">თქვენთვის ხელმისაწვდომი მოდული არ არის.<br>შეამოწმეთ console (F12).</p>';
        return;
    }

    allowedModules.forEach(moduleName => {
        const details = window.ModuleConfig.DETAILS[moduleName];
        if (!details) return;

        const card = document.createElement('div');
        card.className = 'module-card';

        // === აქ არის გამოსწორება: გლობალური goToModule გამოვიყენოთ ===
        card.onclick = function() {
            console.log("კლიკი მოდულზე:", moduleName);
            if (typeof goToModule === 'function') {
                goToModule(moduleName);
            } else if (window.ModuleConfig && window.ModuleConfig.goToModule) {
                window.ModuleConfig.goToModule(moduleName);
            } else {
                alert("გადამისამართების ფუნქცია არ მოიძებნა!");
                console.error("goToModule არ არის განსაზღვრული");
            }
        };

        card.innerHTML = `
            <div class="module-icon">${details.icon}</div>
            <div class="module-title">${details.title}</div>
            <div class="module-description">${details.description}</div>
        `;

        grid.appendChild(card);
    });
}

window.addEventListener('load', renderModules);