package org.ia

import aima.core.probability.bayes.BayesianNetwork
import aima.core.probability.bayes.Node

//P(X,e) probabilità condizionata
//X è l'evento (nodo) che sto analizzando

//e è l'insieme dell'evento che lo condiziona più tutte le variabili nascoste che condizionano X

//in una rete bayesiana le variabili nascoste sono tutti i nodi che compongono il cammino da X a E più tutti
//i nodi che condizionano i nodi del cammino stessi

// prendo un nodo e calcolo le variabili nascoste

// calcolo P(X,e) e la salvo come massimo, P sarà la moltiplicazione  tra le P condizionate di tutte le variabili nascoste
// compresa la variabile casuale E

fun mpe(bn : BayesianNetwork, E : Node ){

    val valorirestituitidallafunzione = bn.variablesInTopologicalOrder.map { nodo ->

    }


}