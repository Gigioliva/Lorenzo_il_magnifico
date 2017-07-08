# LORENZO IL MAGNIFICO
#### *A cura di :Oliva Gianluigi, Noci Lorenzo, Mussi Marco*
## Prova Finale Ingegneria del Software
### Anno 2016/2017

![image_alt](https://4.bp.blogspot.com/-UQcEMscSqB8/V-Iloma1CxI/AAAAAAAAWWk/PuBwpO9TIVgpSF8zQWbM5JHlGXzFZnJiQCLcB/s400/Image2.jpg)

## **Come giocare**

Per avviare il gioco eseguire prima il main della classe Server.java del package it/polimi/ingsw/ps22/server/view.
Quindi avviare il main della classe Client del package it/polimi/ingsw/ps22/client/main; all'avvio di quest'ultimo verrà richiesto quale tecnologia di network usare [Socket o RMI] e quale grafica usare [GUI o CLI].
[Link regole del gioco](https://piazza-resources.s3.amazonaws.com/j0r6g91w2e1kk/j0raol8gxxi30b/RegoleGioco.pdf?AWSAccessKeyId=AKIAIEDNRLJ4AZKBW6HA&Expires=1499453556&Signature=SI3XGaAcGaiCEv%2BzJAzJ6fOd3TM%3D).

## **CLI**
Avviando il gioco in CLI verrà richiesto di effettuare un login, inserendo un username ed una password, e successivamente verrà chiesto se si tratta di un utente già registrato o di uno nuovo e in quanti giocatori si vorrebbe giocare. Nel caso si trattasse di un utente già registrato il server controllerà la correttezza della password inserita; se il controllo dà esito positivo allora il client si metterà in attesa del raggiungimento del numero massimo di giocatori o dello scadere del timer, altrimenti verrà richiesto di effettuare nuovamente il login.
Quando inizia il gioco verrà visualizzato lo stato corrente della partita e, nel caso del giocatore corrente, verrà richiesto l'inserimento di una mossa [giocare familiare, giocare carta leader, terminare il turno].

## **GUI**
Avviando il gioco in GUI si aprirà una finestra di login in cui si richiede l'inserimento dell'username, della password, se si tratta di un nuovo utente e in quale lobby vogliamo entrare. Una volta premuto il tasto confirm il server verificherà i dati inseriti mostrando un messaggio di matching o di errore. Nella finestra di login è presente anche un classifica dei giocatori ordinata in base al rapporto vittorie/partite.
Una volta inziata la partita si aprirà una finestra contenente la board principale, la board personale del giocatore, i familiari selezionabili e i nomi degli avversari. Premendo sui nomi degli avversari è possibile vedere le loro board personali.

![image_alt](http://imgur.com/TgXMzbZ.png)

![image_alt](http://imgur.com/gZeml7J.png)

![image_alt](http://imgur.com/CmKahbV.png)

## **Lettura da file**
Tutti i parametri di gioco, come ad esempio l'elenco delle carte, bonus azione e bonus finali, vengono letti da file salvando tutte le informazioni in formato XML. La lettura avviene mediante le classi contenute del package it/polimi/ingsw/ps22/server/parser che sfuttano la tecnica del Sax Parser. Le strutture dei file XML viene illustrato nelle singole classi che realizzano il parser.

## **Server**
Il Server è implementato nella classe Server.java del package it/polimi/ingsw/ps22/server/view. Una volta avviato inizializza tutte le variabili d'ambiente utili al funzionamento e successivamente si mette in attesa di nuove connessioni. Tutte le connessioni avvengono all'indirizzo //localhost o 127.0.0.1. Le connessioni tramite Socket comunicano usando la porta 12345, mentre quelle RMI sfuttano la porta di default del Registry 1099. Il server può accettare fino ad un massimo di 128 connessioni.
Quando una lobby raggiunge i 2 giocatori, il server avvia un Timer di 30 secondi oltre i quali viene avviata la partita indipendentemente dal numero di giocatori raggiunti; questo timer viene stoppato quando si raggiunge il massimo numero di giocatori della lobby corrispondente.
Se durante una partita un giocatore si disconnette, il gioco continua normalmente con gli altri giocatori; il giocatore disconnesso ha 15 minuti per potersi riconnettere e riprendere il suo turno, altrimenti il gioco lo salterà in automatico finchè il giocatore non si riconnetterà.
Il server salva lo stato dei tutte le partite ogni 3 minuti su file permettendo di riprendere tutte le partite all'avvio successivo del server. Il server inoltre tiene traccia di tutte le partite in corso.
