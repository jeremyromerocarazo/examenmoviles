# Decisiones Técnicas — Panini Support App

## Arquitectura — MVVM

Se eligió MVVM porque este separa la lógica de negocio de la UI, 
asi facilitando el buen mantenimiento y permitiendo que otros ingenieros 
continúen el proyecto sin romper lo existente.

- `TicketListViewModel` este maneja el estado de la lista
- Las pantallas solo observan estado, osea esstos no tienen logica
- El mock data vive en `MockTickets.kt`, desconectado de la UI

---

## Comunicación basada en eventos — StateFlow y SharedFlow

Se usa `StateFlow` para el estado de la UI y `SharedFlow` para 
eventos especificos. Esto permite que la lista se actualice 
ssola sin recargar la pantalla de manera manualmente.

**Flujo al crear un ticket:**

Usuario crea ticket
Primero ViewModel agrega ticket a MockTickets
luego ViewModel llama loadTickets() despues _uiState emite nuevo estado Success y entonces
la lista se recompone automáticamente

**Flujo al cambiar prioridad:**

Usuario cambia prioridad
- ViewModel actualiza el ticket en MockTickets
- getFilteredTickets() reordena por prioridad (HIGH → MEDIUM → LOW)
- La lista se reordena sola

- Se eligió `StateFlow` sobre `LiveData` porque es nativo de Kotlin, 
funciona directamente con `collectAsState()` de Compose y no 
requiere observadores de ciclo de vida.

---

## Feature Flags

Se implementó un objeto `FeatureFlags` con `MutableStateFlow` 
paraa cada funcionalidad controlable cualquier pantalla puede ser
observada con los cambios relativamente.

 Flag y Qué controla cadaa uno
`canCreateTicket` : Muestra o oculta el botón de crear ticket
`canUpdatePriority` : Muestra o oculta el botón de cambiar prioridad 
`showResolvedTickets` : Filtra tickets resueltos de la lista 

Para producción, estos flags se pueden conectar a Firebase por medio de
Remote Config sin cambiar la estructura actual.

---

## Networking Layer — Retrofit preparado

No se solicito backend pero la estructura está lista en `data/network/`:

- `ApiService.kt` — define los endpoints
- `RetrofitClient.kt` — instancia configurada de Retrofit

Cuando el backend .NET esté listo, solo se necesita:
1. Cambiar `BASE_URL` en `RetrofitClient.kt`
2. Reemplazar llamadas a `MockTickets` por llamadas Retrofit en el ViewModel

---

## Manejo de estados UI

Se usa una sealed class `TicketListUiState` con tres estados:

- `Loading` — mientras carga
- `Success` — lista de tickets lista
- `Error` — mensaje de error

Esto prepara la UI para manejar latencia de red real cuando 
se integre el backend.
