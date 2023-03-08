// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyAcipQZBVk4G9p76FJjmcve2X8iMWO1ifE",
  authDomain: "employee-project-management.firebaseapp.com",
  projectId: "employee-project-management",
  storageBucket: "employee-project-management.appspot.com",
  messagingSenderId: "137816452389",
  appId: "1:137816452389:web:54cae6c6a3ae9147673ecc",
  measurementId: "G-3K1NVPVD4X"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
export const analytics = getAnalytics(app);