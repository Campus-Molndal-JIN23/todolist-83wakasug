# Wakana Sugihara

## Egna reflektioner

### Skapa TODO Application

Skapa ett program som kan hantera TODO.   
Programet kan även registrera progress och ansvarig för tasks.   
Alla data kan lägga,radera uppdatera, och ta bort. 

### Vad du har gjort
Planerade hur application fungerar och alla straktur för koden.   
Skapa test för coden.   
Försökte undvika hårda code så mycket som möjligt.   
Försökte dela upp klasser så mycket som möjligt.   
Försökte att undvika upprepning men det misslyckades jag.   

## Planering
1.Jag började planera hur programet bete sig.   
2.Sen bestämde jag vilka databasen jag kommer använda och detta strakturen.   
3.Tillslut planerade jag hur jag delar upp klasser och methoder. 

### Lösningsförslag innan uppgiften påbörjas
1:Planera väl för hur application vorde fungera innan man börjar skapa koden.   
2:Använda Heidi SQL för testa query innan man kör kode på java.  
3:Skapa inte test på alla method. Det bara tog tid. 

#### Hur du tänker försöka lösa uppgiften
1:Försöka inte hårdkoda så mycket som möjligt.  
2:Försöka dela upp kode så mycket som möjligt på olika metohd.  
3:Försöka dela upp till många olika klasser.

#### Diagram
![SQLITe](./SQLite.png)

### Github Project och projekthantering enligt Scrum/Kanban
![Kanban](./Kanban.png)

Min Gitt Repository    
https://github.com/Campus-Molndal-JIN23/todolist-83wakasug.git

## Arbetet och dess genomförande

### Vad som varit svårt
1: Det tog mig lång tid att skapa SQL Query. 
Delade upp till 3 tabell och hämta data från olika tabel och använda dessa data tog mig lång tid.   
2:Skapa Test  
Att skapa Test tog mig jäättelång tid. Jag var osäkert vilka behövde jag skapa test.
Jag tror jag hade skapat test för onödiga funktionen också. 

### Beskriv lite olika lösningar du gjort
1:När jag listar upp todo eller users så lagd jag upp till HashMap så jag kan undvika använda primaryKey för visa number fram för lista.  
2:Använde doCallRealMethod() för testet så kan faktiskt kallas in till real method fast det är Mock.   
3:Använde ByteArrayInputStream inputStream = new ByteArrayInputStream(number.getBytes());    
System.setIn(inputStream); så att input retunerar värde som definerar tidigare.   

### Beskriv något som var besvärligt att få till
Det var jättejobbigt med att skapa test.  Jag kanske misuppfattat så skapade jag test till alla method även till system.out.print.     
Iaf jag lärde mig om att lite funktion jag har aldrig använt så det var bra.   
Det tog jättelång tid att komma på hur man borde skriva koden.     
Speciellt när jag behövde mocka connection eller testa för SQL delen så behövde jag mocka massor och behövde veta vad det borde retunera etc.     
Min Engelska är inte så avancerad så det var svårt att skapa rätt Namn för funktions och variabel. 

### Beskriv om du fått byta lösning och varför i sådana fall
För att skriva Testet så behövde jag byta hur jag behöver skriva koden.   
Annars var det svårt att skapa Testet.  
Det var typ när jag testar menu med loop. 

## Reflektion & Slutsatser

### Vad gick bra
+ Jag använde Heidi SQL för testa SQL Query om SQLQuery fungerar hur jag har funderat.   
+ Fungerade koden kunde jag skapa!     
+ Lyckades innan inlämnings datum!  

### Vad gick dåligt
+ Att skapa test på Application class.   
+ Jag fick hjälp från min man som har aldrig studerat TDD innan men bara han tittade på 1 video  och några försök så blev han mycket bättre förståelse än mig:(  
+ Jag borde ha planerat mycket bättre. Vissa funktion jag är lite missnöjd hur det fungerar. 
+ Jag borde kunna strakturera koden lite bättre. 
+ Brist på tid. Jag är inte snabba att skriva koden och har två små barn så var det lite svårt att ha tid. 

### Vad har du lärt dig
+ Hur man kan skriva test för Static method. 
+ Olika test teknik. 
+ När jag skulle använda HashMap.

### Vad hade ni gjort annorlunda om ni gjort om projektet
+ Lite snyggare kode stractur
+ Skrivar inte test för alla method
+ Hur programet fungera när 
### Vilka möjligheter ser du med de kunskaper du fått under kursen.
+ Jag har lärde mig lite mer funktion inom testet så jag kommer kunna använda dessa teknik. 
+ Att kommer jag göra Bättre planering
+ Mockade objekt kan jag göra lite bättre. 
