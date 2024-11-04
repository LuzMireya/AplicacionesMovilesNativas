// js/edit-user.js

// Obtener el id del usuario desde la URL
const urlParams = new URLSearchParams(window.location.search);
const userId = urlParams.get('id');

async function loadUserData() {
    try {
        const response = await fetch(`http://localhost:8080/api/users/${userId}`);
        const user = await response.json();

        // Cargar datos en el formulario
        document.getElementById('username').value = user.username;
        document.getElementById('email').value = user.email;
        document.getElementById('rol').value = user.rol;
    } catch (error) {
        console.error('Error al cargar datos del usuario:', error);
    }
}

// Llamar a loadUserData al cargar la página
window.onload = loadUserData;

async function updateUser() {
    const updatedUser = {
        username: document.getElementById('username').value,
        email: document.getElementById('email').value,
        rol: document.getElementById('rol').value,
        password: document.getElementById('password').value // Incluir la contraseña
    };

    // Si no se proporciona una contraseña, eliminarla del objeto
    if (!updatedUser.password) {
        delete updatedUser.password;
    }

    try {
        const response = await fetch(`http://localhost:8080/api/users/${userId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedUser)
        });

        if (response.ok) {
            alert('Usuario actualizado correctamente');
            window.location.href = 'admin.html'; // Redirige al dashboard del admin
        } else {
            alert('Error al actualizar el usuario');
        }
    } catch (error) {
        console.error('Error en la actualización:', error);
    }
}
