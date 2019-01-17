<div class="block">

# Agenda - Object Oriented Programming

### Gabor Galazzo (20024195) A.A. 2018-2019 - DiSIT - UniUPO

Agenda è un applicativo per gestire appuntamenti caratterizzati da **data, ora, durata, dove e quando**.

### <span style="color: #0000ff;"><span style="color: #0000ff;"><span style="color: #0000ff;"><span style="color: #0000ff;">Scelte progettuali:</span></span></span></span>

#### [`Appointment`](doc/uniupo/gaborgalazzo/calendar/domain/Appointment.html "class in uniupo.gaborgalazzo.calendar.domain"):

Al fine di gestire il corretto formato dei campi [`Appointment.date`](doc/uniupo/gaborgalazzo/calendar/domain/Appointment.html#date) e [`Appointment.time`](doc/uniupo/gaborgalazzo/calendar/domain/Appointment.html#time) sono state introdotte due costanti statiche di tipo `DateTimeFormatter`:

*   [`Appointment.APPOINTMENT_DATE_FORMAT`](doc/uniupo/gaborgalazzo/calendar/domain/Appointment.html#APPOINTMENT_DATE_FORMAT)
*   [`Appointment.APPOINTMENT_TIME_FORMAT`](doc/uniupo/gaborgalazzo/calendar/domain/Appointment.html#APPOINTMENT_TIME_FORMAT)

Tutti i campi di questa classe sono immutabili al fine di prevenire eventuali errori di incapsulamento che porterebbero ad una scorretta alterazione di un appuntamento causando una situazione di incoerenza difficilmente gestibile da [`Agenda`](doc/uniupo/gaborgalazzo/calendar/domain/Agenda.html "class in uniupo.gaborgalazzo.calendar.domain")

Il metodo [`Appointment.getDateTime()`](doc/uniupo/gaborgalazzo/calendar/domain/Appointment.html#getDateTime--) è stato introdotto per agevolare le operazioni sulle date.

**[`Agenda`](doc/uniupo/gaborgalazzo/calendar/domain/Agenda.html "class in uniupo.gaborgalazzo.calendar.domain"):**

Questa classe permette di gestire e manipolare una lista di appuntamenti. All'interno dell'agenda non ci possono essere due o più appuntamenti che collidono, ovvero già definiti per quello stesso orario, tenendo anche conto della durata.

Per modificare un appuntamento bisogna passare per l'agenda che lo contiene in modo tale da evitare che le modifiche possano creare un agenda inconsistente sui suoi vincoli.

Si considerino i metodi [`findByDate(String)`](doc/uniupo/gaborgalazzo/calendar/domain/Agenda.html#findByDate-java.lang.String-) e [`findByWith(String)`](doc/uniupo/gaborgalazzo/calendar/domain/Agenda.html#findByWith-java.lang.String-) delle agevolazioni di [`findByPredicate(Predicate)`](doc/uniupo/gaborgalazzo/calendar/domain/Agenda.html#findByPredicate-java.util.function.Predicate-)

Il metodo [`writeAgenda(Writer)`](doc/uniupo/gaborgalazzo/calendar/domain/Agenda.html#writeAgenda-java.io.Writer-) sfrutta la libreria `Gson` per serializzare [`appointments`](doc/uniupo/gaborgalazzo/calendar/domain/Agenda.html#appointments) in formato Json.

Il metodo [`readAgenda(Reader)`](doc/uniupo/gaborgalazzo/calendar/domain/Agenda.html#readAgenda-java.io.Reader-) sfrutta la libreria `Gson` per deserializzare un array Json, ne verifica l'integrità, ed ogni errore che si verifica in fase di parsing di un singolo appuntamento (non dell'intero stream) viene incapsulato in un oggetto di tipo `AppointmentParsingException` che, in questo caso, non viene utilizzato come eccezzione, ma come bean.

**[`AppointmentParsingException`](doc/uniupo/gaborgalazzo/calendar/exception/AppointmentParsingException.html "class in uniupo.gaborgalazzo.calendar.exception"):**

Anche se non è mai stata utilizzata come tale questa classe estende `Exception`. E' stata progettata in previsione di un utilizzo concreto: nel caso in cui gli errori di parsing diventino più freqenti e si richiede una gestione diretta di essi.

**[`GUI`](doc/uniupo/gaborgalazzo/calendar/gui/GUI.html "class in uniupo.gaborgalazzo.calendar.gui"):**

Questa classe fa da interfaccia verso l'agenda per l'utente. Permette l'aggiunta, rimozione e manipolazione degli appuntamenti. Fornisce strumenti di ricerca.

### <span style="color: #0000ff;">Test:</span>

Per ogni metodo richiesto che dovesse soddifare dei requisiti specifici presenti nella consegna sono stati creati dei test.

Per ogni classe si effettuano i test di tutti i metodi richiesti o di di quelli a supoorto del funzionamento di quelli richiesti.

Tutti i test, tranne quelli relativi all'I/O da file, sono stati effettuati generando dati casuali, al fine di garantire l'integrità dei test.

</div>