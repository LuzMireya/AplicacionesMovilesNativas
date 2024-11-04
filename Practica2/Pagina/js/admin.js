document.addEventListener("DOMContentLoaded", async () => {
    const response = await fetch("http://localhost:8080/api/users");
    if (response.ok) {
        const users = await response.json();
        const userList = document.getElementById("userList");
        users.forEach(user => {
            userList.innerHTML += `
                <div>
                    <p>Usuario: ${user.username}</p>
                    <p>Email: ${user.email}</p>
                    <p>Rol: ${user.rol}</p>
                    <button onclick="goToEditUser(${user.id})">Editar</button>
                    <button onclick="deleteUser(${user.id})">Eliminar</button>
                </div>
                <hr>
            `;
        });
    } else {
        alert("Error al cargar usuarios");
    }
});

// Redirige a la página de edición del usuario
function goToEditUser(userId) {
    window.location.href = `edit-user.html?id=${userId}`;
}

async function deleteUser(userId) {
    const response = await fetch(`http://localhost:8080/api/users/${userId}`, { method: "DELETE" });
    if (response.ok) {
        alert("Usuario eliminado");
        window.location.reload();
    } else {
        alert("Error al eliminar usuario");
    }
}

function logout() {
    // Limpiar el localStorage y redirigir al inicio de sesión
    localStorage.removeItem("userId");
    alert("Sesión cerrada");
    window.location.href = "index.html";
}
