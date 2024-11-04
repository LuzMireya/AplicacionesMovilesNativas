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
        document.getElementById('password').value = user.password; // Agregando contraseña
        document.getElementById('rol').value = user.rol;
    } catch (error) {
        console.error('Error al cargar datos del usuario:', error);
    }
}

// Llamar a loadUserData al cargar la página
window.onload = loadUserData;

async function updateProfile() {
    const updatedUser = {
        username: document.getElementById('username').value,
        email: document.getElementById('email').value,
        password: document.getElementById('password').value, // Agregar contraseña
        rol: document.getElementById('rol').value
    };

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
            window.location.href = 'user.html'; // Redirige al perfil de usuario
        } else {
            alert('Error al actualizar el usuario');
        }
    } catch (error) {
        console.error('Error en la actualización:', error);
    }
}
