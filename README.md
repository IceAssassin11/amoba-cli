# Amőba CLI játék

## Leírás
Egyszerű, konzolos amőba játék Java nyelven.
Két játékos van: ember és gép (kezdetleges, random AI).
A tábla mérete és betöltése testreszabható.

## Futtatás
A projekt Maven alapú.
Először a JAR elkészítéséhez használd a következő parancsot a terminálban:
mvn clean package

Aztán a JAR futtatása:
java -jar target/amoba-cli-1.0-SNAPSHOT-jar-with-dependencies.jar

## Játék menete
Indítás után választhatsz üres tábla vagy fájlból betöltött tábla (elmentett játékállás betöltése) között.
Custom nevet is lehet magadnak megadni.

Parancsok játék közben:
Lépés, a következő formátumban: a1, b3 stb.
save — játék mentése .txt fájlba
exit — kilépés a játékból

## Egyéb
A projekt JDK 21-en fut.
Nincs adatbázis vagy hálózati komponens, csak helyi játék.
