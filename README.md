# siw-restaurant

**Descrizione progetto** : Il sistema infromativo in questione descrive la sezione delivery di un ristorante. Dunque il sistema permette la prenotazione di ordini da parte di clienti, la presa in carito e consegna di questi da parte di fattorini.
Al sistema possono accedere 4 tipi d'utente:
   - utente occasionale: a cui è permesso solamente prendere visione del menù.
   - utente Amministratore: il quale può aggiungere,modificare ed elminare i piatti presenti nel menù.
   - utente Cliente: che può effetuare l'ordine, inserendo le righe d'ordine, modificarlo, eliminarlo ed eventualmente lasciare un feedback sul servizio. Inoltre ogni cliente può salvare dei piatti come preferiti.
   - utente Fattorino: il quale può prendere in carico e consegnare ordini fino ad un massimo di 3.

* **Caso d'uso UC1: Inserimento di un nuovo piatto**  attore: _amministratore_
1. un amministratore seleziona la finestra gestione piatti.
2. l'amministratore seleziona l'operazione aggiungi piatto.
3. l'amministratore inserisce il nome, la descrizione, il tipo e l'immagine del nuovo piatto e conferma l'inserimento.
4. i dati del piatto sono corretti. Il sistema registra il nuovo piatto.
5. _I dati del piatto non sono corretti. Il sistema termina l'operazione di inserimento piatto e visualizza un messaggio d'errore._

   
* **Caso d'uso UC2: Modifica dati piatto**  attore: _amministratore_
1. un amministratore seleziona la finestra gestione piatti.
2. l'amministratore seleziona l'operazione modifica dati piatto.
3. l'amministratore seleziona il piatto da modificare e  inserisce nome, descrizione, tipo del piatto e conferma l'inserimento.
4. i dati del piatto sono corretti. Il sistema registra la modifica del piatto.
5. _I dati del piatto non sono corretti. Il sistema termina l'operazione di modifica dati piatto e visualizza un messaggio d'errore._

 * **Caso d'uso UC3: Modifica immagine piatto**  attore: _amministratore_
1. un amministratore seleziona la finestra gestione piatti.
2. l'amministratore seleziona l'operazione modifica immagine piatto.
3. l'amministratore seleziona il piatto da modificare e  inserisce la nuova immagine e conferma l'inserimento.
4. i dati del piatto sono corretti. Il sistema registra la modifica del piatto.
5. _I dati del piatto non sono corretti. Il sistema termina l'operazione di modifica dati piatto e visualizza un messaggio d'errore._

  * **Caso d'uso UC4: Visualizza menu**  attore: _utente generico_
1. un utente seleziona la finestra menù.
2. il sistema mostra tutti i piatti in menù suddivisi per tipo.


  * **Caso d'uso UC5: Aggiungi piatto ai preferiti**  attore: _cliente_
1. un cliente seleziona la finestra menù.
2. il cliente sceglie il piatto da aggiungere ai preferiti e conferma.
3. il sistema registra l'aggiunta del piatto ai preferiti dell'utente.

   

  * **Caso d'uso UC6: Togli piatto dai preferiti**  attore: _cliente_
1. un cliente seleziona la finestra menù.
2. il cliente sceglie il piatto da togliere dai preferiti e conferma.
3. il sistema registra la rimozione del piatto dai preferiti dell'utente.


  * **Caso d'uso UC7: Aggiungi feedback a un'ordine**  attore: _cliente_
1. un cliente seleziona la finestra ordini, il sistema mostra gli ordini di quel cliente.
2. il cliente sceglie l'ordine consegnato di cui vuole scrivere il feedback.
3. il cliente inserisce valutazione e testo del feedback e conferma.
4. i dati del feedback sono corretti. Il sistema registra il feedback e lo associa all'ordine.
5. _I dati del feedback non sono corretti. Il sistema termina l'operazione di aggiunta feedback e visualizza un messaggio d'errore._

  * **Caso d'uso UC8: Creazione nuovo ordine**  attore: _cliente_
1. un cliente seleziona la finestra ordini, il sistema mostra gli ordini di quel cliente.
2. il cliente sceglie l'operazione nuovo ordine e il sistema registra un nuovo ordine.
3a. il cliente inserisce una nuova riga di ordine specificando: piatto e quantità e conferma.
3b. il cliente seleziona ed elimina una riga d'ordine.
4. il sistema mostra le righe di ordine inserite.
Il cliente ripete i passi 3-4 fino a che non indica di aver terminato.
5. il cliente conferma l'ordine, il sistema registra le righe d'orine e le associa all'ordine.

  * **Caso d'uso UC9: Presa in carico di un nuovo ordine**  attore: _fattorino_
1. un fattorino seleziona la finestra ordini, il sistema mostra gli ordini pronti per essere spediti, quelli che lui sta consegnando e quelli già consegnati da lui.
2. il fattorino sceglie l'operazione prendi ordine e il sistema registra che l'ordine è stato preso in carico dal fattorino.
3. il fattorino arriva a destinazione e consegna l'ordine.
4. il fattorino conferma la consegna dell'ordine e il sistema la registra.
