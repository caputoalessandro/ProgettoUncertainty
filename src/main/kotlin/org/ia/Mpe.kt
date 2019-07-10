package org.ia

import aima.core.probability.CategoricalDistribution
import aima.core.probability.Factor
import aima.core.probability.RandomVariable
import aima.core.probability.bayes.BayesianNetwork
import aima.core.probability.bayes.Node
import aima.core.probability.example.BayesNetExampleFactory
import aima.core.probability.bayes.BayesInference
import aima.core.probability.bayes.exact.EliminationAsk
import aima.core.probability.proposition.AssignmentProposition
import org.omg.CORBA.Object
import java.util.*

//P(X,e) probabilità condizionata
//X è l'evento (nodo) che sto analizzando

//e è l'insieme dell'evento che lo condiziona più tutte le variabili nascoste che condizionano X


fun main( args : Array<String>){

    var bn = BayesNetExampleFactory.constructCloudySprinklerRainWetGrassNetwork()

    var lista = bn.variablesInTopologicalOrder

    val a = lista[3]
    println(a)

    var X = arrayOf(bn.variables["Sprinkler"])

    val ObservedEvidence = arrayOf(AssignmentProposition(a,false))

    val risultato = EliminationAsk().ask(X,ObservedEvidence,bn)

    EliminationAsk

    println("$risultato")

    }

private fun sumOut(`var`: RandomVariable, factors: List<Factor>, bn: BayesianNetwork):
    List<Factor> {
    val summedOutFactors = ArrayList<Factor>()
    val toMultiply = ArrayList<Factor>()
    for (f in factors) {
        if (f.contains(`var`)) {
            toMultiply.add(f)
        } else {
            // This factor does not contain the variable
            // so no need to sum out - see AIMA3e pg. 527.
            summedOutFactors.add(f)
        }
    }

    summedOutFactors.add(pointwiseProduct(toMultiply).sumOut(`var`))

    return summedOutFactors
}

private List<Factor> sumOut(RandomVariable var, List<Factor> factors,
BayesianNetwork bn) {
    List<Factor> summedOutFactors = new ArrayList<Factor>();
    List<Factor> toMultiply = new ArrayList<Factor>();
    for (Factor f : factors) {
        if (f.contains(var)) {
        toMultiply.add(f);
    } else {
        // This factor does not contain the variable
        // so no need to sum out - see AIMA3e pg. 527.
        summedOutFactors.add(f);
    }
    }

    summedOutFactors.add(pointwiseProduct(toMultiply).sumOut(var));

    return summedOutFactors;
}
