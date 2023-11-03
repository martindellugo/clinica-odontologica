function cerrarSesion() {
    // Realiza una petición POST a la ruta de cierre de sesión en el servidor
    fetch('/logout', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({}) // Puedes enviar datos adicionales en el cuerpo de la petición si es necesario
    })
    .then(response => {
        if (response.redirected) {
            // Si la respuesta redirige, significa que la sesión se cerró correctamente
            window.location.href = response.url; // Redirige a la página de inicio de sesión o a la página que prefieras
        } else {
            // Si la respuesta no redirige, muestra un mensaje de error o toma otra acción según tu lógica de negocio
            console.error('Error al cerrar sesión:', response);
        }
    })
    .catch(error => {
        // Manejo de errores en caso de que la petición falle
        console.error('Error al cerrar sesión:', error);
    });
}