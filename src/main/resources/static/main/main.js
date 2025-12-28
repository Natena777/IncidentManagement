// === ტოკენის დიაგნოსტიკა ===
console.log("%c🔍 Token Check on Main Page", "background: #2196F3; color: white; font-size: 16px; padding: 10px;");

AuthService.requireAuth();

function renderModules() {
    if (!window.ModuleConfig) {
        console.error("ModuleConfig არ იტვირთება!");
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

    // ... დანარჩენი კოდი უცვლელი
    allowedModules.forEach(moduleName => {
        const details = window.ModuleConfig.DETAILS[moduleName];
        if (!details) return;

        const card = document.createElement('div');
        card.className = 'module-card';
        card.onclick = () => window.ModuleConfig.goToModule(moduleName);

        card.innerHTML = `
                <div class="module-icon">${details.icon}</div>
                <div class="module-title">${details.title}</div>
                <div class="module-description">${details.description}</div>
            `;

        grid.appendChild(card);
    });
}

window.addEventListener('load', renderModules);