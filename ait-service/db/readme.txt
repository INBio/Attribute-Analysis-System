Instituto Nacional de Biodiversidad
Esteban Mata Aguilar
Febrero 2010
Proyecto: Indicadores taxonómicos - IABIN

			******************* Notas inportantes referentes a la BD del proyecto ******************

1. Para setear la información de INDICADORES en la base de datos existirán dos posibilidades:
	a) Ingresar los datos de manera "plana" en la tabla ait.taxon_indicator_plain de la forma (indicator,scientific name),
	   por ejemplo: ("En peligro de extinción","Ara macao"). En este caso será necesario correr el proceso de indexaxión de indicadores
 	   en el sistema, para que este último cree la tabla jerárquica ait.indicator a partir de ait.taxon_indicator_plain.
	b) Ingresar los datos directamente en la tabla jerárquica ait.indicator

2. Ahora para el caso a) del paso 1, se deberá además correr desde el sistema el proceso de indexaxión de indicadores taxonómicos, con el
   fin de que el sistema genere la tabla ait.taxon_indicator.

3. Por otro lado para el caso b) del paso 1, se deberá ingresar los datos 
   directamente en la tabla ait.taxon_indicator.

			1 y 2) ait.taxon_indicator_plain ---> ait.indicator
						  	 ---> ait.taxon_indicator

			1 y 3) Indicar datos directamente en: ait.indicator y ait.taxon_indicator

4. La tabla ait.taxon_index será generada durante el proceso de indexaxión de taxonomía. Esta tabla permitirá "resolver"
   el id de un taxon a partir del nombre del taxon en formato texto. Además incluye el rango de dicho taxon.


________________________________________________
|	     Caso de prueba a utilizar: 	|
|						|
|Adrana cultrata:				|
|	MUMAUP:Mollusca:367			|	
|						|
|Arcopsis adamsi:				|
|	MUMAUP:Mollusca:1946			|
|	MUMAUP:Mollusca:1947			|
|	MUMAUP:Mollusca:1948			|
|	MUMAUP:Mollusca:1949			|
|	MUMAUP:Mollusca:1950			|
|	MUMAUP:Mollusca:1951			|
|	MUMAUP:Mollusca:1952			|
|	MUMAUP:Mollusca:5539			|
|						|
|Brachidontes exustus:				|
|	MUMAUP:Mollusca:2500			|
|	MUMAUP:Mollusca:2501			|
|	MUMAUP:Mollusca:2502			|
|	MUMAUP:Mollusca:2503			|
0000000000000000000000000000000000000000000000000

Procesos de indexaxión:
1. Indexaxión de indicadores. (crear ait.indicator)
2. Indexaxión de indicadores taxonómicos. (crear ait.taxon_indicator)
3. Indexaxión de taxonomía. (crear ait.taxon_index)
