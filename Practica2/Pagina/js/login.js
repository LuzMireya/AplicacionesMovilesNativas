document.addEventListener("DOMContentLoaded", () => {
    const userId = localStorage.getItem("userId");
    if (userId) {
        // Si hay un usuario logueado, redirigir según el rol
        const users = JSON.parse(localStorage.getItem("users")) || []; // Suponiendo que tienes usuarios en localStorage
        const user = users.find(u => u.id === userId);
        if (user) {
            if (user.rol === 'admin') {
                window.location.href = 'admin.html';
            } else {
                window.location.href = 'user.html';
            }
        }
    }
});

async function login() {
    try {
        const response = await fetch('http://localhost:8080/api/users');
        if (!response.ok) throw new Error('Network response was not ok');
        const users = await response.json();

        // Obtén los valores de usuario y contraseña ingresados por el usuario
        const enteredUsername = document.getElementById('username').value;
        const enteredPassword = document.getElementById('password').value;

        // Busca un usuario en la lista que coincida con el username y password ingresados
        const user = users.find(user => user.username === enteredUsername && user.password === enteredPassword);

        if (user) {
            // Almacenar el ID del usuario en localStorage
            localStorage.setItem("userId", user.id);
            // Verifica el rol del usuario y redirige según corresponda
            if (user.rol === 'admin') {
                window.location.href = 'admin.html'; // Cambia a la página de administrador
            } else {
                window.location.href = 'user.html'; // Cambia a la página de usuario regular
            }
        } else {
            alert("Usuario o contraseña incorrectos.");
        }
    } catch (error) {
        console.error("Error en la solicitud:", error);
        alert("Error al conectar con el servidor. Verifica si el servidor está activo y configurado correctamente.");
    }
}
