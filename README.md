# examenmoviles

# Panini Support App
**Examen II — Diseño y Programación de Plataformas Móviles | I Ciclo 2026**

**Estudiante:** Jeremy Romero Carazo  
**Docente:** Mag. Rachel Bolívar Morales  
**Fecha:** 30 de Mayo, 2026

---

## Descripción

App móvil para gestión de tickets de soporte interno de Panini, 
relacionados con la distribución del álbum FIFA World Cup 2026.
Centraliza reportes de inventario, logística y proveedores que 
antes se manejaban por correo y hojas de cálculo.

---

## Estructura del repositorio

/app          → Proyecto Android
/contracts    → API Contracts en YAML
/docs         → Decisiones técnicas
/video        → Enlace al video demo
README.md     → Este archivo

---

## Tecnologías

Tecnología y su Uso 

- Se utilizo Kotlin + Jetpack Compose para la UI y lógica de presentación 
- MVVM para la Arquitectura 
- StateFlow y SharedFlow para la Comunicación reactiva basada en eventos 
- Navigation Compose para la Navegación entre pantallas 
- Retrofit usando un Networking layer preparado 
- Material Design 3 para el Sistema de diseño 

---

## Funcionalidades

- Login simulado
- Lista de tickets ordenada por prioridad
- Detalle completo de ticket
- Creación de tickets
- Actualización de estado y prioridad
- Lista reactiva sin recarga manual
- Feature Flags con vista dedicada
- Estados Loading / Success / Error

---

## Cómo ejecutar

1. Clonar el repositorio
2. Abrir `/app` en Android Studio
3. Sincronizar Gradle
4. Ejecutar en dispositivo o emulador API 24+
5. Login: cualquier email y contraseña válidos

---

## Notas para el equipo

- Para conectar el backend: cambiar `BASE_URL` en `RetrofitClient.kt`
- Mock data en `MockTickets.kt` se reemplaza por llamadas Retrofit
- Feature Flags pueden evolucionar a Firebase Remote Config
