// config.js - Global Configuration

const CONFIG = {
    // Backend API URL
    API_BASE_URL: (() => {
        const hostname = window.location.hostname;

        // Localhost-ზე
        if (hostname === 'localhost' || hostname === '127.0.0.1') {
            return 'http://localhost:8080';
        }

        // Production server-ზე
        if (hostname === '72.60.32.128') {
            return 'http://72.60.32.128:8080';
        }

        // Default (თუ სხვა დომენი იქნება)
        return `http://${hostname}:8080`;
    })(),

    // API Endpoints
    API: {
        AUTH: {
            LOGIN: '/api/auth/login',
            REGISTER: '/api/auth/registration',
            LOGOUT: '/api/auth/logout'
        },
        INCIDENTS: {
            LIST: '/api/incidents',
            CREATE: '/api/incidents',
            UPDATE: (id) => `/api/incidents/${id}`,
            DELETE: (id) => `/api/incidents/${id}`,
            GET: (id) => `/api/incidents/${id}`
        },
        USERS: {
            PROFILE: '/api/users/profile',
            LIST: '/api/users'
        }
    },

    // App Settings
    APP: {
        NAME: 'Incident Management System',
        VERSION: '1.0.0',
        TOKEN_KEY: 'jwt_token'  // localStorage key
    }
};

// Export for use in other files
console.log('✅ Config loaded:', CONFIG.API_BASE_URL);