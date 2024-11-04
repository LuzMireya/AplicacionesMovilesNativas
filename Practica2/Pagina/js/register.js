document.getElementById("registerForm").addEventListener("submit", async (event) => {
    event.preventDefault(); // Evitar que el formulario se envíe de forma tradicional

    const username = document.getElementById("newUsername").value;
    const email = document.getElementById("newEmail").value;
    const password = document.getElementById("newPassword").value;

    const user = {
        username: username,
        email: email,
        password: password,
        rol: 'usuario' // Rol por defecto
    };

    try {
        const response = await fetch('http://localhost:8080/api/users/register', {
            method: 'POST', // Cambiar a POST si decides seguir esa ruta
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });

        if (response.ok) {
            alert("Registro exitoso. Puedes iniciar sesión ahora.");
            window.location.href = 'index.html'; // Redirigir a la página de inicio de sesión
        } else {
            alert("Error al registrar el usuario.");
        }
    } catch (error) {
        console.error("Error en la solicitud:", error);
        alert("Error al conectar con el servidor. Verifica si el servidor está activo y configurado correctamente.");
    }
});
