# Pruebas Postman - proveedor-service

## GET - Listar proveedores
`GET http://localhost:8082/api/v1/proveedores`

---

## GET - Buscar por ID
`GET http://localhost:8082/api/v1/proveedores/1`
`GET http://localhost:8082/api/v1/proveedores/5`

---

## GET - Buscar por RUT
`GET http://localhost:8082/api/v1/proveedores/rut/76543210-1`
`GET http://localhost:8082/api/v1/proveedores/rut/22334455-6`

---

## GET - Buscar por Nombre
`GET http://localhost:8082/api/v1/proveedores/nombre/Distribuidora San Cristóbal`

---

## POST - Crear proveedor
`POST http://localhost:8082/api/v1/proveedores`
```json
{
    "rut": "99999999-9",
    "nombre": "Proveedor Prueba",
    "email": "prueba@proveedor.cl",
    "telefono": "+56912345678",
    "direccion": "Av. Prueba 123, Santiago"
}
```

---

## POST - RUT duplicado
`POST http://localhost:8082/api/v1/proveedores`
```json
{
    "rut": "76543210-1",
    "nombre": "Test",
    "email": "test@test.cl",
    "telefono": "+56900000000",
    "direccion": "Calle Test 123"
}
```

---

## POST - Campos vacíos (validación)
`POST http://localhost:8082/api/v1/proveedores`
```json
{
    "rut": "",
    "nombre": "",
    "email": "noesunemail",
    "telefono": "",
    "direccion": ""
}
```

---

## PUT - Actualizar proveedor
`PUT http://localhost:8082/api/v1/proveedores/1`
```json
{
    "rut": "76543210-1",
    "nombre": "Distribuidora Sur Actualizada",
    "email": "nuevo@sur.cl",
    "telefono": "+56999999999",
    "direccion": "Av. Nueva 999, Santiago"
}
```

---

## DELETE - Eliminar proveedor (borrado lógico)
`DELETE http://localhost:8082/api/v1/proveedores/2`

> El registro no se elimina físicamente de la BD, solo cambia `activo = 0`.
> Verificar en phpMyAdmin que el campo `activo` cambió a `0`.

---

> Nota
> ```properties
> server.port=8082
> ```