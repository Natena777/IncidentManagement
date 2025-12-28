// auth.js - Authentication Service

class AuthService {

    // Token-ის შენახვა
    static saveToken(token) {
        localStorage.setItem(CONFIG.APP.TOKEN_KEY, token);
        console.log("Token saved successfully");
    }

    // Token-ის წამოღება
    static getToken() {
        return localStorage.getItem(CONFIG.APP.TOKEN_KEY);
    }

    // Token-ის წაშლა
    static removeToken() {
        localStorage.removeItem(CONFIG.APP.TOKEN_KEY);
        console.log("Token removed");
    }

    // შემოწმება: დალოგინებული ხარ თუ არა
    static isAuthenticated() {
        const token = this.getToken();
        if (!token) return false;

        if (this.isTokenExpired()) {
            return false;
        }

        return true;
    }

    // Authorization Headers
    static getAuthHeaders() {
        const token = this.getToken();

        if (!token || this.isTokenExpired()) {
            throw new Error("No valid authentication token");
        }

        return {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        };
    }

    // Fetch Wrapper JWT Authentication-ით
    static async fetchWithAuth(url, options = {}) {
        try {
            if (this.isTokenExpired()) {
                alert("Your session has expired. Please login again.");
                this.removeToken();
                window.location.href = "/auth/login.html";
                throw new Error("Token expired");
            }

            const headers = this.getAuthHeaders();

            // ← CONFIG.API_BASE_URL გამოვიყენოთ
            const fullUrl = url.startsWith('http')
                ? url
                : `${CONFIG.API_BASE_URL}${url}`;

            const response = await fetch(fullUrl, {
                ...options,
                headers: {
                    ...headers,
                    ...options.headers
                }
            });

            if (response.status === 401) {
                alert("Session expired. Please login again.");
                this.removeToken();
                window.location.href = "/auth/login.html";
                throw new Error("Unauthorized");
            }

            if (!response.ok) {
                throw new Error(`HTTP ${response.status}: ${response.statusText}`);
            }

            return response;

        } catch (error) {
            console.error("Fetch error:", error);
            throw error;
        }
    }

    // Protected Page-ის დაცვა
    static requireAuth(redirectUrl = "/auth/login.html") {
        if (!this.isAuthenticated()) {
            alert("You must be logged in to access this page.");
            window.location.href = redirectUrl;
            return false;
        }
        return true;
    }

    // Logout
    static logout() {
        this.removeToken();
        alert("You have been logged out.");
        window.location.href = "/auth/login.html";
    }

    // JWT Token Decode
    static decodeToken(token) {
        try {
            const base64Url = token.split('.')[1];
            const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
            const jsonPayload = decodeURIComponent(
                atob(base64).split('').map(function(c) {
                    return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
                }).join('')
            );
            return JSON.parse(jsonPayload);
        } catch (error) {
            console.error("Token decode error:", error);
            return null;
        }
    }

    // Token-ის ვადის გასვლის შემოწმება
    static isTokenExpired() {
        const token = this.getToken();
        if (!token) return true;

        const decoded = this.decodeToken(token);
        if (!decoded || !decoded.exp) return true;

        const currentTime = Date.now() / 1000;
        const isExpired = decoded.exp < currentTime;

        if (isExpired) {
            console.log("Token has expired!");
            this.removeToken();
        }

        return isExpired;
    }
}