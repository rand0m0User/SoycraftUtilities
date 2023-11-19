@echo off
color 0a
cd "build"

"C:\Program Files\7-Zip\7z" a -sdel tmp.null "classes"
del tmp.null
"C:\Program Files\7-Zip\7z" a -sdel tmp.null "generated"
del tmp.null
"C:\Program Files\7-Zip\7z" a -sdel tmp.null "resources"
del tmp.null
"C:\Program Files\7-Zip\7z" a -sdel tmp.null "tmp"
del tmp.null

cd "libs"
del *-dev.jar
"..\..\advzip.exe" -z -3 *.jar