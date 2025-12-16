/* Hausaufgabe 1
   Name: Rustam Khavaiashkhov
*/

-- a) Studierende, die Sokrates aus Vorlesungen kennen
SELECT DISTINCT s.Name, s.MatrNr
FROM Studenten s, hoeren h, Vorlesungen v, Professoren p
WHERE s.MatrNr = h.MatrNr
  AND h.VorlNr = v.VorlNr
  AND v.gelesenVon = p.PersNr
  AND p.Name = 'Sokrates';

-- b) Studierende, die Vorlesungen hören, die auch Fichte hört
SELECT DISTINCT s.Name, s.MatrNr
FROM Studenten s, hoeren h
WHERE s.MatrNr = h.MatrNr
  AND h.VorlNr IN (
      SELECT h2.VorlNr
      FROM hoeren h2, Studenten s2
      WHERE h2.MatrNr = s2.MatrNr
      AND s2.Name = 'Fichte'
  );


  