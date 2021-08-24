SELECT * FROM estudiantes;

SELECT * FROM estudiantes ORDER BY programa;

SELECT COUNT(programa) AS conteo FROM estudiantes GROUP BY programa HAVING programa = 'Electrónica';