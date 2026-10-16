-- ============================================================================
-- Ejecutar DESPUÉS de levantar la app UNA VEZ (para que Hibernate cree las tablas) y detenerla.
-- ============================================================================

-- 1) ROLES de TU dominio (nombre SIN 'ROLE_'; debe coincidir con @PreAuthorize)
INSERT INTO roles(name) VALUES ('ADMINISTRADOR'), ('OPERADOR');

-- 2) USUARIOS. Contraseña de ambos = 12345 (hash BCrypt)
INSERT INTO users(username, password, enabled) VALUES
  ('admin1',    '$2a$10$pQeZla3780Hhi38rLnuRvuJuppfpE0EVeFQ5xFLEvEWKzNzpgzv4.', true),
  ('operador1', '$2a$10$pQeZla3780Hhi38rLnuRvuJuppfpE0EVeFQ5xFLEvEWKzNzpgzv4.', true);

-- 3) USUARIO <-> ROL
INSERT INTO user_role(user_id, role_id) VALUES
  ((SELECT id FROM users WHERE username='admin1'),    (SELECT id FROM roles WHERE name='ADMINISTRADOR')),
  ((SELECT id FROM users WHERE username='operador1'), (SELECT id FROM roles WHERE name='OPERADOR'));

-- 4) DATOS DE PRUEBA del dominio (las FK necesitan filas existentes: no hay endpoint para crearlas)
INSERT INTO astronomo(nombre, email, telefono) VALUES
  ('Carla Ríos',  'carla@mail.com', '999111222'),
  ('Luis Pardo',  'luis@mail.com',  NULL);

INSERT INTO telescopio(nombre, categoria, ubicacion) VALUES
  ('Hubble Local', 'REFLECTOR',   'Observatorio Norte'),
  ('Celestron 8',  'REFRACTOR',   'Azotea Central');

INSERT INTO observacion(horas, fecha, tarifa, estado, astronomo_id, telescopio_id) VALUES
  (3, '2025-05-10', 50.00, true,  1, 1),
  (2, '2025-05-11', 60.00, true,  1, 2),
  (4, '2025-05-12', 45.50, false, 2, 1);

-- Verificar usuarios y roles:
-- SELECT u.username, r.name FROM users u JOIN user_role ur ON ur.user_id=u.id JOIN roles r ON r.id=ur.role_id;
