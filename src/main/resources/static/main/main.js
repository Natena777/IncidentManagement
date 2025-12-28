// === ტოკენის დიაგნოსტიკა ===
console.log("%cToken Check on Main Page", "background: #2196F3; color: white; font-size: 16px; padding: 10px;");

AuthService.requireAuth();

// ტესტის dummy როლი, რომ მინიმუმ ერთი მოდული გამოჩნდეს
function getTestUserRoles() {
    // სინამდვილეში აქ იკითხება token
    try {
        if (window.ModuleConfig && typeof window.ModuleConfig.getAllowedModules === 'function') {
            return window.ModuleConfig.getAllowedModules().length > 0
                ? window.ModuleConfig.getAllowedModules()
                : ['users']; // default ADMIN role მოდული
        }
        return ['users']; // default fallback
    } catch {
        return ['users'];
    }
}

function renderModules() {
    if (!window.ModuleConfig) {
        console.error("ModuleConfig არ იტვირთება! შეამოწმეთ script-ების თანმიმდევრობა.");
        return;
    }

    // არსებული allowed modules
    let allowedModules = window.ModuleConfig.getAllowedModules();
    console.log("ნებადართული მოდულები:", allowedModules);

    // თუ ცარიელია, ვმუშაობთ dummy role-ით
    if (!allowedModules || allowedModules.length === 0) {
        console.warn("მომხმარებელს როლი არ აქვს ან token არ არის. ტესტისთვის dummy modules გამოიყენება.");
        allowedModules = Object.keys(window.ModuleConfig.DETAILS);
    }

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

        // კლიკზე ModuleConfig.goToModule იძახება
        card.addEventListener('click', () => {
            console.log("კლიკი მოდულზე:", moduleName);
            window.ModuleConfig.goToModule(moduleName);
        });

        card.innerHTML = `
            <div class="module-icon">${details.icon}</div>
            <div class="module-title">${details.title}</div>
            <div class="module-description">${details.description}</div>
        `;

        grid.appendChild(card);
    });
}

// დარწმუნდით, რომ DOM მზად არის
document.addEventListener('DOMContentLoaded', renderModules);
