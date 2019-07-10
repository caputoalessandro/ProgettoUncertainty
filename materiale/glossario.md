# Glossario Reti Bayesiane

Probabilità a posteriori: probabilità condizionata

Variabile aleatoria: in una rete bayesiana ogni nodo è una variabile
aleatoria. Una VA è una funzione che associa a un evento un valore.

Variabili di query: variabili a cui sono associati valori di partenza, a
seconda dell'evento.

Variabile di prova (evidenza):

Variabile nascosta Y (o non di prova): Variabili che influenzano,
direttamente o indirettamente la variabile obiettivo e che non hanno un
assegnamento a priori (?).

Variabile obiettivo: variabile di cui calcolare il grado di credenza a
partire dalla variabile di prova e dalle variabili nascoste.

X = tutte le variabili

x = istanza di X (X con assegnamento T o F)

E = variabili di evidenza

e = particolare evento osservato (istanze di  E, E con assegnamento T o F)

Y = variabili nascoste

## prerequisiti per Filtri di Kalman

## distribuzione gaussiana
È la curva della probabilità più usata nell'analisi statistica, perché descrive con discreta efficacia gran parte dei fenomeni naturali. Le probabilità più elevate di un fenomeno si concentrano intorno alla media. Le probabilità si riducono man mano che ci si allontana dal valore medio a destra o a sinistra.

## Varianza
è uno di principali indici di variabilità di una distribuzione statistica

## Covarianza
la covarianza di due variabili statistiche o variabili aleatorie è un numero che fornisce una misura di quanto le due varino assieme, ovvero della loro dipendenza

## matrice di covarianza
si indica di solito con Σ ed è una generalizzazione della covarianza al caso di dimensione maggiore di due. Essa è una matrice che rappresenta la variazione di ogni variabile rispetto alle altre (inclusa se stessa). È una matrice simmetrica.

# Kalman filter https://www.bzarg.com/p/how-a-kalman-filter-works-in-pictures/

È un algoritmo per gestire le **variabili continue**.

Consideriamo uno stato avente posizione e  velocità, il filtro di Kalman considera entrambe le variabili come casuali e distribuite secondo la curva di gauss.

Ogni variabile ha **un valore medio μ**  che è il centro della distribuzione gaussiana (**il suo stato più probabile**) e **una varianza σ^2** che è l'**incertezza**

quello che vogliamo fare è **stimare una nuova posizione basandoci su  una vecchia**, la velocità ci da informazioni sulla stima della nuova posizione

questa relazione tra velocità e posizione è molto importante, la misurazione precedente ci dice qualcosa su come potrebbero essere le misurazioni successive e **l'obiettivo del filtro di kalman è prendere quante più informazioni a partire da misurazioni incerte**.

queste misurazioni sono catturate dalle **matrici di covarianza** le quali mettono in correlazione le variabili delle misurazioni (velocità e posizioe ad esempio).

## descrivere il problema attraverso matrici
vogliamo modellare la nostra base di conoscenza attraverso una macchia gaussiana, quindi ci servono due variabili:

1. **x^k** ovvero la stima migliore (chiamata anche μ )
2. **Pk** la sua matrice di covarianza

ora abbiamo bisogno di guardare lo stato corrente al tempo **k-1**  e predire il prossimo stato all'istante **k**

possiamo  rappresentare questo passo, ovvero la **predizione**, attraverso la matrice **Fk** ovvero quella che  che prende ogni punto della **stima originale** e lo sposta nel nuovo punto nel caso in cui la nostra nuova stima fosse vera.

la funzione sarà:

xk =Fk Xk-1

ora abbiamo la **matrice di predizione Fk** ma non sappiamo ancora come aggiornare la matrice di covarianza.

Per fare ciò moltiplichiamo

Pk = Fk Pk-1 Fk(trasposta)

## Rumore
Non abbiamo considerato tutto, ci potrebbero essere dei cambiamenti che  non dipendono dalle variabili stesse ma da fattori esterni.

tutte le informazioni del mondo esterno verranno inserite in un vettore **uk**

se il nostro corpo aumenta  di velocità anche il calcolo della sua posizione cambierà in base all'accelerazione.

**Bk è chiamata matrice di controllo e uk → il vettore di controllo.** (Per sistemi molto semplici senza influenza esterna, potresti ometterli).

uk = a (accellerazione)

Bk = contiene i valori dell'accellerazione delle due  variabili ovvero posizione e velocità

la formula diventerà:

xk = Fk xk-1 + Bk uk

Cosa succede se la nostra previsione non è un modello accurato al 100% di ciò che sta effettivamente accadendo?

## incertezza
Se conosciamo quali fattori intervengono sul sistema sia internamente che esternamente non ci sono problemi riguardo la predizione ma a volte possono esserci fattori di cui non sappiamo nulla e che potrebbero farci sbagliare.

Possiamo modellare l'incertezza  associata al mondo aggiungendo nuove  "incertezze" ad ogni fase  di predizione.

per modellare tutto ci possiamo dire che ogni punto in xk-1 viene spostato da  qualche parte all'interno di una macchia gaussiana con covarianza Qk. In altre parole possiamo dire che Qk è il rumore causato da  fattori esterni di cui non conosciamo nulla.

Tutto ciò produce una nuova macchia gaussiana

In formule ci basta aggiungere  questo varole Qk alla matrice  di Covarianza

Pk = Fk Pk-1 Fk (trasposto) + Qk

Cosa succede se i nostri sensori non riescono ad ottenere alcuni dati ?

## sensori
i sensori operano sullo stato e producono delle letture, modelliamo i sensori con  una matrice Hk.

per considerare i sensori possiamo applicare le formule nel solito modo:

μ aspettata = Hk xk

P aspettata = Hk Pk Hk(trasposta)

Anche i sensori possono produrre errori e noi rappresenteremo questo errore con Rk, la distribuzione avrà una media che chiameremo zk.

A questo punt avremo due macchie  gaussiane, una calcolata attraverso la nostra stima e una calcolata attraverso i sensori.

Quello che  dobbiamo fare e moltiplicare le due macchie gaussiane e ottenere quella nuova con la sua nuova media e la sua nuova covarianza.

## Combinare le Gaussiane

N(x,μ0,σ0)⋅N(x,μ1,σ1)= N(x,μ′,σ′)

dove N rappresenta la macchia gaussiana

μ′ e σ′ sono legate a determinate formule che hanno in comune dei fattori che noi chiameremo K allo scopo di semplificare il tutto.

a questo punto potremmo rappresentare questi due valori sottoforma di matrice e K prende il nome di **KALMAN GAIN**.

## MORALE

###  variabili basate sulla stima del valore precedente (producono prima macchia gaussiana)
x^k = stima migliore (media delle gaussiana)

Pk = matrice di covarianza

Fk = matrie di predizione

bk = vettore di controllo, variabile esterna che causa rumore  (ad esempio un accellerazione applicata al  corpo )

Bk = matrice  di controllo, contiene i valori della variabile di controllo

Qk = incertezza, rumore causato da fattori esterni di cui non sappiamo nulla

### variabili basate sulle osservazioni dei sensori  (seconda macchia gaussiana)

Hk = valori catturati dai sensori

Rk = rumore dei sensori

zk = stima migliore dei sensori

k = rumore di kalman, incide sia sulla varianza che sul valor medio della nuova gaussiana calcolata dalle prime due. il suo  valore è uguale alla varianza della prima curva diviso la somma delle varianze delle due curve.

# variabili della libreria

A = matrice di predizione

B = matrice di controllo 

H = matrice delle misurazioni dei sensori 

Q = matrice rumore della prima macchia gaussiana

R = matrice  rumore della seconda macchia gaussiana (dei sensori)

P = matrice Rumore di Kalman (incrocio delle due macchie)