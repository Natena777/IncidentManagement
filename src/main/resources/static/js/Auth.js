// auth.js - Authentication Service

class AuthService {

    // Token-ის შენახვა localStorage-ში
    static saveToken(token) {
        localStorage.setItem('jwt_token', token);
        console.log("Token saved successfully");
    }

    // Token-ის წამოღება localStorage-დან
    static getToken() {
        return localStorage.getItem('jwt_token');
    }

    // Token-ის წაშლა (logout-ისთვის)
    static removeToken() {
        localStorage.removeItem('jwt_token');
        console.log("Token removed");
    }

    // შემოწმება: დალოგინებული ხარ თუ არა (ვადის შემოწმებით)
    static isAuthenticated() {
        const token = this.getToken();
        if (!token) return false;  // token არ არის

        // თუ token expired-ია, დააბრუნე false
        if (this.isTokenExpired()) {
            return false;
        }

        return true;  // token არის და ვალიდურია ✅
    }

    // Authorization Headers-ის გენერირება fetch-ისთვის
    static getAuthHeaders() {
        const token = this.getToken();

        // თუ token არ არის ან expired-ია
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
            // ჯერ შევამოწმოთ token ლოკალურად
            if (this.isTokenExpired()) {
                alert("Your session has expired. Please login again.");
                this.removeToken();
                window.location.href = "/auth/login.html";
                throw new Error("Token expired");
            }

            // Headers-ის დამატება
            const headers = this.getAuthHeaders();

            // Request-ის გაგზავნა
            const response = await fetch(url, {
                ...options,
                headers: {
                    ...headers,
                    ...options.headers  // დამატებითი headers-ები თუ არის
                }
            });

            // Backend-იდან 401 შემოწმება
            if (response.status === 401) {
                alert("Session expired. Please login again.");
                this.removeToken();
                window.location.href = "/auth/login.html";
                throw new Error("Unauthorized");
            }

            // სხვა შეცდომების შემოწმება
            if (!response.ok) {
                throw new Error(`HTTP ${response.status}: ${response.statusText}`);
            }

            return response;

        } catch (error) {
            console.error("Fetch error:", error);
            throw error;
        }
    }


    // Protected Page-ის დაცვა (გვერდის ჩატვირთვისას)
    static requireAuth(redirectUrl = "/auth/login.html") {
        if (!this.isAuthenticated()) {
            alert("You must be logged in to access this page.");
            window.location.href = redirectUrl;
            return false;
        }
        return true;
    }


    // Logout - Token-ის წაშლა და Login-ზე გადასვლა
    static logout() {
        this.removeToken();
        alert("You have been logged out.");
        window.location.href = "/auth/login.html";
    }



    // JWT Token-ის Decode (payload-ის წასაკითხად) ← ახალი!
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
        if (!token) return true;  // თუ token არ არის → expired-ია

        const decoded = this.decodeToken(token);
        if (!decoded || !decoded.exp) return true;  // თუ decode ვერ მოხდა → expired-ია

        // exp არის წამებში, Date.now() არის მილიწამებში
        const currentTime = Date.now() / 1000;  // გადავაქციოთ წამებში

        const isExpired = decoded.exp < currentTime;

        if (isExpired) {
            console.log("Token has expired!");
            this.removeToken();  // წავშალოთ expired token
        }

        return isExpired;
    }

}