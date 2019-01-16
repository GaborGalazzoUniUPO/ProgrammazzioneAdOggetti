# Agenda - Object Oriented Programming

### Gabor Galazzo (20024195) A.A. 2018-2019 - DiSIT - UniUPO

Agenda è un applicativo per gestire appuntamenti caratterizzati da **data, ora, durata, dove e quando**.

### <span style="color: #0000ff;"><span style="color: #0000ff;"><span style="color: #0000ff;"><span style="color: #0000ff;">Scelte progettuali:</span></span></span></span>

#### {@link uniupo.gaborgalazzo.calendar.domain.Appointment}:

Al fine di gestire il corretto formato dei campi {@link uniupo.gaborgalazzo.calendar.domain.Appointment#date} e {@link uniupo.gaborgalazzo.calendar.domain.Appointment#time} sono state introdotte due costanti statiche di tipo {@link java.time.format.DateTimeFormatter}:

*   {@link uniupo.gaborgalazzo.calendar.domain.Appointment#APPOINTMENT_DATE_FORMAT}
*   {@link uniupo.gaborgalazzo.calendar.domain.Appointment#APPOINTMENT_TIME_FORMAT}

Tutti i campi di questa classe sono immutabili al fine di prevenire eventuali errori di incapsulamento che porterebbero ad una scorretta alterazione di un appuntamento causando una situazione di incoerenza difficilmente gestibile da {@link uniupo.gaborgalazzo.calendar.domain.Agenda}

Il metodo {@link uniupo.gaborgalazzo.calendar.domain.Appointment#getDateTime()} è stato introdotto per agevolare le operazioni sulle date.

**{@link uniupo.gaborgalazzo.calendar.domain.Agenda}:**

Questa classe permette di gestire e manipolare una lista di appuntamenti. All'interno dell'agenda non ci possono essere due o più appuntamenti che collidono, ovvero già definiti per quello stesso orario, tenendo anche conto della durata.

Per modificare un appuntamento bisogna passare per l'agenda che lo contiene in modo tale da evitare che le modifiche possano creare un agenda inconsistente sui suoi vincoli.

Si considerino i metodi {@link uniupo.gaborgalazzo.calendar.domain.Agenda#findByDate(String)} e {@link uniupo.gaborgalazzo.calendar.domain.Agenda#findByWith(String)} delle agevolazioni di {@link uniupo.gaborgalazzo.calendar.domain.Agenda#findByPredicate(Predicate)}

Il metodo {@link uniupo.gaborgalazzo.calendar.domain.Agenda#writeAgenda(Writer)} sfrutta la libreria {@link com.google.gson.Gson} per serializzare {@link uniupo.gaborgalazzo.calendar.domain.Agenda#appointments} in formato Json.

Il metodo {@link uniupo.gaborgalazzo.calendar.domain.Agenda#readAgenda(Reader)} sfrutta la libreria {@link com.google.gson.Gson} per deserializzare un array Json, ne verifica l'integrità, ed ogni errore che si verifica in fase di parsing di un singolo appuntamento (non dell'intero stream) viene incapsulato in un oggetto di tipo {@link AppointmentParsingException} che, in questo caso, non viene utilizzato come eccezzione, ma come bean.

**{@link uniupo.gaborgalazzo.calendar.exception.AppointmentParsingException}:**

Anche se non è mai stata utilizzata come tale questa classe estende {@link java.lang.Exception}. E' stata progettata in previsione di un utilizzo concreto: nel caso in cui gli errori di parsing diventino più freqenti e si richiede una gestione diretta di essi.

**{@link uniupo.gaborgalazzo.calendar.gui.GUI}:**

Questa classe fa da interfaccia verso l'agenda per l'utente. Permette l'aggiunta, rimozione e manipolazione degli appuntamenti. Fornisce strumenti di ricerca.

### <span style="color: #0000ff;">Test:</span>

Per ogni metodo richiesto che dovesse soddifare dei requisiti specifici presenti nella consegna sono stati creati dei test.

Per ogni classe si effettuano i test di tutti i metodi richiesti o di di quelli a supoorto del funzionamento di quelli richiesti.

Tutti i test, tranne quelli relativi all'I/O da file, sono stati effettuati generando dati casuali, al fine di garantire l'integrità dei test.