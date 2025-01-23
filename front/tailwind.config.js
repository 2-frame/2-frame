const {heroui} = require("@heroui/react");

/** @type {import('tailwindcss').Config} */
export default {
    content: [
        "./src/**/*.{html,js,ts,tsx}",
        "./index.html",
        "./node_modules/@heroui/theme/dist/**/*.{js,ts,jsx,tsx}",
    ],
    darkMode: "class",
    plugins: [heroui()],
    theme: {
        extend: {},
    },
}

