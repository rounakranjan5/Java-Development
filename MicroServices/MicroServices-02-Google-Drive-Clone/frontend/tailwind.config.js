/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,jsx}"],
  theme: {
    extend: {
      colors: { ink: "#20201e", paper: "#f7f7f5", line: "#e6e6e1", lime: "#d8f36a" },
      fontFamily: { sans: ["DM Sans", "ui-sans-serif", "system-ui", "sans-serif"], display: ["Space Grotesk", "ui-sans-serif", "sans-serif"] },
    },
  },
  plugins: [],
};
