
document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("registrationForm");
    const responseBox = document.getElementById("response");

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        responseBox.className = "response-box";
        responseBox.style.display = "block";
        responseBox.style.backgroundColor = "#e3f2fd";
        responseBox.style.color = "#0277bd";
        responseBox.style.border = "1px solid #90caf9";
        responseBox.innerText = "⏳ Creating your account...";

        const data = {
            firstName: document.getElementById("firstName").value,
            lastName: document.getElementById("lastName").value,
            email: document.getElementById("email").value,
            address: document.getElementById("address").value,
            password: document.getElementById("password").value,
            phone: document.getElementById("phone").value
        };

        // ← CONFIG.API_BASE_URL + CONFIG.API.AUTH.REGISTER
        const url = `${CONFIG.API_BASE_URL}${CONFIG.API.AUTH.REGISTER}`;

        console.log("📤 Sending registration to:", url);

        fetch(url, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(data)
        })
            .then(res => {
                console.log("📥 Response status:", res.status);
                if (!res.ok) {
                    throw new Error(`Registration failed: ${res.status}`);
                }
                return res.json();
            })
            .then(resp => {
                console.log("✅ Success:", resp);
                responseBox.className = "response-box success";
                responseBox.innerText = "✅ Registration successful! Redirecting to login...";
                form.reset();
                setTimeout(() => {
                    window.location.href = "../auth/login.html";
                }, 2000);
            })
            .catch(err => {
                console.error("❌ Error:", err);
                responseBox.className = "response-box error";
                responseBox.innerText = "❌ Registration Error: " + err.message;
            });
    });
});