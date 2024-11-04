document.addEventListener("DOMContentLoaded", async () => {
    const userId = localStorage.getItem("userId");
    const response = await fetch(`http://localhost:8080/api/users/${userId}`);
    if (response.ok) {
        const user = await response.json();
        document.getElementById("profileInfo").innerHTML = `
            <p>Usuario: ${user.username}</p>
            <p>Email: ${user.email}</p>
            <p>Rol: ${user.rol}</p>
        `;
    } else {
        alert("Error al cargar la información del perfil.");
    }
});

function goToEditProfile() {
    const userId = localStorage.getItem("userId");
    window.location.href = `edit-profile.html?id=${userId}`;
}

function logout() {
    // Limpiar el localStorage y redirigir al inicio de sesión
    localStorage.removeItem("userId");
    alert("Sesión cerrada");
    window.location.href = "index.html";
}
