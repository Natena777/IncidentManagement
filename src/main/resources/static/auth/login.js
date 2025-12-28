// /js/login.js

document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("loginForm");
    const responseBox = document.getElementById("response");

    if (!form || !responseBox) {
        console.error("ლოგინის ფორმა ან response box არ მოიძებნა!");
        return;
    }

    form.addEventListener("submit", function(event) {
        event.preventDefault();

        responseBox.className = "response-box loading";
        responseBox.innerText = "⏳ Logging in...";
        responseBox.style.display = "block";

        const data = {
            username: document.getElementById("username").value.trim(),
            password: document.getElementById("password").value
        };

        const url = `${CONFIG.API_BASE_URL}${CONFIG.API.AUTH.LOGIN}`;

        console.log("📤 Login request to:", url);

        fetch(url, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        })
            .then(res => {
                console.log("📥 Response status:", res.status);
                if (!res.ok) {
                    throw new Error("Invalid credentials or server error");
                }
                return res.json();
            })
            .then(response => {
                console.log("✅ Login response:", response);

                if (response.token) {
                    AuthService.saveToken(response.token);

                    responseBox.className = "response-box success";
                    responseBox.innerText = "✅ Login successful! Redirecting...";

                    setTimeout(() => {
                        window.location.href = "/main/main.html";
                    }, 1500);
                } else {
                    throw new Error("No token received from server");
                }
            })
            .catch(err => {
                console.error("❌ Login error:", err);
                responseBox.className = "response-box error";
                responseBox.innerText = "❌ " + err.message;
            });
    });
});